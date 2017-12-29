pragma solidity 0.4.19;

import "./Owned.sol";

/**
 * @title Contract that contain modifiers and functions for access managing in
 *  smart-contracts.
 *
 * @dev Contract that executes access managing using modifiers and functions.
 *  The first part of is 'Owned' contract that contains code for checking whether
 *  sender is owner. This contract execute checking for other roles which will be
 *  encountered during program's work.
 */
contract Administration {

  // administrators/developers
  address[] public administrators;

  modifier onlyByAdmin {
    require(isAdmin(msg.sender));
    _;
  }

  /**
   * Makes a check whether the sender is an admin.
   *
   * @param address sender - address of the sender
   *
   * @return bool - 'true' if sender is administator, false otherwise.
   */
  function isAdmin(address sender) internal constant returns (bool) {
    for(uint8 i=0; i<administrators.length; i++) {
      if(sender == administrators[i]) {
        return true;
      }
    }
    return false;
  }

}
