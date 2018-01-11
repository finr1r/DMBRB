package com.dkohut.dmbrb;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Int256;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.dkohut.dmbrb.wrappers.AccessManager;
import com.dkohut.dmbrb.wrappers.Storage;

public class StorageTest {

	private static final String MAIN_PRIVATE_KEY = "29d9b2e87e7c9e50ab355932c2aa1c2e4602523fafe0bf96ab3152b785cbf225";
	private static final String SECOND_PRIVATE_KEY = "a91d0741be7241354ea5dfbdd2c6b05289f7d8e2ccee0f8e6a7e99eae900dba6";
	private static final String BALANCE = "100000000000000000000";
	private static final String TEST_ADDRESS = "0x692a70d2e424a56d2c6c27aa97d1a86395877b3a";
	private static final Integer TEST_UINT_VALUE = 50;
	private static final Integer TEST_INT_VALUE = -50;
	private static final Boolean TEST_BOOL_VALUE = true;
	private static final Bytes32 TEST_KEY = stringToBytes32("key");
	private static final Bytes32 TEST_CRATE = stringToBytes32("TestCrate");
	private static final Bytes32 TEST_INCORRECT_CRATE = stringToBytes32("IncorrectCrate");
	private static final Bytes32 TEST_BYTES32_VALUE = stringToBytes32("TestValue");
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
	private Storage storage;
	
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
		
		storage = Storage.deploy(
				web3j, 
				credentials, 
				Storage.GAS_PRICE, 
				Storage.GAS_LIMIT
			).send();
		
		AccessManager accessManager = AccessManager.deploy(
				web3j, 
				credentials, 
				AccessManager.GAS_PRICE, 
				AccessManager.GAS_LIMIT
			).send();
		
		storage.setManager(new Address(accessManager.getContractAddress())).send();
		
		accessManager.giveAccess(
				new Address(credentials.getAddress()), 
				TEST_CRATE
			).send();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = storage.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		storage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = storage.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing")
			.isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct owner address after changing")
			.isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		storage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		storage.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test
	public void testSetManager() throws Exception {		
		storage.setManager(new Address(TEST_ADDRESS)).send();		
		Address manager = storage.manager().send();
		assertThat(manager.getValue().toString()).as("Should have correct address").isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetManagerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		storage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		storage.setManager(new Address(TEST_ADDRESS)).send();
	}
	
	@Test
	public void testSetUInt() throws Exception {
		storage.setUInt(TEST_CRATE, TEST_KEY, new Uint256(TEST_UINT_VALUE)).send();
		Uint256 value = storage.getUInt(TEST_CRATE, TEST_KEY).send();
		assertThat(value.getValue().intValue()).as("Should have correct value").isEqualTo(TEST_UINT_VALUE);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetUIntIncorrectCrateException() throws Exception {
		storage.setUInt(TEST_INCORRECT_CRATE, TEST_KEY, new Uint256(TEST_UINT_VALUE)).send();
	}
	
	@Test
	public void testSetInt() throws Exception {
		storage.setInt(TEST_CRATE, TEST_KEY, new Int256(TEST_INT_VALUE)).send();
		Int256 value = storage.getInt(TEST_CRATE, TEST_KEY).send();
		assertThat(value.getValue().intValue()).as("Should have correct value").isEqualTo(TEST_INT_VALUE);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetIntIncorrectCrateException() throws Exception {
		storage.setInt(TEST_INCORRECT_CRATE, TEST_KEY, new Int256(TEST_INT_VALUE)).send();
	}
	
	@Test
	public void testSetAddress() throws Exception {
		storage.setAddress(TEST_CRATE, TEST_KEY, new Address(TEST_ADDRESS)).send();
		Address value = storage.getAddress(TEST_CRATE, TEST_KEY).send();
		assertThat(value.getValue().toString()).as("Should have correct value").isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetAddressIncorrectCrateException() throws Exception {
		storage.setAddress(TEST_INCORRECT_CRATE, TEST_KEY, new Address(TEST_ADDRESS)).send();
	}
	
	@Test
	public void testSetBool() throws Exception {
		storage.setBool(TEST_CRATE, TEST_KEY, new Bool(TEST_BOOL_VALUE)).send();
		Bool value = storage.getBool(TEST_CRATE, TEST_KEY).send();
		assertThat(value.getValue().booleanValue()).as("Should have correct value").isTrue();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetBoolIncorrectCrateException() throws Exception {
		storage.setBool(TEST_INCORRECT_CRATE, TEST_KEY, new Bool(TEST_BOOL_VALUE)).send();
	}
	
	@Test
	public void testSetBytes32() throws Exception {
		storage.setBytes32(TEST_CRATE, TEST_KEY, TEST_BYTES32_VALUE).send();
		Bytes32 value = storage.getBytes32(TEST_CRATE, TEST_KEY).send();
		assertThat(value).as("Should have correct value").isEqualTo(TEST_BYTES32_VALUE);		
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetBytes32IncorrectCrateException() throws Exception {
		storage.setBytes32(TEST_INCORRECT_CRATE, TEST_KEY, TEST_BYTES32_VALUE).send();
	}
	
	
	private static Bytes32 stringToBytes32(String string) {
		byte[] byteValue = string.getBytes();
		byte[] byteValueLen32 = new byte[32];
		System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
		return new Bytes32(byteValueLen32);
	}
	
}
