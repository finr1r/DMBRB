pragma solidity 0.4.19;

contract IBookStorage {

  function addBook(string _title, string _author, bool _isRentable, uint256 _price) public returns (bool);

  function updateBookStatus(uint id, uint8 _status) public returns (bool);
}
