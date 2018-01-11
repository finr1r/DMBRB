pragma solidity 0.4.18;

import "../common/Owned.sol";
import "./IAccessManager.sol";

contract Storage is Owned {

    struct Crate {
        mapping (bytes32 => uint) uints;
        mapping (bytes32 => address) addresses;
        mapping (bytes32 => bool) bools;
        mapping (bytes32 => int) ints;
        mapping (bytes32 => uint8) uint8s;
        mapping (bytes32 => bytes32) bytes32s;
    }

    mapping (bytes32 => Crate) crates;

    IAccessManager public manager;

    modifier onlyByAllowed(bytes32 _role) {
        require(manager.isAllowed(msg.sender, _role));
        _;
    }

    function setManager(address _manager) external onlyByOwner returns(bool) {
        manager = IAccessManager(_manager);
        return true;
    }

    function setUInt(bytes32 _crate, bytes32 _key, uint _value) onlyByAllowed(_crate) external {
        crates[_crate].uints[_key] = _value;
    }

    function getUInt(bytes32 _crate, bytes32 _key) external view returns(uint) {
        return crates[_crate].uints[_key];
    }

    function setUInt8(bytes32 _crate, bytes32 _key, uint8 _value) onlyByAllowed(_crate) external {
        crates[_crate].uint8s[_key] = _value;
    }

    function getUInt8(bytes32 _crate, bytes32 _key) external view returns(uint8) {
        return crates[_crate].uint8s[_key];
    }

    function setInt(bytes32 _crate, bytes32 _key, int _value) onlyByAllowed(_crate) external {
        crates[_crate].ints[_key] = _value;
    }

    function getInt(bytes32 _crate, bytes32 _key) external view returns(int) {
        return crates[_crate].ints[_key];
    }

    function setAddress(bytes32 _crate, bytes32 _key, address _value) onlyByAllowed(_crate) external {
        crates[_crate].addresses[_key] = _value;
    }

    function getAddress(bytes32 _crate, bytes32 _key) external view returns(address) {
        return crates[_crate].addresses[_key];
    }

    function setBool(bytes32 _crate, bytes32 _key, bool _value) onlyByAllowed(_crate) external {
        crates[_crate].bools[_key] = _value;
    }

    function getBool(bytes32 _crate, bytes32 _key) external view returns(bool) {
        return crates[_crate].bools[_key];
    }

    function setBytes32(bytes32 _crate, bytes32 _key, bytes32 _value) onlyByAllowed(_crate) external {
        crates[_crate].bytes32s[_key] = _value;
    }

    function getBytes32(bytes32 _crate, bytes32 _key) external view returns(bytes32) {
        return crates[_crate].bytes32s[_key];
    }

}
