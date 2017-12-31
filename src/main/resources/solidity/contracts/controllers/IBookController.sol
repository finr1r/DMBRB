pragma solidity 0.4.19;

contract IBookController {

  function buyBook(uint id) external returns (bool);

  function rentBook(uint id, uint term) external returns (bool);

  function returnBook(uint id) external returns (bool);
}
