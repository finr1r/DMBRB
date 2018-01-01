pragma solidity 0.4.19;

import "../common/Mortal.sol";
import "./IDMBRBProxy.sol";

/**
 * @title Using for keeping address of actual version of contract.
 *
 * @dev This contract is a significant part of working and upgradability of system.
 *  This contract necessary such as it is keeping an address of actual version of
 *  contract. Because of the fact that a lot of vulnerabilities will be detected
 *  after the system begin to work it is necessary to give  capability replace one
 *  vulnerable contract with another one with better security.
 */
contract DMBRBProxy is Mortal, IDMBRBProxy {

  event LogUpdateContract(address indexed oldContract, address indexed newContract);

  // actual contract address
  address public activeContract;

  function DMBRBProxy() public {
    owner = msg.sender;
  }

  /**
   * This function updates the old contract address with new better.
   *
   * @param newContract - the address of new better contract
   *
   * @return 'true' if address of contract was updated
   */
  function updateContract(address newContract)
    onlyByOwner
    public
    returns (bool)
  {
    require(address(newContract) != 0x0);
    LogUpdateContract(activeContract, newContract);
    activeContract = newContract;
    return true;
  }

  /**
   * Contract cannot store money or execute another functions throught fallback
   * function.
   */
  function() public {
    revert();
  }

}
