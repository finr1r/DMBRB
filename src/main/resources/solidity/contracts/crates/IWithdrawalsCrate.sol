pragma solidity 0.4.18;

interface IWithdrawalsCrate {

    function setPendingWithdrawals(address _actor, uint _value) external returns (bool);

    function getPendingWithdrawals(address _actor) external view returns (uint);
}
