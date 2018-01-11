pragma solidity 0.4.18;

import "../common/Mortal.sol";
import "../common/Validator.sol";

/**
 * @title DMBRBProxy
 *
 * @dev This contract contains addresses of actual contracts. Contains only addresses
 *  of major contracts.
 */
contract DMBRBProxy is Mortal, Validator {

    address public withdrawalsCrate;
    address public booksCrate;
    address public debtorsCrate;
    address public dmbrbController;
    address public storageAddress;

    event LogChangeAddress(bytes32 contractName, address oldAddress, address newAddress);

    /**
     * @dev Setting new address of WithdrawalsCrate contract.
     *
     * @param _withdrawalsCrate - address of the new WithdrawalsCrate contract
     *
     * @return 'true' if address was successfully changed
     */
    function setWithdrawalsCrateAddress(address _withdrawalsCrate)
        onlyByOwner
        addressIsNotNull(_withdrawalsCrate)
        public
        returns (bool)
    {
        address oldAddress = withdrawalsCrate;
        withdrawalsCrate = _withdrawalsCrate;
        LogChangeAddress("WithdrawalsCrate", oldAddress, _withdrawalsCrate);
        return true;
    }

    /**
     * @dev Setting new address of BooksCrate contract.
     *
     * @param _booksCrate - address of the new BooksCrate contract
     *
     * @return 'true' if address was successfully changed
     */
    function setBooksCrateAddress(address _booksCrate)
        onlyByOwner
        addressIsNotNull(_booksCrate)
        public
        returns (bool)
    {
        address oldAddress = _booksCrate;
        booksCrate = _booksCrate;
        LogChangeAddress("BooksCrate", oldAddress, _booksCrate);
        return true;
    }

    /**
     * @dev Setting new address of DebtorsCrate contract.
     *
     * @param _debtorsCrate - address of the new DebtorsCrate contract
     *
     * @return 'true' if address was successfully changed
     */
    function setDebtorsCrateAddress(address _debtorsCrate)
        onlyByOwner
        addressIsNotNull(_debtorsCrate)
        public
        returns (bool)
    {
        address oldAddress = debtorsCrate;
        debtorsCrate = _debtorsCrate;
        LogChangeAddress("DebtorsCrate", oldAddress, _debtorsCrate);
        return true;
    }

    /**
     * @dev Setting new address of DMBRBCotroller contract.
     *
     * @param _dmbrbController - address of the new DMBRBCotroller contract
     *
     * @return 'true' if address was successfully changed
     */
    function setControllerAddress(address _dmbrbController)
        onlyByOwner
        addressIsNotNull(_dmbrbController)
        public
        returns (bool)
    {
        address oldAddress = dmbrbController;
        dmbrbController = _dmbrbController;
        LogChangeAddress("DMBRBController", oldAddress, _dmbrbController);
        return true;
    }

    /**
     * @dev Setting new address of Storage contract.
     *
     * @param _storage - address of the Storage contract
     *
     * @return 'true' if address was successfully changed
     */
    function setStorageAddress(address _storage)
        onlyByOwner
        addressIsNotNull(_storage)
        public
        returns (bool)
    {
        address oldAddress = storageAddress;
        storageAddress = _storage;
        LogChangeAddress("Storage", oldAddress, _storage);
        return true;
    }


    function() public {
        revert();
    }

}
