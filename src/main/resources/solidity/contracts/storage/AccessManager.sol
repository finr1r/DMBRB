pragma solidity 0.4.18;

import "../common/Owned.sol";
import "./IAccessManager.sol";

contract AccessManager is Owned, IAccessManager {

    mapping(address => mapping(bytes32 => bool)) internal approvedContracts;

    event LogAccessGranted(address indexed self, address actor, bytes32 role);
    event LogAccessDenied(address indexed self, address actor, bytes32 role);

    function giveAccess(address _actor, bytes32 _role) public onlyByOwner returns(bool) {
        require(!approvedContracts[_actor][_role]);
        approvedContracts[_actor][_role] = true;
        LogAccessGranted(this, _actor,  _role);
        return true;
    }

    function blockAccess(address _actor, bytes32 _role) public onlyByOwner returns(bool) {
        require(approvedContracts[_actor][_role]);
        approvedContracts[_actor][_role] = false;
        LogAccessDenied(this, _actor,  _role);
        return true;
    }

    function isAllowed(address _actor, bytes32 _role) public view returns(bool) {
        return approvedContracts[_actor][_role];
    }

}
