pragma solidity 0.4.19;

import "../common/Mortal.sol";
import "./IBookStorage.sol";

/**
 * @title This contract contain info about books.
 *
 * @dev Contract that contain info about books. This is a significant contract
 *  that execute role of storage for info about books. This contract allows to
 *  separate the data from business logic of program that make a program more
 *  flexible and allows replace business logic contract without data lossing.
 */
contract BookStorage is Mortal, IBookStorage {

  enum BookStatus {
    RENTED,   // customer rent the book
    BUYED,    // customer buy the book
    RETURNED,   // customer return the book after rent
    EARLY_RETURN,   // customer return book because of some defect, etc.
    NEW_IN_SYSTEM    // the book was just added to the system
  }

  struct Book {
    string title;
    string author;
    bool isRentable;
    uint price;
    BookStatus status;
    uint lastRentDay;   // used only for rent period
  }

  Book[] public books;

  // map of the customers that can withdraw money
  mapping (address => uint) public pendingWithdrawals;

  // map of the customers that should pay their debt
  mapping (address => uint) public debtors;


  /**
   * This function add new book to the array.
   *
   * @param _title - the title of the book
   * @param _author - the author of the book
   * @param _isRentable - the book can be rented if 'true', else only for selling
   * @param _price - the price of the books
   *
   * @return 'true' if book added
   */
  function addBook(string _title, string _author, bool _isRentable, uint256 _price)
    onlyByOwner
    public
    returns (bool)
  {
    books.push(Book({
      title: _title,
      author: _author,
      isRentable: _isRentable,
      price: _price,
      status: BookStatus.NEW_IN_SYSTEM,
      lastRentDay: uint(now)
    }));
    return true;
  }

  /**
   * Update book status.
   *
   * @param id - ID of book
   * @param _status - new status of book
   *
   * @return 'true' if book was updated
   */
  function updateBookStatus(uint id, uint8 _status)
    public
    returns (bool)
  {
    books[id].status = BookStatus(_status);
    return true;
  }

  function getSize() public view returns (uint) {
    return books.length;
  }

  function getStatus(uint id) public view returns (uint8) {
    return uint8(books[id].status);
  }

  function getPrice(uint id) public view returns (uint) {
    return books[id].price;
  }

  function getIsRentable(uint id) public view returns (bool) {
    return books[id].isRentable;
  }

  function getLastRentDay(uint id) public view returns (uint) {
      return books[id].lastRentDay;
  }

  function setLastRentDay(uint id, uint term) public {
    books[id].lastRentDay = uint(now) + term;
  }

  function getPendingWithdrawals(address _sender) public view returns (uint) {
      return pendingWithdrawals[_sender];
  }

  function setPendingWithdrawals(address _sender, uint _sum) public {
      pendingWithdrawals[_sender] = _sum;
  }

  function getDebtor(address _sender) public view returns (uint) {
      return debtors[_sender];
  }

  function setDebtor(address _sender, uint amount) public {
      debtors[_sender] = amount;
  }

  /**
   * Contract cannot obtain the ether.
   */
  function() public payable {
    revert();
  }

}
