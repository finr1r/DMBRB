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
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.dkohut.dmbrb.wrappers.AccessManager;
import com.dkohut.dmbrb.wrappers.BooksCrate;
import com.dkohut.dmbrb.wrappers.DMBRBController;
import com.dkohut.dmbrb.wrappers.DebtorsCrate;
import com.dkohut.dmbrb.wrappers.Storage;
import com.dkohut.dmbrb.wrappers.WithdrawalsCrate;

public class DMBRBControllerTest {
	
	private static final String MAIN_PRIVATE_KEY = "29d9b2e87e7c9e50ab355932c2aa1c2e4602523fafe0bf96ab3152b785cbf225";
	private static final String SECOND_PRIVATE_KEY = "a91d0741be7241354ea5dfbdd2c6b05289f7d8e2ccee0f8e6a7e99eae900dba6";
	private static final String BALANCE = "100000000000000000000";
	private static final Bytes32 BOOKS_CRATE = stringToBytes32("BooksCrate");
	private static final Bytes32 DEBTORS_CRATE = stringToBytes32("DebtorsCrate");
	private static final Bytes32 WITHDRAWALS_CRATE = stringToBytes32("WithdrawalsCrate");
	private static final Bytes32 CONTROLLER_ROLE = stringToBytes32("Controller");
	private static final String TEST_ADDRESS = "0x692a70d2e424a56d2c6c27aa97d1a86395877b3a";
	private static final String TEST_NULL_ADDRESS = "0x0";
	private static final Bytes32 BOOK_KEY_1 = stringToBytes32("Key1");
	private static final Bytes32 BOOK_TITLE_1 = stringToBytes32("Simple Title 1");
	private static final Bytes32 BOOK_AUTHOR_1 = stringToBytes32("Simple Author 1");
	private static final boolean BOOK_IS_RENTABLE_1 = true;
	private static final long BOOK_PRICE_1 = 5000L;
	private static final Bytes32 BOOK_KEY_2 = stringToBytes32("Key2");
	private static final Bytes32 BOOK_TITLE_2 = stringToBytes32("Simple Title 2");
	private static final Bytes32 BOOK_AUTHOR_2 = stringToBytes32("Simple Author 2");
	private static final boolean BOOK_IS_RENTABLE_2 = false;
	private static final long BOOK_PRICE_2 = 4000L;
	private static final byte RENTED_BOOK_STATUS = 0;
	private static final byte BUYED_BOOK_STATUS = 1;
	private static final byte EARLY_RETURN_BOOK_STATUS = 3;
	private static final long RENT_PERIOD = 1209600;
	private static final long LOW_RENT_PERIOD = 864000;
	private static final long RENT_AMOUNT = 1000;
	private static final long DEBT_AMOUNT = 5;
	
	private static Process testrpc;
	
	private Web3j web3j;
	private Credentials credentials;
	private DMBRBController dmbrbController;
	private BooksCrate booksCrate;
	private DebtorsCrate debtorsCrate;
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
		
		booksCrate = BooksCrate.deploy(
				web3j, 
				credentials, 
				BooksCrate.GAS_PRICE, 
				BooksCrate.GAS_LIMIT, 
				new Address(storage.getContractAddress()), 
				BOOKS_CRATE
			).send();
		
		debtorsCrate = DebtorsCrate.deploy(
				web3j, 
				credentials, 
				DebtorsCrate.GAS_PRICE, 
				DebtorsCrate.GAS_LIMIT, 
				new Address(storage.getContractAddress()), 
				DEBTORS_CRATE
			).send();
		
		withdrawalsCrate = WithdrawalsCrate.deploy(
				web3j, 
				credentials, 
				WithdrawalsCrate.GAS_PRICE, 
				WithdrawalsCrate.GAS_LIMIT, 
				new Address(storage.getContractAddress()), 
				WITHDRAWALS_CRATE
			).send();
		
		dmbrbController = DMBRBController.deploy(
				web3j, 
				credentials, 
				DMBRBController.GAS_PRICE, 
				DMBRBController.GAS_LIMIT
			).send();
		
		storage.setManager(new Address(accessManager.getContractAddress())).send();
		booksCrate.setManager(new Address(accessManager.getContractAddress())).send();
		debtorsCrate.setManager(new Address(accessManager.getContractAddress())).send();
		withdrawalsCrate.setManager(new Address(accessManager.getContractAddress())).send();
		
		accessManager.giveAccess(
				new Address(booksCrate.getContractAddress()), 
				BOOKS_CRATE
			).send();
		
		accessManager.giveAccess(
				new Address(debtorsCrate.getContractAddress()), 
				DEBTORS_CRATE
			).send();
		
		accessManager.giveAccess(
				new Address(withdrawalsCrate.getContractAddress()), 
				WITHDRAWALS_CRATE
			).send();
		
		accessManager.giveAccess(
				new Address(dmbrbController.getContractAddress()), 
				CONTROLLER_ROLE
			).send();
		
		
		dmbrbController.setWithdrawalsCrate(new Address(withdrawalsCrate.getContractAddress())).send();
		dmbrbController.setBooksCrate(new Address(booksCrate.getContractAddress())).send();
		dmbrbController.setDebtorsCrate(new Address(debtorsCrate.getContractAddress())).send();
		
		booksCrate.addBook(
				BOOK_KEY_1, 
				BOOK_TITLE_1, 
				BOOK_AUTHOR_1, 
				new Bool(BOOK_IS_RENTABLE_1), 
				new Uint256(BOOK_PRICE_1)
			).send();
		
		booksCrate.addBook(
				BOOK_KEY_2, 
				BOOK_TITLE_2, 
				BOOK_AUTHOR_2, 
				new Bool(BOOK_IS_RENTABLE_2), 
				new Uint256(BOOK_PRICE_2)
			).send();
	}
	
	@Test
	public void testChangeOwner() throws Exception {
		Address owner = dmbrbController.owner().send();
		
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);		
		dmbrbController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		Address newOwner = dmbrbController.owner().send();
		
		assertThat(owner.getValue().toString()).as("Should have correct owner address before changing")
			.isEqualTo(credentials.getAddress());
		assertThat(newOwner.getValue().toString()).as("Should have correct owner address after changing")
			.isEqualTo(secondCredentials.getAddress());
	}
	
	@Test(expected=RuntimeException.class)
	public void testChangeOwnerAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbController.changeOwner(new Address(credentials.getAddress())).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKill() throws Exception {
		dmbrbController.kill().send();
		Address owner = dmbrbController.owner().send();
		assertThat(owner.getValue().toString()).isNotNull();
	}
	
	@Test(expected=RuntimeException.class)
	public void testKillAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbController.kill().send();
	}
	
	@Test
	public void testSetWithdrawalsCrate() throws Exception {
		dmbrbController.setWithdrawalsCrate(new Address(TEST_ADDRESS)).send();
		Address withdrawalsCrateAddress = dmbrbController.withdrawalsCrate().send();
		assertThat(withdrawalsCrateAddress.getValue().toString()).as("Should have correct address")
			.isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class) 
	public void testSetWithdrawalsCrateAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbController.setWithdrawalsCrate(new Address(TEST_ADDRESS)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetWithdrawalsCrateIncorrectAddressException() throws Exception {
		dmbrbController.setWithdrawalsCrate(new Address(TEST_NULL_ADDRESS)).send();
	}
	
	@Test
	public void testSetBooksCrate() throws Exception {
		dmbrbController.setBooksCrate(new Address(TEST_ADDRESS)).send();
		Address booksCrateAddress = dmbrbController.booksCrate().send();
		assertThat(booksCrateAddress.getValue().toString()).as("Should have correct address")
			.isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class) 
	public void testSetBooksCrateAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbController.setBooksCrate(new Address(TEST_ADDRESS)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetBooksCrateIncorrectAddressException() throws Exception {
		dmbrbController.setBooksCrate(new Address(TEST_NULL_ADDRESS)).send();
	}
	
	@Test
	public void testSetDebtorsCrate() throws Exception {
		dmbrbController.setDebtorsCrate(new Address(TEST_ADDRESS)).send();
		Address deborsCrateAddress = dmbrbController.debtorsCrate().send();
		assertThat(deborsCrateAddress.getValue().toString()).as("Should have correct address")
			.isEqualTo(TEST_ADDRESS);
	}
	
	@Test(expected=RuntimeException.class) 
	public void testSetDebtorsCrateAccessException() throws Exception {
		Credentials secondCredentials = Credentials.create(SECOND_PRIVATE_KEY);
		dmbrbController.changeOwner(new Address(secondCredentials.getAddress())).send();
		
		dmbrbController.setDebtorsCrate(new Address(TEST_ADDRESS)).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testSetDebtorsCrateIncorrectAddressException() throws Exception {
		dmbrbController.setDebtorsCrate(new Address(TEST_NULL_ADDRESS)).send();
	}
	
	@Test
	public void testBuyBook() throws Exception {
		dmbrbController.buyBook(
				BOOK_KEY_1, 
				BigInteger.valueOf(BOOK_PRICE_1)
			).send();
		
		Uint256 bookStatus = booksCrate.getBookStatus(BOOK_KEY_1).send();
		assertThat(bookStatus.getValue().byteValue()).as("Should have status 'BUYED'").isEqualTo(BUYED_BOOK_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testBuyBookSenderIsDebtorException() throws Exception {
		debtorsCrate.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBT_AMOUNT)
			).send();

		dmbrbController.buyBook(
				BOOK_KEY_1, 
				BigInteger.valueOf(BOOK_PRICE_1)
			).send();
	}

	
	@Test(expected=RuntimeException.class)
	public void testBuyBookIsNotAvailabelException() throws Exception {
		booksCrate.setBookStatus(
				BOOK_KEY_1, 
				new Uint8(RENTED_BOOK_STATUS)
			).send();

		dmbrbController.buyBook(
				BOOK_KEY_1, 
				BigInteger.valueOf(BOOK_PRICE_1)
			).send();
	}
	
	@Test
	public void testRentBook() throws Exception {
		dmbrbController.rentBook(
				BOOK_KEY_1, 
				new Uint256(RENT_PERIOD),
				BigInteger.valueOf(1000)
			).send();
		
		Uint256 bookStatus = booksCrate.getBookStatus(BOOK_KEY_1).send();
		assertThat(bookStatus.getValue().byteValue()).as("Should have status 'RENTED'").isEqualTo(RENTED_BOOK_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentBookSenderIsDebtorException() throws Exception {
		debtorsCrate.setDebtor(
				new Address(credentials.getAddress()), 
				new Uint256(DEBT_AMOUNT)
			).send();

		dmbrbController.rentBook(
				BOOK_KEY_1, 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentBookIsNotAvailabelException() throws Exception {
		booksCrate.setBookStatus(
				BOOK_KEY_1, 
				new Uint8(BUYED_BOOK_STATUS)
			).send();

		dmbrbController.rentBook(
				BOOK_KEY_1, 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentBookCannotBeRentedException() throws Exception {
		dmbrbController.rentBook(
				BOOK_KEY_2, 
				new Uint256(RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testRentBookLowRentPeriodException() throws Exception {
		dmbrbController.rentBook(
				BOOK_KEY_1, 
				new Uint256(LOW_RENT_PERIOD), 
				BigInteger.valueOf(RENT_AMOUNT)
			).send();
	}
	
	@Test
	public void testBookEarlyReturn() throws Exception {
		dmbrbController.rentBook(
				BOOK_KEY_1, 
				new Uint256(RENT_PERIOD),
				BigInteger.valueOf(1000)
			).send();
		
		dmbrbController.bookEarlyReturn(BOOK_KEY_1).send();
		
		Uint256 bookStatus = booksCrate.getBookStatus(BOOK_KEY_1).send();
		assertThat(bookStatus.getValue().byteValue()).as("Should have status 'EARLY_RETURN'")
			.isEqualTo(EARLY_RETURN_BOOK_STATUS);
	}
	
	@Test(expected=RuntimeException.class)
	public void testBookEarlyReturnBookIsNotRentedException() throws Exception {
		dmbrbController.bookEarlyReturn(BOOK_KEY_1).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testBookReturnIsNotRentedException() throws Exception {
		dmbrbController.bookReturn(BOOK_KEY_1).send();
	}
	
	@Test(expected=RuntimeException.class)
	public void testPayDebtSenderIsNotDebtorException() throws Exception {
		dmbrbController.payDebt(BigInteger.valueOf(DEBT_AMOUNT)).send();
	}
	
	
	private static Bytes32 stringToBytes32(String string) {
		byte[] byteValue = string.getBytes();
		byte[] byteValueLen32 = new byte[32];
		System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
		return new Bytes32(byteValueLen32);
	}
	
}
