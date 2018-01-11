package com.dkohut.dmbrb;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.dkohut.dmbrb.wrappers.AccessManager;
import com.dkohut.dmbrb.wrappers.Storage;
import com.dkohut.dmbrb.wrappers.WithdrawalsCrate;

public class WithdrawalsCrateTest {

	private static final String MAIN_PRIVATE_KEY = "29d9b2e87e7c9e50ab355932c2aa1c2e4602523fafe0bf96ab3152b785cbf225";
	private static final String SECOND_PRIVATE_KEY = "a91d0741be7241354ea5dfbdd2c6b05289f7d8e2ccee0f8e6a7e99eae900dba6";
	private static final String BALANCE = "100000000000000000000";
	private static final String TEST_ADDRESS = "0x692a70d2e424a56d2c6c27aa97d1a86395877b3a";
	private static final String TEST_NULL_ADDRESS = "0x0";
	private static final Bytes32 WITHDRAWALS_CRATE = stringToBytes32("WithdrawalsCrate");
	private static final Bytes32 INCORRECT_WITHDRAWALS_CRATE = stringToBytes32("Test");
	private static final Bytes32 TEST_ROLE = stringToBytes32("Controller");
	private static final Long AMOUNT_TO_WITHDRAW = 5000L; 
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
	private WithdrawalsCrate withdrawalsCrate;
	
	@BeforeClass
	public static void setUpTestrpc() throws IOException {
		testrpc = new ProcessBuilder(
				"testrpc",
				"--account=0x" + MAIN_PRIVATE_KEY + "," + BALANCE,
				"--account=0x" + SECOND_PRIVATE_KEY + "," + BALANCE
			).start();
	}
	
	@AfterClass
	public static void shutDownTestrpc() {
		testrpc.destroyForcibly();
	}
	
	@Before
	public void setUp() throws Exception {
		web3j = Web3j.build(new HttpService());
		credentials = Credentials.create(MAIN_PRIVATE_KEY);
		
		AccessManager accessManager = AccessManager.deploy(
				web3j, 
				credentials, 
				AccessManager.GAS_PRICE, 
				AccessManager.GAS_LIMIT
			).send();
		
		Storage storage = Storage.deploy(
				web3j, 
				credentials, 
				Storage.GAS_PRICE, 
				Storage.GAS_LIMIT
			).send();
		
		withdrawalsCrate = WithdrawalsCrate.deploy(
				web3j, 
				credentials, 
				WithdrawalsCrate.GAS_PRICE, 
				WithdrawalsCrate.GAS_LIMIT, 
				new Address(storage.getContractAddress()), 
				WITHDRAWALS_CRATE
			).send();
		
		storage.setManager(new Address(accessManager.getContractAddress())).send();
		withdrawalsCrate.setManager(new Address(accessManager.getContractAddress())).send();
		
		accessManager.giveAccess(
				new Address(withdrawalsCrate.getContractAddress()), 
				WITHDRAWALS_CRATE
			).send();
		
		accessManager.giveAccess(
				new Address(credentials.getAddress()), 
				TEST_ROLE
			).send();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = withdrawalsCrate.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		withdrawalsCrate.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = withdrawalsCrate.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing")
			.isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct owner address after changing")
			.isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		withdrawalsCrate.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		withdrawalsCrate.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKill() throws Exception {
		withdrawalsCrate.kill().send();
		Address owner = withdrawalsCrate.owner().send();
		assertThat(owner.getValue().toString()).isNotNull();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		withdrawalsCrate.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		withdrawalsCrate.kill().send();
	}
	
	@Test
	public void testSetManager() throws Exception {
		withdrawalsCrate.setManager(new Address(TEST_ADDRESS)).send();
		Address manager = withdrawalsCrate.manager().send();
		assertThat(manager.getValue().toString()).as("Should have correct address").isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetManagerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		withdrawalsCrate.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		withdrawalsCrate.setManager(new Address(TEST_ADDRESS)).send();
	}
	
	@Test
	public void setPendingWithdrawals() throws Exception {
		withdrawalsCrate.setPendingWithdrawals(
				new Address(TEST_ADDRESS), 
				new Uint256(AMOUNT_TO_WITHDRAW)
			).send();
		
		Uint256 debtAmount = withdrawalsCrate.getPendingWithdrawals(new Address(TEST_ADDRESS)).send();
		assertThat(debtAmount.getValue().longValue()).as("Should have correct value").isEqualTo(AMOUNT_TO_WITHDRAW);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetPendingWithdrawalsAccessException() throws Exception {
		Storage storage = Storage.deploy(
				web3j, 
				credentials, 
				Storage.GAS_PRICE, 
				Storage.GAS_LIMIT
			).send();
		
		withdrawalsCrate = WithdrawalsCrate.deploy(
				web3j, 
				credentials, 
				WithdrawalsCrate.GAS_PRICE, 
				WithdrawalsCrate.GAS_LIMIT, 
				new Address(storage.getContractAddress()), 
				INCORRECT_WITHDRAWALS_CRATE
			).send();
		
		withdrawalsCrate.setPendingWithdrawals(
				new Address(TEST_ADDRESS), 
				new Uint256(AMOUNT_TO_WITHDRAW)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetPendingWithdrawalsIncorrectAddressException() throws Exception {
		withdrawalsCrate.setPendingWithdrawals(
				new Address(TEST_NULL_ADDRESS), 
				new Uint256(AMOUNT_TO_WITHDRAW)
			).send();
	}
	
	
	private static Bytes32 stringToBytes32(String string) {
		byte[] byteValue = string.getBytes();
		byte[] byteValueLen32 = new byte[32];
		System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
		return new Bytes32(byteValueLen32);
	}
	
}
