package com.dkohut.dmbrb;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigInteger;

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

import com.dkohut.dmbrb.wrappers.BookController;
import com.dkohut.dmbrb.wrappers.BookStorage;

public class BookControllerTest {

	private static final String MAIN_PRIVATE_KEY = "29d9b2e87e7c9e50ab355932c2aa1c2e4602523fafe0bf96ab3152b785cbf225";
	private static final String SECOND_PRIVATE_KEY = "a91d0741be7241354ea5dfbdd2c6b05289f7d8e2ccee0f8e6a7e99eae900dba6";
	private static final String BALANCE = "1000000000000000000000";
	private static final long BOOK_INDEX_1 = 0;
	private static final String BOOK_TITLE_1 = "Simple Title 1";
	private static final String BOOK_AUTHOR_1 = "Simple Author 1";
	private static final boolean BOOK_RENTED_1 = true;
	private static final long BOOK_PRICE_1 = 5000;
	private static final long BOOK_INDEX_2 = 1;
	private static final String BOOK_TITLE_2 = "Simple Title 2";
	private static final String BOOK_AUTHOR_2 = "Simple Author 2";
	private static final boolean BOOK_RENTED_2 = false;
	private static final long BOOK_PRICE_2 = 4000;
	private static final byte RENTED_BOOK_STATUS = 0;
	private static final byte BUYED_BOOK_STATUS = 1;
	private static final byte RETURNED_BOOK_STATUS = 2;
	private static final byte EARLY_RETURN_BOOK_STATUS = 3;
	private static final long RENT_PERIOD = 1209600;
	private static final long LOW_RENT_PERIOD = 864000;
	private static final long RENT_AMOUNT = 1000;
	private static final long DEBT_AMOUNT = 5;
	private static final long LEFT_AMOUNT = 0;
	private static final long NON_EXISTING_BOOK_INDEX = 2;
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
	private BookController bookController;
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
		
		bookController = BookController.deploy(
				web3j, 
				credentials, 
				BookController.GAS_PRICE, 
				BookController.GAS_LIMIT, 
				new Address(bookStorage.getContractAddress())
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
	public void testChangeOwner() throws Exception {
		Address owner = bookController.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		bookController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = bookController.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing")
			.isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct owner address after changing")
			.isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		bookController.changeOwner(new Address(secondCredentials.getAddress())).send();

		bookController.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=NullPointerException.class)
	public void testKill() throws Exception {
		bookController.kill().send();
		
		Address bookStorageAddress = bookController.bookStorage().send();
		assertThat(bookStorageAddress.getValue().toString()).isNull();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		bookController.changeOwner(new Address(secondCredentials.getAddress())).send();

		bookController.kill().send();
	}
	
	@Test
	public void testChangeBookStorage() throws Exception {
		Address bookStorageAddress = bookController.bookStorage().send();
		
		BookStorage secondBookStorage = BookStorage.deploy(
				web3j, 
				credentials, 
				BookStorage.GAS_PRICE, 
				BookStorage.GAS_LIMIT
			).send();		
		
		bookController.changeBookStorage(new Address(secondBookStorage.getContractAddress())).send();
		Address newBookStorageAddress = bookController.bookStorage().send();
		
		assertThat(bookStorageAddress.getValue().toString()).as("Should have correct address before changing")
			.isEqualTo(bookStorage.getContractAddress());
		assertThat(newBookStorageAddress.getValue().toString()).as("Should have correct address after changing")
			.isEqualTo(secondBookStorage.getContractAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeBookStorageAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		bookController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		BookStorage secondBookStorage = BookStorage.deploy(
				web3j, 
				credentials, 
				BookStorage.GAS_PRICE, 
				BookStorage.GAS_LIMIT
			).send();

		bookController.changeBookStorage(new Address(secondBookStorage.getContractAddress())).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeBookStorageIncorrectAddressException() throws Exception {
		bookController.changeBookStorage(new Address("0x0")).send();
	}
	
	@Test
	public void testBuyBook() throws Exception {
		bookController.buyBook(
				new Uint256(BOOK_INDEX_1), 
				BigInteger.valueOf(BOOK_PRICE_1)
			).send();
		
		Uint8 bookStatus = bookStorage.getStatus(new Uint256(0)).send();
		assertThat(bookStatus.getValue().byteValue()).as("Should have status 'BUYED'").isEqualTo(BUYED_BOOK_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testBuyBookSenderIsDebtorException() throws Exception {
		bookStorage.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBT_AMOUNT)
			).send();

		bookController.buyBook(
				new Uint256(BOOK_INDEX_1), 
				BigInteger.valueOf(BOOK_PRICE_1)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBuyBookIsNotExistsException() throws Exception {
		bookController.buyBook(
				new Uint256(NON_EXISTING_BOOK_INDEX), 
				BigInteger.valueOf(BOOK_PRICE_1)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBuyBookIsNotAvailabelException() throws Exception {
		bookStorage.updateBookStatus(
				new Uint256(BOOK_INDEX_1), 
				new Uint8(RENTED_BOOK_STATUS)
			).send();

		bookController.buyBook(
				new Uint256(BOOK_INDEX_1), 
				BigInteger.valueOf(BOOK_PRICE_1)
			).send();
	}
	
	@Test
	public void testRentBook() throws Exception {
		bookController.rentBook(
				new Uint256(BOOK_INDEX_1), 
				new Uint256(RENT_PERIOD),
				BigInteger.valueOf(1000)
			).send();
		
		Uint8 bookStatus = bookStorage.getStatus(new Uint256(0)).send();
		assertThat(bookStatus.getValue().byteValue()).as("Should have status 'RENTED'").isEqualTo(RENTED_BOOK_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentBookSenderIsDebtorException() throws Exception {
		bookStorage.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBT_AMOUNT)
			).send();

		bookController.rentBook(
				new Uint256(BOOK_INDEX_1), 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentBookBookIsNotExistsException() throws Exception {
		bookController.rentBook(
				new Uint256(NON_EXISTING_BOOK_INDEX), 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentBookIsNotAvailabelException() throws Exception {
		bookStorage.updateBookStatus(
				new Uint256(BOOK_INDEX_1), 
				new Uint8(BUYED_BOOK_STATUS)
			).send();

		bookController.rentBook(
				new Uint256(BOOK_INDEX_1), 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentBookCannotBeRentedException() throws Exception {
		bookController.rentBook(
				new Uint256(BOOK_INDEX_2), 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentBookLowRentPeriodException() throws Exception {
		bookController.rentBook(
				new Uint256(BOOK_INDEX_1), 
				new Uint256(LOW_RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test
	public void testBookEarlyReturn() throws Exception {
		bookController.rentBook(
				new Uint256(BOOK_INDEX_1), 
				new Uint256(RENT_PERIOD),
				BigInteger.valueOf(1000)
			).send();
		
		bookController.bookEarlyReturn(new Uint256(0)).send();
		
		Uint8 bookStatus = bookStorage.getStatus(new Uint256(0)).send();
		assertThat(bookStatus.getValue().byteValue()).as("Should have status 'EARLY_RETURN'").isEqualTo(EARLY_RETURN_BOOK_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testBookEarlyReturnBookIsNotExistsException() throws Exception {
		bookController.bookEarlyReturn(new Uint256(NON_EXISTING_BOOK_INDEX)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBookEarlyReturnBookIsNotRentedException() throws Exception {
		bookController.bookEarlyReturn(new Uint256(BOOK_INDEX_1)).send();
	}
	
	@Test
	public void testBookReturn() throws Exception {
		bookStorage.updateBookStatus(
				new Uint256(BOOK_INDEX_1), 
				new Uint8(RENTED_BOOK_STATUS)
			).send();
		
		bookStorage.setLastRentDay(
				new Uint256(BOOK_INDEX_1), 
				new Uint256(0)
			).send();
		
		bookController.bookReturn(new Uint256(0)).send();
		
		Uint8 bookStatus = bookStorage.getStatus(new Uint256(0)).send();
		assertThat(bookStatus.getValue().byteValue()).as("Should have status 'RETURNED'").isEqualTo(RETURNED_BOOK_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testBookReturnIsNotExistsException() throws Exception {
		bookController.bookReturn(new Uint256(NON_EXISTING_BOOK_INDEX)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBookReturnIsNotRentedException() throws Exception {
		bookController.bookReturn(new Uint256(BOOK_INDEX_1)).send();
	}
	
	@Test
	public void testPayDebt() throws Exception {
		bookStorage.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBT_AMOUNT)
			).send();
		
		Uint256 debtAmount = bookStorage.getDebtor(new Address(credentials.getAddress())).send();
		bookController.payDebt(BigInteger.valueOf(DEBT_AMOUNT)).send();
		
		Uint256 leftDebtAmount = bookStorage.getDebtor(new Address(credentials.getAddress())).send();
		
		assertThat(debtAmount.getValue().longValue()).as("Should have a debt before transaction").isEqualTo(DEBT_AMOUNT);
		assertThat(leftDebtAmount.getValue().longValue()).as("Should have zero sum of debt").isEqualTo(LEFT_AMOUNT);
	}
	
	@Test(expected=RuntimeException.class)
	public void testPayDebtSenderIsNotDebtorException() throws Exception {
		bookController.payDebt(BigInteger.valueOf(DEBT_AMOUNT)).send();
	}
	
}
