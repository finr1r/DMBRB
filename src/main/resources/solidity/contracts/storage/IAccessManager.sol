pragma solidity 0.4.18;

interface IAccessManager {
    function isAllowed(address _actor, bytes32 _role) public constant returns(bool);
}
