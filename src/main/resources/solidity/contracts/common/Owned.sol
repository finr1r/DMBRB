pragma solidity 0.4.18;

/**
 * @title Owned is a contract that specifies some operations for the owner.
 *
 * @dev There are some operations that should be executed only by the owner.
 *  Goal this contract is to define such operations.
 */
contract Owned {

    address public owner;

    event LogChangeOwner(address indexed previousOwner, address indexed newOwner);

    function Owned() public {
        owner = msg.sender;
    }

    modifier onlyByOwner {
        require(msg.sender == owner);
        _;
    }

    /**
     * @dev Allows to change the owner of the contract.
     */
    function changeOwner(address newOwner) onlyByOwner public returns (bool) {
        require(newOwner != 0x0);
        LogChangeOwner(owner, newOwner);
        owner = newOwner;
        return true;
    }
}
