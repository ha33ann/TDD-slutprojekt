package bank;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BankTest {
	Bank bank = new Bank();

	@Test
	public void checkIfAccountListSizeAreNotGreaterThan10(){
		Assertions.assertTrue(bank.accountList.size() < 10);
	}

	@Test
	public void checkIfBalanceFromTomyIsCorrect(){
		Account account = new Account("tomy", "coor", "2022202914", "161617", 14000, "STC");
		Assertions.assertTrue(account.getBalance() == 14000);
	}

	@Test
	public void checkPinNumberWhenPinNumberIsValid() {
		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");
		Assertions.assertTrue(bank.checkPinNumber("678909", account));
	}

	@Test
	public void checkPinNumberWhenPinNumberIsNotValid() {
		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");
		Assertions.assertFalse(bank.checkPinNumber("90000", account));
	}

	@Test
	public void checkIfUserIsLockedAfter3InvalidPinNumbers() {
		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");
		//when all pin numbers are invalid, the user is locked
		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);
		Mockito.doReturn("000000").when(bank1).getPinNumber1();
		Mockito.doReturn("000000").when(bank1).getPinNumber2();
		Mockito.doReturn("000000").when(bank1).getPinNumber3();

		//when the user is at tryNumber 3, the user is locked
		bank1.bank(account);

		Assertions.assertTrue(account.isLocked());

		verify(bank1).getPinNumber1();
		verify(bank1).getPinNumber2();
		verify(bank1).getPinNumber3();

	}

	@Test
	public void checkIfPinNumberOnLuisIsNotValid() {

		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("000000").when(bank1).getPinNumber1();
		Mockito.doReturn("123").when(bank1).getPinNumber2();
		Mockito.doReturn("456").when(bank1).getPinNumber3();

		Assertions.assertFalse(bank1.getPinNumber1().equals(account.cardPinNumber));

		verify(bank1).getPinNumber1();

		bank1.bank(account);
	}

	@Test
	public void checkIfPinIsNotValidFromTheFirstCheck() {

		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("0000").when(bank1).getPinNumber1();
		Mockito.doReturn("678909").when(bank1).getPinNumber2();
		Mockito.doReturn(0).when(bank1).getChoiceOperation();

		Assertions.assertTrue(bank1.getPinNumber2().equals(account.cardPinNumber));
		Assertions.assertFalse(bank1.getPinNumber1().equals(account.cardPinNumber));

		verify(bank1).getPinNumber1();
		verify(bank1).getPinNumber2();

		bank1.bank(account);
	}

	@Test
	public void checkWhenUserIsNotValidButPinIsValid() {
		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");


		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("0000000").when(bank1).getIdNumber();
		Mockito.doReturn("678909").when(bank1).getPinNumber1();
		Mockito.doReturn("1").when(bank1).getPinNumber2();

		Assertions.assertFalse(bank1.getIdNumber().equals(account.cardIdNumber));
		Assertions.assertTrue(bank1.getPinNumber1().equals(account.cardPinNumber));

		verify(bank1).getPinNumber1();
		verify(bank1).getIdNumber();

		bank1.bankAccess("0");
	}

	@Test
	public void checkWhenUserIsValidButPinIsNotValid() {

		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");


		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("1223465432").when(bank1).getIdNumber();
		Mockito.doReturn("0000").when(bank1).getPinNumber1();
		Mockito.doReturn("1").when(bank1).getPinNumber2();
		Mockito.doReturn("1").when(bank1).getPinNumber3();


		Assertions.assertTrue(bank1.getIdNumber().equals(account.cardIdNumber));

		Assertions.assertFalse(bank1.getPinNumber1().equals(account.cardPinNumber));

		verify(bank1).getPinNumber1();
		verify(bank1).getIdNumber();

		bank1.bankAccess("0");
	}

	@Test
	public void checkWhenChoiceOperationIsTrue() {

		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("678909").when(bank1).getPinNumber1();
		Mockito.doReturn(0).when(bank1).getChoiceOperation();

		Assertions.assertTrue(bank1.getChoiceOperation() == 0);

		verify(bank1).getChoiceOperation();

		bank1.bank(account);
	}

	@Test
	public void checkWhenChoiceOperationIsFalse() {

		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("678909").when(bank1).getPinNumber1();
		Mockito.doReturn(0).when(bank1).getChoiceOperation();

		Assertions.assertFalse(bank1.getChoiceOperation() == 1);


		bank1.bank(account);
	}

}
