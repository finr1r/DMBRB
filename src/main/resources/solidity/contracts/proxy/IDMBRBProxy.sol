pragma solidity 0.4.19;

interface IDMBRBProxy {

  function updateContractAddress(address newContract) public returns (bool);
}
