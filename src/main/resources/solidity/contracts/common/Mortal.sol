pragma solidity 0.4.18;

import "./Owned.sol";

/**
 * @title Mortal
 *
 * @dev Contains only one method that allows destroying of the contract but
 *  only by the owner.
 */
contract Mortal is Owned {

    /**
     * @dev Allows destroying the contract only by owner.
     */
    function kill() onlyByOwner public {
        selfdestruct(owner);
    }
}
