pragma solidity 0.4.19;

import "../common/Mortal.sol";
import "../storage/BookStorage.sol";
import "../lib/SafeMath.sol";

/**
 * @title Contract that allow customers to buy and rent books.
 *
 * @dev This contract stores data in the storage and allows customers to buy and
 *  rent books. This is also a part of pattern that allows to upgrade the system.
 *  This contract only manipulates the data that stored in the storage so it can
 *  be replaced by a new better contract without losing data.
 */
contract BookController is Mortal {

  using SafeMath for uint;

  event LogBuyBook(address indexed _by, uint _id, uint _price);

  // address of the contract storage
  BookStorage bookStorage;

  modifier bookShouldBeAvailabel(uint id) {
    // 0 means that book is currently rented by the customer
    require(bookStorage.getSize() >= id.add(1) && bookStorage.getStatus(id) != 0 && bookStorage.getStatus(id) != 1);
    _;
  }

  function BookController(BookStorage _bookStorage) public {
    require(address(_bookStorage) != 0x0);
    bookStorage = _bookStorage;
    owner = msg.sender;
  }

  /**
   * Upgrade the address of the storage contract.
   *
   * @param _bookStorage - address of the storage contract
   *
   * @return 'true' if operation was success
   */
  function upgradeBookStorage(BookStorage _bookStorage)
    onlyByOwner
    public
    returns (bool)
  {
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
    bookShouldBeAvailabel(id)
    external
    payable
    returns (bool)
  {
    uint bookPrice = bookStorage.getPrice(id);

    if(msg.value >= bookPrice) {
      uint amount = msg.value.sub(bookPrice);
      msg.sender.transfer(amount);

      bookStorage.updateBookStatus(id, 1);

      LogBuyBook(msg.sender, id, bookPrice);

      return true;
    } else {
      return false;
    }
  }

}
