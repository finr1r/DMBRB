pragma solidity 0.4.18;

/**
 * @title AddressValidator
 *
 * @dev This contract contains the modifier(s) that executes validation of
 *  addresses.
 */
contract Validator {

    modifier isNotNull(address _address) {
        require(_address != 0x0);
        _;
    }

}
