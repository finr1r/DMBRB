pragma solidity 0.4.18;

import "../common/Mortal.sol";
import "../lib/SafeMath.sol";
import "./IBookController.sol";

/**
 * @title Contract that allow customers to buy and rent books.
 *
 * @dev This contract stores data in the storage and allows customers to buy and
 *  rent books. This is also a part of pattern that allows to upgrade the system.
 *  This contract only manipulates the data that stored in the storage so it can
 *  be replaced by a new better contract without losing data.
 */
contract BookController is Mortal, IBookController {

  using SafeMath for uint;

  event LogChangeBookStorage(address _oldBookStorage, address _newBookStorage);
  event LogBuyBook(address indexed _sender, uint _id, uint _price);
  event LogRentBook(address indexed _sender, uint _id, uint _price, uint _term);
  event LogBookReturn(address indexed _sender, uint _id, uint _time);
  event LogEtherReceived(address indexed _sender, uint _value);

  // address of the contract storage
  IBookStorage public bookStorage;

  modifier bookShouldExists(uint id) {
    require(bookStorage.getSize() >= id.add(1));
    _;
  }

  modifier bookShouldBeAvailabel(uint id) {
    require(bookStorage.getStatus(id) != 0 && bookStorage.getStatus(id) != 1);
    _;
  }

  modifier bookShouldBeRented(uint id) {
    require(bookStorage.getStatus(id) == 0);
    _;
  }

  modifier senderShouldNotBeDebtor() {
    require(bookStorage.getDebtor(msg.sender) == 0);
    _;
  }

  modifier senderShouldBeDebtor() {
    require(bookStorage.getDebtor(msg.sender) > 0);
    _;
  }


  function BookController(IBookStorage _bookStorage) public {
    require(address(_bookStorage) != 0x0);
    bookStorage = _bookStorage;
  }

  /**
   * Change the address of the storage contract.
   *
   * @param _bookStorage - address of the storage contract
   *
   * @return 'true' if operation was success
   */
  function changeBookStorage(IBookStorage _bookStorage)
    onlyByOwner
    public
    returns (bool)
  {
    require(address(_bookStorage) != 0x0);
    LogChangeBookStorage(bookStorage, _bookStorage);
    bookStorage = _bookStorage;
    return true;
  }

  /**
   * Allows buying book by customer
   *
   * @param id - the ID of chosen book
   *
   * @return 'true' if buying was success, 'false' otherwise
   */
  function buyBook(uint id)
    senderShouldNotBeDebtor
    bookShouldExists(id)
    bookShouldBeAvailabel(id)
    external
    payable
    returns (bool)
  {
    uint bookPrice = bookStorage.getPrice(id);
    uint pendingMoney = bookStorage.getPendingWithdrawals(msg.sender);
    uint moneyOfSender = msg.value.add(pendingMoney);

    if(moneyOfSender >= bookPrice) {
      bookStorage.updateBookStatus(id, 1);

      // if the buyer sent more money than necessary - put the rest into the map
      uint amount = moneyOfSender.sub(bookPrice);
      bookStorage.setPendingWithdrawals(msg.sender, amount);

      LogBuyBook(msg.sender, id, bookPrice);
      return true;
    } else {
      // if money doesn't enough - put money into map, the customer can withdraw
      // it or leave in system for the next purchases
      bookStorage.setPendingWithdrawals(msg.sender, moneyOfSender);
      return false;
    }
  }

  /**
   * Allows rent the book.
   *
   * @notice the book rent period cannot be less than 14 days.
   *
   * @param id - the ID of chosen book
   * @param term - the period of the rent in seconds
   *
   * @return 'true' if book was rented, 'false' otherwise
   */
  function rentBook(uint id, uint term)
    senderShouldNotBeDebtor
    bookShouldExists(id)
    bookShouldBeAvailabel(id)
    external
    payable
    returns (bool)
  {
    require(bookStorage.getIsRentable(id));
    uint termInDays = (term.div(86400)).add(1);

    assert(termInDays >= 14);

    uint payment = (bookStorage.getPrice(id).div(150)).mul(termInDays);
    uint pendingMoney = bookStorage.getPendingWithdrawals(msg.sender);
    uint moneyOfSender = msg.value.add(pendingMoney);

    if(moneyOfSender >= payment) {
      bookStorage.updateBookStatus(id, 0);
      bookStorage.setLastRentDay(id, term);

      // if the client sent more money than necessary - return the rest
      uint amount = moneyOfSender.sub(payment);
      bookStorage.setPendingWithdrawals(msg.sender, amount);

      LogRentBook(msg.sender, id, payment, termInDays);
      return true;
    } else {
      // if money not enough - put money into the map
      bookStorage.setPendingWithdrawals(msg.sender, moneyOfSender);
      return false;
    }
  }

  /**
   * Return book early than last day of rent
   *
   * @param id - the ID of the book
   *
   * @return 'true' if return operation was success
   */
  function bookEarlyReturn(uint id)
    bookShouldExists(id)
    bookShouldBeRented(id)
    external
    returns (bool)
  {
    require(uint(bookStorage.getLastRentDay(id)) >= uint(now));
    bookStorage.updateBookStatus(id, 3);

    // the money for days that left to the end of the rent period should be returned
    uint lastRentDay = bookStorage.getLastRentDay(id);
    uint leftDays = (uint(lastRentDay).sub(uint(now))).div(86400);

    uint amount = (bookStorage.getPrice(id).div(150)).mul(leftDays);
    uint pendingMoney = bookStorage.getPendingWithdrawals(msg.sender);
    uint amountToReturn = pendingMoney.add(amount);
    bookStorage.setPendingWithdrawals(msg.sender, amountToReturn);

    LogBookReturn(msg.sender, id, uint(now));
    return true;
  }

  /**
   * Allows to return the book
   *
   * @param id - the ID of the book
   *
   * @return 'true' if book was returned
   */
  function bookReturn(uint id)
    bookShouldExists(id)
    bookShouldBeRented(id)
    external
    returns (bool)
  {
    require(uint(now) >= uint(bookStorage.getLastRentDay(id)));
    bookStorage.updateBookStatus(id, 2);

    // if customer return book later than the last day of rent then the customer
    // must pay for these days
    uint lastRentDay = bookStorage.getLastRentDay(id);
    uint penaltyDays = (uint(now).sub(uint(lastRentDay))).div(86400);
    if(penaltyDays > 0) {
      uint penaltyMoney = (bookStorage.getPrice(id).div(150)).mul(penaltyDays);
      uint pendingMoney = bookStorage.getPendingWithdrawals(msg.sender);

      // if money enough - just substract the necessary sum from pendingWithdrawals,
      // else - write to the debtors map.
      if(pendingMoney >= penaltyMoney) {
        bookStorage.setPendingWithdrawals(msg.sender, pendingMoney.sub(penaltyMoney));
      } else {
        uint amount = bookStorage.getPendingWithdrawals(msg.sender);
        uint penaltyMoneyLeft = penaltyMoney.sub(amount);
        bookStorage.setPendingWithdrawals(msg.sender, 0);
        bookStorage.setDebtor(msg.sender, penaltyMoneyLeft);
      }
    }

    LogBookReturn(msg.sender, id, uint(now));
    return true;
  }

  /**
   * Allows clients to pay a debt
   *
   * @return 'true' if debt was paid, 'false' otherwise
   */
  function payDebt()
    senderShouldBeDebtor
    external
    payable
    returns (bool)
  {
    uint penaltyMoney = bookStorage.getDebtor(msg.sender);

    if(msg.value >= penaltyMoney) {
      uint extraMoney = msg.value.sub(penaltyMoney);
      bookStorage.setDebtor(msg.sender, 0);
      bookStorage.setPendingWithdrawals(msg.sender, extraMoney);

      return true;
    } else {
      uint amountToPay = penaltyMoney.sub(msg.value);
      bookStorage.setDebtor(msg.sender, amountToPay);

      return false;
    }
  }

  /**
   * Allows customers to withdraw their money.
   */
  function withdraw()
    external
    returns (bool)
  {
    uint amount = bookStorage.getPendingWithdrawals(msg.sender);
    bookStorage.setPendingWithdrawals(msg.sender, 0);
    msg.sender.transfer(amount);
  }


  function() public payable {
    LogEtherReceived(msg.sender, msg.value);
  }

}
