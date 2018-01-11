pragma solidity 0.4.18;

import "./Storage.sol";
import "./StorageInterface.sol";

contract StorageAdapter {
    using StorageInterface for *;

    StorageInterface.Config store;

    function StorageAdapter(Storage _store, bytes32 _crate) public {
        assert(_crate != bytes32(0));
        store.init(_store, _crate);
    }
}
