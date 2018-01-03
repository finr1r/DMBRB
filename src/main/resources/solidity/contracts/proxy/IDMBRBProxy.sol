pragma solidity 0.4.18;

interface IDMBRBProxy {

  function updateContractAddress(address newContract) public returns (bool);
}
