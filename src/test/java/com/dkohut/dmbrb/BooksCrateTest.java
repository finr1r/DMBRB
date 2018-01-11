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
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple4;

import com.dkohut.dmbrb.wrappers.AccessManager;
import com.dkohut.dmbrb.wrappers.BooksCrate;
import com.dkohut.dmbrb.wrappers.Storage;
import com.dkohut.dmbrb.wrappers.WithdrawalsCrate;

public class BooksCrateTest {

	private static final String MAIN_PRIVATE_KEY = "29d9b2e87e7c9e50ab355932c2aa1c2e4602523fafe0bf96ab3152b785cbf225";
	private static final String SECOND_PRIVATE_KEY = "a91d0741be7241354ea5dfbdd2c6b05289f7d8e2ccee0f8e6a7e99eae900dba6";
	private static final String BALANCE = "100000000000000000000";
	private static final String TEST_ADDRESS = "0x692a70d2e424a56d2c6c27aa97d1a86395877b3a";
	private static final String TEST_NULL_ADDRESS = "0x0";
	private static final Bytes32 BOOKS_CRATE = stringToBytes32("WithdrawalsCrate");
	private static final Bytes32 INCORRECT_BOOKS_CRATE = stringToBytes32("Test");
	private static final Bytes32 TEST_ROLE = stringToBytes32("Controller");
	private static final Bytes32 TEST_BOOK_KEY_1 = stringToBytes32("TestKey1");
	private static final Bytes32 TEST_BOOK_TITLE_1 = stringToBytes32("Simple Title 1");
	private static final Bytes32 TEST_BOOK_AUTHOR_1 = stringToBytes32("Simple Author 1");
	private static final boolean TEST_BOOK_IS_RENTABLE_1 = true;
	private static final long TEST_BOOK_PRICE_1 = 5000L;
	private static final Bytes32 TEST_BOOK_KEY_2 = stringToBytes32("TestKey2");
	private static final Bytes32 TEST_BOOK_TITLE_2 = stringToBytes32("Simple Title 2");
	private static final Bytes32 TEST_BOOK_AUTHOR_2 = stringToBytes32("Simple Author 2");
	private static final boolean TEST_BOOK_IS_RENTABLE_2 = false;
	private static final long TEST_BOOK_PRICE_2 = 4000L;
	private static final long TEST_BOOK_STATUS = 4L;
	private static final int TEST_NEW_BOOK_STATUS = 2;
	private static final long TEST_BOOK_RENT_PERIOD = 100000L;
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
	private BooksCrate booksCrate;
	
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
		
		booksCrate = BooksCrate.deploy(
				web3j, 
				credentials, 
				BooksCrate.GAS_PRICE, 
				BooksCrate.GAS_LIMIT, 
				new Address(storage.getContractAddress()), 
				BOOKS_CRATE
			).send();
		
		storage.setManager(new Address(accessManager.getContractAddress())).send();
		booksCrate.setManager(new Address(accessManager.getContractAddress())).send();
		
		accessManager.giveAccess(
				new Address(booksCrate.getContractAddress()), 
				BOOKS_CRATE
			).send();
		
		accessManager.giveAccess(
				new Address(credentials.getAddress()), 
				TEST_ROLE
			).send();
		
		booksCrate.addBook(
				TEST_BOOK_KEY_1,
				TEST_BOOK_TITLE_1, 
				TEST_BOOK_AUTHOR_1,
				new Bool(TEST_BOOK_IS_RENTABLE_1), 
				new Uint256(TEST_BOOK_PRICE_1)
			).send();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = booksCrate.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		booksCrate.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = booksCrate.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing")
			.isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct owner address after changing")
			.isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		booksCrate.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		booksCrate.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKill() throws Exception {
		booksCrate.kill().send();
		Address owner = booksCrate.owner().send();
		assertThat(owner.getValue().toString()).isNotNull();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		booksCrate.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		booksCrate.kill().send();
	}
	
	@Test
	public void testSetManager() throws Exception {
		booksCrate.setManager(new Address(TEST_ADDRESS)).send();
		Address manager = booksCrate.manager().send();
		assertThat(manager.getValue().toString()).as("Should have correct address").isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetManagerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		booksCrate.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		booksCrate.setManager(new Address(TEST_ADDRESS)).send();
	}
	
	@Test
	public void testAddBook() throws Exception {
		booksCrate.addBook(
				TEST_BOOK_KEY_2, 
				TEST_BOOK_TITLE_2, 
				TEST_BOOK_AUTHOR_2, 
				new Bool(TEST_BOOK_IS_RENTABLE_2), 
				new Uint256(TEST_BOOK_PRICE_2)
			).send();
		
		Tuple4<StaticArray2<Bytes32>, Bool, StaticArray2<Uint256>, Address> book = booksCrate.getBook(TEST_BOOK_KEY_2).send();
		
		Bytes32 bookTitle = book.getValue1().getValue().get(0);
		Bytes32 bookAuthor = book.getValue1().getValue().get(1);
		boolean bookIsRentable = book.getValue2().getValue().booleanValue();
		long bookPrice = book.getValue3().getValue().get(0).getValue().longValue();
		long bookStatus = book.getValue3().getValue().get(1).getValue().longValue();
		String bookOwner = book.getValue4().getValue().toString();
		
		assertThat(bookTitle).as("Should have correct title").isEqualTo(TEST_BOOK_TITLE_2);
		assertThat(bookAuthor).as("Should have correct author").isEqualTo(TEST_BOOK_AUTHOR_2);
		assertThat(bookIsRentable).as("Should have correct isRentable value").isEqualTo(TEST_BOOK_IS_RENTABLE_2);
		assertThat(bookPrice).as("Should have correct price").isEqualTo(TEST_BOOK_PRICE_2);
		assertThat(bookStatus).as("Should have correct status").isEqualTo(TEST_BOOK_STATUS);
		assertThat(bookOwner).as("Should have correct owner address").isEqualTo(credentials.getAddress());
	}
	
	@Test
	public void testSetBookStatus() throws Exception {
		Uint256 statusBefore = booksCrate.getBookStatus(TEST_BOOK_KEY_1).send();
		
		booksCrate.setBookStatus(
				TEST_BOOK_KEY_1, 
				new Uint8(TEST_NEW_BOOK_STATUS)
			).send();
		Uint256 statusAfter = booksCrate.getBookStatus(TEST_BOOK_KEY_1).send();
		
		assertThat(statusBefore.getValue().longValue()).as("Should have correct status before changing")
			.isEqualTo(TEST_BOOK_STATUS);
		assertThat(statusAfter.getValue().intValue()).as("Should have correct status after changing")
			.isEqualTo(TEST_NEW_BOOK_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetBookStatusAccessException() throws Exception {
		Storage storage = Storage.deploy(
				web3j, 
				credentials, 
				Storage.GAS_PRICE, 
				Storage.GAS_LIMIT
			).send();
		
		booksCrate = BooksCrate.deploy(
				web3j, 
				credentials, 
				WithdrawalsCrate.GAS_PRICE, 
				WithdrawalsCrate.GAS_LIMIT, 
				new Address(storage.getContractAddress()), 
				INCORRECT_BOOKS_CRATE
			).send();
		
		booksCrate.setBookStatus(
				TEST_BOOK_KEY_2, 
				new Uint8(TEST_NEW_BOOK_STATUS)
			).send();
	}
	
	@Test
	public void testSetBookOwner() throws Exception {
		Address ownerBefore = booksCrate.getBookOwner(TEST_BOOK_KEY_1).send();
		
		booksCrate.setBookOwner(
				TEST_BOOK_KEY_1, 
				new Address(TEST_ADDRESS)
			).send();
		Address ownerAfter = booksCrate.getBookOwner(TEST_BOOK_KEY_1).send();
		
		assertThat(ownerBefore.getValue().toString()).as("Should have correct owner address before changing")
			.isEqualTo(credentials.getAddress());
		assertThat(ownerAfter.getValue().toString()).as("Should have correct owner address after changing")
			.isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetBookOwnerAccessException() throws Exception {
		Storage storage = Storage.deploy(
				web3j, 
				credentials, 
				Storage.GAS_PRICE, 
				Storage.GAS_LIMIT
			).send();
		
		booksCrate = BooksCrate.deploy(
				web3j, 
				credentials, 
				WithdrawalsCrate.GAS_PRICE, 
				WithdrawalsCrate.GAS_LIMIT, 
				new Address(storage.getContractAddress()), 
				INCORRECT_BOOKS_CRATE
			).send();
		
		booksCrate.setBookOwner(
				TEST_BOOK_KEY_1, 
				new Address(TEST_ADDRESS)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetBookOwnerIncorrectAddressException() throws Exception {
		booksCrate.setBookOwner(
				TEST_BOOK_KEY_1, 
				new Address(TEST_NULL_ADDRESS)
			).send();
	}
	
	@Test
	public void testSetPeriodOfBookRent() throws Exception {
		booksCrate.setPeriodOfBookRent(
				TEST_BOOK_KEY_1, 
				new Uint256(TEST_BOOK_RENT_PERIOD)
			).send();
		
		Uint256 period = booksCrate.getPeriodOfBookRent(TEST_BOOK_KEY_1).send();
		assertThat(period.getValue().longValue()).as("Should have correct rent period").isEqualTo(TEST_BOOK_RENT_PERIOD);
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetPeriodOfBookRentAccessException() throws Exception {
		Storage storage = Storage.deploy(
				web3j, 
				credentials, 
				Storage.GAS_PRICE, 
				Storage.GAS_LIMIT
			).send();
		
		booksCrate = BooksCrate.deploy(
				web3j, 
				credentials, 
				WithdrawalsCrate.GAS_PRICE, 
				WithdrawalsCrate.GAS_LIMIT, 
				new Address(storage.getContractAddress()), 
				INCORRECT_BOOKS_CRATE
			).send();
		
		booksCrate.setPeriodOfBookRent(
				TEST_BOOK_KEY_1, 
				new Uint256(TEST_BOOK_RENT_PERIOD)
			).send();
	}
	
	
	private static Bytes32 stringToBytes32(String string) {
		byte[] byteValue = string.getBytes();
		byte[] byteValueLen32 = new byte[32];
		System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
		return new Bytes32(byteValueLen32);
	}
	
}
