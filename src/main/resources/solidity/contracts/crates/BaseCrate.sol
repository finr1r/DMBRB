pragma solidity 0.4.18;

import "../common/Mortal.sol";
import "../common/Validator.sol";
import "../storage/IAccessManager.sol";

/**
 * @title BaseCrate
 *
 * @dev This is a basic contract that defines method and modifier that allow execution
 *  of operations only from controller contract.
 */
contract BaseCrate is Mortal, Validator {

    bytes32 internal CONTROLLER_ROLE = "Controller";

    IAccessManager public manager;

    modifier onlyByAllowed() {
        require(manager.isAllowed(msg.sender, CONTROLLER_ROLE));
        _;
    }

    /**
     * @dev Setting address of the AccessManager contract.
     *
     * @param _manager - address of the AccessManager contract
     *
     * @return 'true' if operation was success
     */
    function setManager(address _manager)
        onlyByOwner
        addressIsNotNull(_manager)
        public
        returns (bool)
    {
        manager = IAccessManager(_manager);
        return true;
    }

}
