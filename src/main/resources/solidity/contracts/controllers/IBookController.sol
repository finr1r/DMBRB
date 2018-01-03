pragma solidity 0.4.19;

import "../storage/IBookStorage.sol";

interface IBookController {

  function changeBookStorage(IBookStorage _bookStorage) public returns (bool);

  function buyBook(uint id) external payable returns (bool);

  function rentBook(uint id, uint term) external payable returns (bool);

  function bookEarlyReturn(uint id) external returns (bool);

  function bookReturn(uint id) external returns (bool);

  function payDebt() external payable returns (bool);

  function withdraw() external returns (bool);
}
