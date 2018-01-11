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
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.dkohut.dmbrb.wrappers.AccessManager;

public class AccessManagerTest {

	private static final String MAIN_PRIVATE_KEY = "29d9b2e87e7c9e50ab355932c2aa1c2e4602523fafe0bf96ab3152b785cbf225";
	private static final String SECOND_PRIVATE_KEY = "a91d0741be7241354ea5dfbdd2c6b05289f7d8e2ccee0f8e6a7e99eae900dba6";
	private static final String BALANCE = "100000000000000000000";
	private static final String TEST_ADDRESS_1 = "0x692a70d2e424a56d2c6c27aa97d1a86395877b3a";
	private static final String TEST_ADDRESS_2 = "0xbbf289d846208c16edc8474705c748aff07732db"; 
	private static final String TEST_NULL_ADDRESS = "0x0";
	private static final String TEST_ROLE = "Role"; 
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
	private AccessManager accessManager;
	
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
		
		accessManager = AccessManager.deploy(
				web3j, 
				credentials, 
				AccessManager.GAS_PRICE, 
				AccessManager.GAS_LIMIT
			).send();
		
		accessManager.giveAccess(new Address(TEST_ADDRESS_1), stringToBytes32(TEST_ROLE)).send();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = accessManager.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		accessManager.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = accessManager.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing")
			.isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct owner address after changing")
			.isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		accessManager.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		accessManager.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test
	public void testGiveAccess() throws Exception {
		accessManager.giveAccess(
				new Address(TEST_ADDRESS_2), 
				stringToBytes32(TEST_ROLE)
			).send();
		
		Bool isApproved = accessManager.isAllowed(
				new Address(TEST_ADDRESS_2), 
				stringToBytes32(TEST_ROLE)
			).send();
		
		assertThat(isApproved.getValue().booleanValue()).as("Is approved").isTrue();
	}
	
	@Test(expected=RuntimeException.class)
	public void testGiveAccessAddressIsNullException() throws Exception {
		accessManager.giveAccess(
				new Address(TEST_NULL_ADDRESS), 
				stringToBytes32(TEST_ROLE)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testGiveAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		accessManager.changeOwner(new Address(secondCredentials.getAddress())).send();		
		accessManager.giveAccess(
				new Address(TEST_ADDRESS_1), 
				stringToBytes32(TEST_ROLE)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testGiveAccessAlreadyApprovedException() throws Exception {
		accessManager.giveAccess(
				new Address(TEST_ADDRESS_1), 
				stringToBytes32(TEST_ROLE)
			).send();
	}
	
	@Test
	public void testBlockAccess() throws Exception {
		accessManager.blockAccess(
				new Address(TEST_ADDRESS_1), 
				stringToBytes32(TEST_ROLE)
			).send();
		
		Bool isApproved = accessManager.isAllowed(new Address(TEST_ADDRESS_1), stringToBytes32(TEST_ROLE)).send();		
		assertThat(isApproved.getValue().booleanValue()).as("Is not approved").isFalse();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBlockAddressIsNullException() throws Exception {
		accessManager.blockAccess(
				new Address(TEST_NULL_ADDRESS), 
				stringToBytes32(TEST_ROLE)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBlockAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		accessManager.changeOwner(new Address(secondCredentials.getAddress())).send();		
		accessManager.blockAccess(
				new Address(TEST_ADDRESS_1), 
				stringToBytes32(TEST_ROLE)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBlockAccessAlreadyBlockedException() throws Exception {
		accessManager.blockAccess(
				new Address(TEST_ADDRESS_1), 
				stringToBytes32(TEST_ROLE)
			).send();
		
		accessManager.blockAccess(
				new Address(TEST_ADDRESS_1), 
				stringToBytes32(TEST_ROLE)
			).send();
	}
	
	
	private static Bytes32 stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return new Bytes32(byteValueLen32);
	}
	
}
