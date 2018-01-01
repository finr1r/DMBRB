pragma solidity 0.4.19;

import "../common/Mortal.sol";
import "../storage/BookStorage.sol";
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

  event LogChangeBookStorage(address oldBookStorage, address newBookStorage);
  event LogBuyBook(address indexed _by, uint _id, uint _price);
  event LogRentBook(address indexed _by, uint _id, uint _price, uint _term);
  event LogBookReturn(address indexed _by, uint _id, uint _time);

  // address of the contract storage
  BookStorage bookStorage;

  modifier bookShouldExists(uint id) {
    require(bookStorage.getSize() >= id.add(1));
    _;
  }

  modifier bookShouldBeAvailabel(uint id) {
    require(bookStorage.getStatus(id) != 0 || bookStorage.getStatus(id) != 1);
    _;
  }

  modifier bookShouldBeUnavailabel(uint id) {
    require(bookStorage.getStatus(id) == 0 || bookStorage.getStatus(id) == 1);
    _;
  }

  function BookController(BookStorage _bookStorage) public {
    require(address(_bookStorage) != 0x0);
    bookStorage = _bookStorage;
    owner = msg.sender;
  }

  /**
   * Change the address of the storage contract.
   *
   * @param _bookStorage - address of the storage contract
   *
   * @return 'true' if operation was success
   */
  function changeBookStorage(BookStorage _bookStorage)
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
    bookShouldExists(id)
    bookShouldBeAvailabel(id)
    external
    payable
    returns (bool)
  {
    uint bookPrice = bookStorage.getPrice(id);

    // if buyer sent less money than necessary - finish transaction
    // continue a transaction if money more/equal necessary sum
    if(msg.value >= bookPrice) {
      LogBuyBook(msg.sender, id, bookPrice);
      bookStorage.updateBookStatus(id, 1);

      // if the client sent more money than necessary - return the rest
      uint amount = msg.value.sub(bookPrice);
      msg.sender.transfer(amount);

      return true;
    } else {
      // if money not enoght - return money and cancel the transaction
      msg.sender.transfer(msg.value);
      return false;
    }
  }

  /**
   * Allows rent the book
   *
   * @param id - the ID of chosen book
   * @param term - the period of the rent in seconds
   *
   * @return 'true' if book was rented, 'false' otherwise
   */
  function rentBook(uint id, uint term)
    bookShouldExists(id)
    bookShouldBeAvailabel(id)
    external
    payable
    returns (bool)
  {
    require(bookStorage.getIsRentable(id));
    uint termInDays = (term.div(86400)).add(1);

    uint payment = (bookStorage.getPrice(id).div(100)).mul(termInDays);
    if(msg.value >= payment) {
      LogRentBook(msg.sender, id, payment, termInDays);
      bookStorage.updateBookStatus(id, 0);
      bookStorage.setLastRentDay(id, term);

      // if the client sent more money than necessary - return the rest
      uint amount = msg.value.sub(payment);
      msg.sender.transfer(amount);

      return true;
    } else {
      // if money not enough - return money and cancel the transaction
      msg.sender.transfer(msg.value);
      return false;
    }
  }

  /**
   * Allows to return the book
   *
   * @param id - the ID of the book
   *
   * @return 'true' if book was returned
   */
  function returnBook(uint id, bool isEarlyReturn)
    bookShouldExists(id)
    bookShouldBeUnavailabel(id)
    external
    returns (bool)
  {
    if(isEarlyReturn) {
      bookStorage.updateBookStatus(id, 3);
    } else {
      bookStorage.updateBookStatus(id, 2);
    }
  }

  /**
   * Contract cannot store money or execute another functions throught fallback
   * function.
   */
  function() public {
    revert();
  }

}
