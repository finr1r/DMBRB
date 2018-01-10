pragma solidity 0.4.18;

interface IDMBRBController {

    function setWithdrawalsCrate(address _withdrawalsCrate) public returns (bool);

    function setBooksCrate(address _booksCrate) public returns (bool);

    function setDebtorsCrate(address _debtorsCrate) public returns (bool);

    function buyBook(bytes32 _key) external payable returns (bool);

    function rentBook(bytes32 _key, uint term) external payable returns (bool);

    function bookEarlyReturn(bytes32 _key) external returns (bool);

    function bookReturn(bytes32 _key) external returns (bool);

    function payDebt() external payable returns (bool);

    function withdraw() external returns (bool);
}
