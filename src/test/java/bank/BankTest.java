package bank;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BankTest {
	Bank bank = new Bank();

	@Test
	public void checkBankName() {
		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");
		Assertions.assertEquals("DC", account.getBankName());
	}

	@Test
	public void testCheckUserLuisRed() {
		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");
		Assertions.assertEquals("luis", account.getUserFirstName());
	}

	@Test
	public void checkPinNumberWhenNumberIsValid() {
		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");
		Assertions.assertTrue(bank.checkPinNumber("678909", account));
	}

	@Test
	public void checkPinNumberWhenNumberIsNotValid() {
		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");
		Assertions.assertFalse(bank.checkPinNumber("90000", account));
	}

	@Test
	public void bank_whenPinNumberIsNotValid() {

		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("000000").when(bank1).getPinNumber1();
		Mockito.doReturn("000000").when(bank1).getPinNumber2();
		Mockito.doReturn("000000").when(bank1).getPinNumber3();

		bank1.bank(account);
	}

	@Test
	public void bank_whenPinNumberIsValidNotFromTheFirstCheck() {

		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("0000").when(bank1).getPinNumber1();
		Mockito.doReturn("678909").when(bank1).getPinNumber2();
		Mockito.doReturn(0).when(bank1).getChoiceOperation();

		bank1.bank(account);
	}

	@Test
	public void bank_whenUserIsNotValid() {

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("0000000").when(bank1).getIdNumber();
		Mockito.doReturn("0000").when(bank1).getPinNumber1();
		Mockito.doReturn("1").when(bank1).getPinNumber2();

		bank1.bankAccess("0");
	}

	@Test
	public void bank_whenUserIsValidAndPinIsNotValid() {

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("1223465432").when(bank1).getIdNumber();
		Mockito.doReturn("0000").when(bank1).getPinNumber1();
		Mockito.doReturn("1").when(bank1).getPinNumber2();
		Mockito.doReturn("1").when(bank1).getPinNumber3();

		bank1.bankAccess("0");
	}

	@Test
	public void bank_whenPinNumberIsValid() {

		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("678909").when(bank1).getPinNumber1();
		Mockito.doReturn(0).when(bank1).getChoiceOperation();

		bank1.bank(account);
	}

	@Test
	public void bank_whenPinNumberIsValid2() {

		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");

		Bank bank = new Bank();

		Bank bank1 = Mockito.spy(bank);

		Mockito.doReturn("678909").when(bank1).getPinNumber1();
		Mockito.doReturn(0).when(bank1).getChoiceOperation();

		bank1.bank(account);
	}

}
