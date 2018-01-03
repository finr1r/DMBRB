pragma solidity 0.4.18;

interface IBookStorage {

  function addBook(string _title, string _author, bool _isRentable, uint256 _price) public returns (bool);

  function updateBookStatus(uint id, uint8 _status) public returns (bool);

  function getSize() public view returns (uint);

  function getStatus(uint id) public view returns (uint8);

  function getPrice(uint id) public view returns (uint);

  function getIsRentable(uint id) public view returns (bool);

  function getLastRentDay(uint id) public view returns (uint);

  function setLastRentDay(uint id, uint term) public;

  function getPendingWithdrawals(address _sender) public view returns (uint);

  function setPendingWithdrawals(address _sender, uint _sum) public;

  function getDebtor(address _sender) public view returns (uint);

  function setDebtor(address _sender, uint amount) public;
}
