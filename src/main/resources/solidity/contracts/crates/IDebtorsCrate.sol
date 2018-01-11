pragma solidity 0.4.18;

interface IDebtorsCrate {

    function setDebtor(address debtor, uint debtMoney) external returns (bool);

    function getDebtor(address debtor) external view returns (uint);
}
