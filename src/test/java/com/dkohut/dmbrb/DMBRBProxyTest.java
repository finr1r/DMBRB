package com.dkohut.dmbrb;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.dkohut.dmbrb.wrappers.DMBRBProxy;

public class DMBRBProxyTest {

	private static final String MAIN_PRIVATE_KEY = "29d9b2e87e7c9e50ab355932c2aa1c2e4602523fafe0bf96ab3152b785cbf225";
	private static final String SECOND_PRIVATE_KEY = "a91d0741be7241354ea5dfbdd2c6b05289f7d8e2ccee0f8e6a7e99eae900dba6";
	private static final String BALANCE = "100000000000000000000";
	private static final String TEST_ADDRESS = "0x692a70d2e424a56d2c6c27aa97d1a86395877b3a";
	private static final String TEST_NULL_ADDRESS = "0x0";
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
	private DMBRBProxy dmbrbProxy;
	
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
		
		dmbrbProxy = DMBRBProxy.deploy(
				web3j, 
				credentials, 
				DMBRBProxy.GAS_PRICE, 
				DMBRBProxy.GAS_LIMIT
			).send();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = dmbrbProxy.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		dmbrbProxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = dmbrbProxy.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing")
			.isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct owner address after changing")
			.isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbProxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbProxy.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKill() throws Exception {
		dmbrbProxy.kill().send();
		Address owner = dmbrbProxy.owner().send();
		assertThat(owner.getValue().toString()).isNotNull();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbProxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbProxy.kill().send();
	}
	
	@Test
	public void testSetWithdrawalsCrateAddress() throws Exception {
		dmbrbProxy.setWithdrawalsCrateAddress(new Address(TEST_ADDRESS)).send();
		Address _address = dmbrbProxy.withdrawalsCrate().send();
		assertThat(_address.getValue().toString()).as("Should have correct address").isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetWithdrawalsCrateAddressAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbProxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbProxy.setWithdrawalsCrateAddress(new Address(TEST_ADDRESS)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetWithdrawalsCrateAddressIncorrectAddressException() throws Exception {
		dmbrbProxy.setWithdrawalsCrateAddress(new Address(TEST_NULL_ADDRESS)).send();
	}
	
	@Test
	public void testSetBooksCrateAddress() throws Exception {
		dmbrbProxy.setBooksCrateAddress(new Address(TEST_ADDRESS)).send();
		Address _address = dmbrbProxy.booksCrate().send();
		assertThat(_address.getValue().toString()).as("Should have correct address").isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetBooksCrateAddressAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbProxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbProxy.setBooksCrateAddress(new Address(TEST_ADDRESS)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetBooksCrateAddressIncorrectAddressException() throws Exception {
		dmbrbProxy.setBooksCrateAddress(new Address(TEST_NULL_ADDRESS)).send();
	}
	
	@Test
	public void testSetDebtorsCrateAddress() throws Exception {
		dmbrbProxy.setDebtorsCrateAddress(new Address(TEST_ADDRESS)).send();
		Address _address = dmbrbProxy.debtorsCrate().send();
		assertThat(_address.getValue().toString()).as("Should have correct address").isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetDebtorsCrateAddressAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbProxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbProxy.setDebtorsCrateAddress(new Address(TEST_ADDRESS)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetDebtorsCrateAddressIncorrectAddressException() throws Exception {
		dmbrbProxy.setDebtorsCrateAddress(new Address(TEST_NULL_ADDRESS)).send();
	}
	
	@Test
	public void testSetControllerAddress() throws Exception {
		dmbrbProxy.setControllerAddress(new Address(TEST_ADDRESS)).send();
		Address _address = dmbrbProxy.dmbrbController().send();
		assertThat(_address.getValue().toString()).as("Should have correct address").isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetControllerAddressAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbProxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbProxy.setControllerAddress(new Address(TEST_ADDRESS)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetControllerAddressIncorrectAddressException() throws Exception {
		dmbrbProxy.setControllerAddress(new Address(TEST_NULL_ADDRESS)).send();
	}
	
	@Test
	public void testSetStorageAddress() throws Exception {
		dmbrbProxy.setStorageAddress(new Address(TEST_ADDRESS)).send();
		Address _address = dmbrbProxy.storageAddress().send();
		assertThat(_address.getValue().toString()).as("Should have correct address").isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetStorageAddressAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbProxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbProxy.setStorageAddress(new Address(TEST_ADDRESS)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetStorageAddressIncorrectAddressException() throws Exception {
		dmbrbProxy.setStorageAddress(new Address(TEST_NULL_ADDRESS)).send();
	}
	
}
