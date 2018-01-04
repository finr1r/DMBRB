package com.dkohut.dmbrb;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple6;

import com.dkohut.dmbrb.wrappers.BookStorage;

public class BookStorageTest {
	
	private static final String MAIN_PRIVATE_KEY = "29d9b2e87e7c9e50ab355932c2aa1c2e4602523fafe0bf96ab3152b785cbf225";
	private static final String SECOND_PRIVATE_KEY = "a91d0741be7241354ea5dfbdd2c6b05289f7d8e2ccee0f8e6a7e99eae900dba6";
	private static final String BALANCE = "100000000000000000000";
	private static final String BOOK_TITLE_1 = "Simple Title 1";
	private static final String BOOK_AUTHOR_1 = "Simple Author 1";
	private static final boolean BOOK_RENTED_1 = true;
	private static final long BOOK_PRICE_1 = 5000;
	private static final String BOOK_TITLE_2 = "Simple Title 2";
	private static final String BOOK_AUTHOR_2 = "Simple Author 2";
	private static final boolean BOOK_RENTED_2 = false;
	private static final long BOOK_PRICE_2 = 4000;
	private static final byte DEFAULT_BOOK_STATUS = 4;
	private static final byte RENTED_BOOK_STATUS = 0;	// means that the book status is 'RENTED'
	private static final byte BUYED_BOOK_STATUS = 1;	// means that the book status is 'BUYED'
	private static final long NUMBER_OF_BOOKS = 2;
	private static final int PENDING_WITHDRAWALS_SUM = 228;
	private static final int DEBTORS_SUM = 322;
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
	private BookStorage bookStorage;
	
	
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
		
		bookStorage.addBook(
				new Utf8String(BOOK_TITLE_1),
				new Utf8String(BOOK_AUTHOR_1), 
				new Bool(BOOK_RENTED_1), 
				new Uint256(BOOK_PRICE_1)
			).send();
		
		bookStorage.addBook(
				new Utf8String(BOOK_TITLE_2),
				new Utf8String(BOOK_AUTHOR_2), 
				new Bool(BOOK_RENTED_2), 
				new Uint256(BOOK_PRICE_2)
			).send();
	}
	
	@Test
	public void testDeploy() throws IOException {
		assertThat(bookStorage.isValid()).as("Should be deployed and valid").isTrue();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = bookStorage.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		bookStorage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = bookStorage.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing").isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct owner address after changing").isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		bookStorage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		// must throw exception
		bookStorage.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testKill() throws Exception {
		bookStorage.kill().send();		
		
		// must throw an exception
		bookStorage.books(new Uint256(0)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		bookStorage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		// must throw an exception
		bookStorage.kill().send();
	}
	
	@Test
	public void testAddBook() throws Exception {		
		Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256> firstBook = bookStorage.books(new Uint256(0)).send();
		Tuple6<Utf8String, Utf8String, Bool, Uint256, Uint8, Uint256> secondBook = bookStorage.books(new Uint256(1)).send();
		
		assertThat(firstBook.getValue1().getValue().toString()).as("Should have correct book title").isEqualTo(BOOK_TITLE_1);
		assertThat(firstBook.getValue2().getValue().toString()).as("Should have correct book author").isEqualTo(BOOK_AUTHOR_1);
		assertThat(firstBook.getValue3().getValue().booleanValue()).as("Should have correct isRented field value").isTrue();
		assertThat(firstBook.getValue4().getValue().longValue()).as("Should have correct price").isEqualTo(BOOK_PRICE_1);
		assertThat(firstBook.getValue5().getValue().byteValue()).as("Should have correct status").isEqualTo(DEFAULT_BOOK_STATUS);
		
		assertThat(secondBook.getValue1().getValue().toString()).as("Should have correct owner address before changing").isEqualTo(BOOK_TITLE_2);
		assertThat(secondBook.getValue2().getValue().toString()).as("Should have correct book author").isEqualTo(BOOK_AUTHOR_2);
		assertThat(secondBook.getValue3().getValue().booleanValue()).as("Should have correct isRented field value").isFalse();
		assertThat(secondBook.getValue4().getValue().longValue()).as("Should have correct price").isEqualTo(BOOK_PRICE_2);
		assertThat(secondBook.getValue5().getValue().byteValue()).as("Should have correct status").isEqualTo(DEFAULT_BOOK_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddBookAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		bookStorage.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		// must throw exception
		bookStorage.addBook(
				new Utf8String(BOOK_TITLE_1),
				new Utf8String(BOOK_AUTHOR_1), 
				new Bool(BOOK_RENTED_1), 
				new Uint256(BOOK_PRICE_1)
			).send();
	}
	
	@Test
	public void testUpdateBookStatus() throws Exception {
		bookStorage.updateBookStatus(
				new Uint256(0), 
				new Uint8(RENTED_BOOK_STATUS)
			).send();
		
		bookStorage.updateBookStatus(
				new Uint256(1), 
				new Uint8(BUYED_BOOK_STATUS)
			).send();
		
		Uint8 firstBookStatus = bookStorage.getStatus(new Uint256(0)).send();
		Uint8 secondBookStatus = bookStorage.getStatus(new Uint256(1)).send();
		
		assertThat(firstBookStatus.getValue().byteValue()).as("Should have correct status").isEqualTo(RENTED_BOOK_STATUS);
		assertThat(secondBookStatus.getValue().byteValue()).as("Should have correct status").isEqualTo(BUYED_BOOK_STATUS);
	}
	
	@Test
	public void testGetSize() throws Exception {
		Uint256 sizeOfArray = bookStorage.getSize().send();
		assertThat(sizeOfArray.getValue().longValue()).as("Should have correct number of books").isEqualTo(NUMBER_OF_BOOKS);
	}
	
	@Test
	public void testGetPrice() throws Exception {
		Uint256 firstBookPrice = bookStorage.getPrice(new Uint256(0)).send();
		Uint256 secondBookPrice = bookStorage.getPrice(new Uint256(1)).send();
		
		assertThat(firstBookPrice.getValue().longValue()).as("Should have correct price").isEqualTo(BOOK_PRICE_1);
		assertThat(secondBookPrice.getValue().longValue()).as("Should have correct price").isEqualTo(BOOK_PRICE_2);
	}
	
	@Test
	public void testPendingWithdrawals() throws Exception {
		bookStorage.setPendingWithdrawals(
				new Address(credentials.getAddress()),
				new Uint256(PENDING_WITHDRAWALS_SUM)
			).send();
		
		Uint256 pendingWithdrawalSum = bookStorage.getPendingWithdrawals(new Address(credentials.getAddress())).send();
		assertThat(pendingWithdrawalSum.getValue().longValue()).as("Should have correct withdrawal sum").isEqualTo(PENDING_WITHDRAWALS_SUM);
	}
	
	@Test
	public void testDebtors() throws Exception {
		bookStorage.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBTORS_SUM)
			).send();
		
		Uint256 debtorSum = bookStorage.getDebtor(new Address(credentials.getAddress())).send();
		assertThat(debtorSum.getValue().longValue()).as("Should have correct debt sum").isEqualTo(DEBTORS_SUM);
	}
	
}
