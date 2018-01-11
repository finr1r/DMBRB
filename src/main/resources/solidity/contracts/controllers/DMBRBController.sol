pragma solidity 0.4.18;

import "../common/Mortal.sol";
import "../common/Validator.sol";
import "../crates/IWithdrawalsCrate.sol";
import "../crates/IBooksCrate.sol";
import "../crates/IDebtorsCrate.sol";
import "../lib/SafeMath.sol";
import "./IDMBRBController.sol";

/**
 * @title DMBRBController
 *
 * @dev Controls the activity of the customer. This contract allows customers
 *  to execute main operations.
 */
contract DMBRBController is IDMBRBController, Mortal, Validator {

    using SafeMath for uint;

    IWithdrawalsCrate public withdrawalsCrate;
    IBooksCrate public booksCrate;
    IDebtorsCrate public debtorsCrate;

    IBooksCrate.BookStatus public RENTED_BOOK_STATUS = IBooksCrate.BookStatus.RENTED;
    IBooksCrate.BookStatus public BUYED_BOOK_STATUS = IBooksCrate.BookStatus.BUYED;
    IBooksCrate.BookStatus public EARLY_RETURN_BOOK_STATUS = IBooksCrate.BookStatus.EARLY_RETURN;
    IBooksCrate.BookStatus public RETURNED_BOOK_STATUS = IBooksCrate.BookStatus.RETURNED;

    uint public TOTAL_TERM_IN_DAYS = 150;
    uint public SECONDS_IN_DAY = 86400;


    event LogChangeCrateAddress(bytes32 contractName, address oldAddress, address newAddress);
    event LogBuyBook(address indexed actor, bytes32 key, uint price);
    event LogRentBook(address indexed actor, bytes32 key, uint price, uint period);
    event LogReturnBook(address indexed actor, bytes32 key, uint time);
    event LogEtherReceived(address indexed from, uint value);


    modifier bookExists(bytes32 _key) {
        require(booksCrate.getBookTitle(_key) != bytes32(0));
        _;
    }

    modifier bookIsAvailabel(bytes32 _key) {
        require(booksCrate.getBookStatus(_key) != uint(RENTED_BOOK_STATUS));
        require(booksCrate.getBookStatus(_key) != uint(BUYED_BOOK_STATUS));
        _;
    }

    modifier bookIsRented(bytes32 _key) {
        require(booksCrate.getBookStatus(_key) == uint(RENTED_BOOK_STATUS));
        _;
    }

    modifier senderIsNotDebtor() {
        require(debtorsCrate.getDebtor(msg.sender) == 0);
        _;
    }

    modifier senderIsDebtor() {
        require(debtorsCrate.getDebtor(msg.sender) > 0);
        _;
    }


    /**
     * @dev Setting new address of the WithdrawalsCrate contract.
     *
     * @param _withdrawalsCrate - new contract address
     *
     * @return 'true' if operation was success
     */
    function setWithdrawalsCrate(address _withdrawalsCrate)
        addressIsNotNull(_withdrawalsCrate)
        public
        returns (bool)
    {
        address oldAddress = address(withdrawalsCrate);
        withdrawalsCrate = IWithdrawalsCrate(_withdrawalsCrate);
        LogChangeCrateAddress("IWithdrawalsCrate", oldAddress, _withdrawalsCrate);
        return true;
    }

    /**
     * @dev Setting new address of the BooksCrate contract.
     *
     * @param _booksCrate - new contract address
     *
     * @return 'true' if operation was success
     */
    function setBooksCrate(address _booksCrate)
        addressIsNotNull(_booksCrate)
        public
        returns (bool)
    {
        address oldAddress = address(booksCrate);
        booksCrate = IBooksCrate(_booksCrate);
        LogChangeCrateAddress("IBooksCrate", oldAddress, _booksCrate);
        return true;
    }

    /**
     * @dev Setting new address of the DebtorsCrate contract.
     *
     * @param _debtorsCrate - new contract address
     *
     * @return 'true' if operation was success
     */
    function setDebtorsCrate(address _debtorsCrate)
        addressIsNotNull(_debtorsCrate)
        public
        returns (bool)
    {
        address oldAddress = address(debtorsCrate);
        debtorsCrate = IDebtorsCrate(_debtorsCrate);
        LogChangeCrateAddress("IDebtorsCrate", oldAddress, _debtorsCrate);
        return true;
    }

    /**
   * @dev Execute buying book by customer.
   *
   * @param _key - the key of chosen book
   *
   * @return 'true' if buying was success, 'false' otherwise
   */
  function buyBook(bytes32 _key)
    senderIsNotDebtor
    bookExists(_key)
    bookIsAvailabel(_key)
    external
    payable
    returns (bool)
  {
    uint bookPrice = booksCrate.getBookPrice(_key);
    uint pendingMoney = withdrawalsCrate.getPendingWithdrawals(msg.sender);
    uint moneyOfSender = msg.value.add(pendingMoney);

    if(moneyOfSender >= bookPrice) {
      booksCrate.setBookStatus(_key, BUYED_BOOK_STATUS);

      uint amount = moneyOfSender.sub(bookPrice);
      withdrawalsCrate.setPendingWithdrawals(msg.sender, amount);
      booksCrate.setBookOwner(_key, msg.sender);

      LogBuyBook(msg.sender, _key, bookPrice);
      return true;

    } else {
      withdrawalsCrate.setPendingWithdrawals(msg.sender, moneyOfSender);
      return false;
    }
  }

  /**
   * @dev Execute renting of the book.
   *
   * @notice The book rent period cannot be less than 14 days.
   *
   * @param _key - the key of chosen book
   * @param term - the period of the rent in seconds
   *
   * @return 'true' if operation was success, 'false' otherwise
   */
  function rentBook(bytes32 _key, uint term)
    senderIsNotDebtor
    bookExists(_key)
    bookIsAvailabel(_key)
    external
    payable
    returns (bool)
  {
    require(booksCrate.getBookIsRentable(_key));
    uint termInDays = (term.div(SECONDS_IN_DAY)).add(1);

    require(termInDays >= 14);

    uint payment = (booksCrate.getBookPrice(_key).div(TOTAL_TERM_IN_DAYS)).mul(termInDays);
    uint pendingMoney = withdrawalsCrate.getPendingWithdrawals(msg.sender);
    uint moneyOfSender = msg.value.add(pendingMoney);

    if(moneyOfSender >= payment) {
      booksCrate.setBookStatus(_key, RENTED_BOOK_STATUS);
      booksCrate.setPeriodOfBookRent(_key, uint(now).add(term));

      uint amount = moneyOfSender.sub(payment);
      withdrawalsCrate.setPendingWithdrawals(msg.sender, amount);
      booksCrate.setBookOwner(_key, msg.sender);

      LogRentBook(msg.sender, _key, payment, termInDays);
      return true;

    } else {
      withdrawalsCrate.setPendingWithdrawals(msg.sender, moneyOfSender);
      return false;
    }
  }

  /**
   * @dev Returns book early than last day of rent.
   *
   * @param _key - the key of the book
   *
   * @return 'true' if return operation was success
   */
  function bookEarlyReturn(bytes32 _key)
    bookExists(_key)
    bookIsRented(_key)
    external
    returns (bool)
  {
    require(uint(booksCrate.getPeriodOfBookRent(_key)) >= uint(now));
    booksCrate.setBookStatus(_key, EARLY_RETURN_BOOK_STATUS);

    uint lastRentDay = booksCrate.getPeriodOfBookRent(_key);
    uint leftDays = (uint(lastRentDay).sub(uint(now))).div(SECONDS_IN_DAY);

    uint amount = (booksCrate.getBookPrice(_key).div(TOTAL_TERM_IN_DAYS)).mul(leftDays);
    uint pendingMoney = withdrawalsCrate.getPendingWithdrawals(msg.sender);
    uint amountToReturn = pendingMoney.add(amount);
    withdrawalsCrate.setPendingWithdrawals(msg.sender, amountToReturn);

    LogReturnBook(msg.sender, _key, uint(now));
    return true;
  }

  /**
   * @dev Returns the book.
   *
   * @param _key - the key of the book
   *
   * @return 'true' if book was returned
   */
  function bookReturn(bytes32 _key)
    bookExists(_key)
    bookIsRented(_key)
    external
    returns (bool)
  {
    require(uint(now) >= uint(booksCrate.getPeriodOfBookRent(_key)));
    booksCrate.setBookStatus(_key, RETURNED_BOOK_STATUS);

    uint lastRentDay = booksCrate.getPeriodOfBookRent(_key);
    uint penaltyDays = (uint(now).sub(uint(lastRentDay))).div(SECONDS_IN_DAY);
    if(penaltyDays > 0) {
      uint penaltyMoney = (booksCrate.getBookPrice(_key).div(TOTAL_TERM_IN_DAYS)).mul(penaltyDays);
      uint pendingMoney = withdrawalsCrate.getPendingWithdrawals(msg.sender);

      if(pendingMoney >= penaltyMoney) {
        withdrawalsCrate.setPendingWithdrawals(msg.sender, pendingMoney.sub(penaltyMoney));
      } else {
        uint amount = withdrawalsCrate.getPendingWithdrawals(msg.sender);
        uint penaltyMoneyLeft = penaltyMoney.sub(amount);
        withdrawalsCrate.setPendingWithdrawals(msg.sender, 0);
        debtorsCrate.setDebtor(msg.sender, penaltyMoneyLeft);
      }
    }

    LogReturnBook(msg.sender, _key, uint(now));
    return true;
  }

  /**
   * @dev Allows clients to pay a debt
   *
   * @return 'true' if debt was paid, 'false' otherwise
   */
  function payDebt()
    senderIsDebtor
    external
    payable
    returns (bool)
  {
    uint penaltyMoney = debtorsCrate.getDebtor(msg.sender);

    if(msg.value >= penaltyMoney) {
      uint extraMoney = msg.value.sub(penaltyMoney);
      debtorsCrate.setDebtor(msg.sender, 0);
      withdrawalsCrate.setPendingWithdrawals(msg.sender, extraMoney);

      return true;
    } else {
      uint amountToPay = penaltyMoney.sub(msg.value);
      debtorsCrate.setDebtor(msg.sender, amountToPay);

      return false;
    }
  }

  /**
   * @dev Allows customers withdraws their money.
   */
  function withdraw()
    external
    returns (bool)
  {
    uint amount = withdrawalsCrate.getPendingWithdrawals(msg.sender);
    withdrawalsCrate.setPendingWithdrawals(msg.sender, 0);
    msg.sender.transfer(amount);
  }


  function() public payable {
    LogEtherReceived(msg.sender, msg.value);
  }

}
