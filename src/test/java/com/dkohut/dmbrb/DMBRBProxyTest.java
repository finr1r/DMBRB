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

import com.dkohut.dmbrb.wrappers.BookController;
import com.dkohut.dmbrb.wrappers.BookStorage;
import com.dkohut.dmbrb.wrappers.DMBRBProxy;

public class DMBRBProxyTest {

	private static final String MAIN_PRIVATE_KEY = "29d9b2e87e7c9e50ab355932c2aa1c2e4602523fafe0bf96ab3152b785cbf225";
	private static final String SECOND_PRIVATE_KEY = "a91d0741be7241354ea5dfbdd2c6b05289f7d8e2ccee0f8e6a7e99eae900dba6";
	private static final String BALANCE = "1000000000000000000000";
	
	private static Process testrpc;
	private static String secondContractAddress;
	
	private Web3j web3j;
	private Credentials credentials;
	private DMBRBProxy proxy;
	private BookStorage bookStorage;
	private BookController bookController;
	
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
		
		bookStorage = BookStorage.deploy(
				web3j, 
				credentials, 
				BookStorage.GAS_PRICE, 
				BookStorage.GAS_LIMIT
			).send();
		
		bookController = BookController.deploy(
				web3j, 
				credentials, 
				BookController.GAS_PRICE, 
				BookController.GAS_LIMIT,
				new Address(bookStorage.getContractAddress())
			).send();
		
		proxy = DMBRBProxy.deploy(
				web3j, 
				credentials, 
				DMBRBProxy.GAS_PRICE,
				DMBRBProxy.GAS_LIMIT
			).send();
		
		secondContractAddress = BookController.deploy(
				web3j, 
				credentials, 
				BookController.GAS_PRICE, 
				BookController.GAS_LIMIT, 
				new Address(bookStorage.getContractAddress())
			).send().getContractAddress();
		
		proxy.updateContractAddress(new Address(bookController.getContractAddress())).send();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = proxy.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		proxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = proxy.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct address").isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct address").isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		proxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		proxy.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=NullPointerException.class)
	public void testKill() throws Exception {
		proxy.kill().send();
		
		Address contract = proxy.activeContract().send();
		assertThat(contract.getValue().toString()).isNullOrEmpty();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		proxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		proxy.kill().send();
	}
	
	@Test
	public void testUpdateContractAddress() throws Exception {
		Address contractAddress = proxy.activeContract().send();		
		
		proxy.updateContractAddress(new Address(secondContractAddress)).send();
		
		Address newContractAddress = proxy.activeContract().send();
		
		assertThat(contractAddress.getValue().toString()).as("Should have correct contract address")
			.isEqualTo(bookController.getContractAddress());
		assertThat(newContractAddress.getValue().toString()).as("Should have correct contract address")
			.isEqualTo(newContractAddress.getValue().toString());
	}
	
	@Test(expected=RuntimeException.class)
	public void testUpdateContractAddressAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		proxy.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		proxy.updateContractAddress(new Address(secondContractAddress)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testUpdateContractAddressIncorrectAddressException() throws Exception {
		proxy.updateContractAddress(new Address("0x0")).send();
	}
	
}
