pragma solidity 0.4.19;

interface IDMBRBProxy {

  function updateFunction(address newContract) public returns (bool);
}
