package bank;

import java.util.*;

public class Bank {
	static Scanner in = new Scanner(System.in);

	List<Account> accountList = new ArrayList<>();

	public static List<Account> getAccountList() {
		List<Account> accountList = new ArrayList<>();
		Account account = new Account("luis", "red", "1223465432", "678909", 1000, "DC");
		Account account1 = new Account("maris", "yellow", "2098876545", "181889", 18000, "AR");
		Account account2 = new Account("tomy", "coor", "2022202914", "161617", 14000, "STC");
		Account account3 = new Account("faty", "mtyr", "1400011112", "897654", 67990, "TRE");
		Account account4 = new Account("braham", "braham", "2023111400", "609080", 60, "UY");
		Account account5 = new Account("tarik", "tarik", "1414147890", "141489", 300, "UI");

		accountList.add(account);
		accountList.add(account1);
		accountList.add(account2);
		accountList.add(account3);
		accountList.add(account4);
		accountList.add(account5);
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public boolean checkPinNumber(String pinNumber, Account account) {
		boolean loggedIn = false;
		Optional<Account> accOptional = getAccountList().stream()
				.filter(b -> b.getUserFirstName().equals(account.getUserFirstName())).findFirst();

		if (accOptional.get().getCardPinNumber().equals(pinNumber)) {
			loggedIn = true;
			System.out.println("You are logged In");
		}
		return loggedIn;

	}

	public String getPinNumber1() {
		System.out.println("Enter the pin number");
		String pinNumber = in.nextLine();
		return pinNumber;
	}

	public String getPinNumber2() {
		System.out.println("Enter the pin number for the second time");
		String pinNumber2 = in.nextLine();
		return pinNumber2;
	}

	public String getPinNumber3() {
		System.out.println("Enter the pin number for the last time");
		String pinNumber3 = in.nextLine();
		return pinNumber3;
	}

	public int getChoiceOperation() {
		System.out.println("********************");
		System.out.println("Enter 1 to check your balance");
		System.out.println("Enter 2 to deposit");
		System.out.println("Enter 3 to withdraw");
		System.out.println("Enter 4 to end the process");
		int choice = in.nextInt();
		return choice;
	}

	public void bank(Account account) {

		System.out.println("********************");
		String pinNumber = getPinNumber1();
		int tryNumber = 1;

		if (!checkPinNumber(pinNumber, account)) {
			String pinNumber2 = getPinNumber2();
			if (!checkPinNumber(pinNumber2, account)) {
				tryNumber = 2;
			}

		}
		if (tryNumber == 2) {
			String pinNumber3 = getPinNumber3();
			if (!checkPinNumber(pinNumber3, account)) {
				tryNumber = 3;

			}
		}

		if (tryNumber == 3) {
			account.setLocked(true);
			System.out.println("You are blocked");

		} else if (!account.isLocked()) {
			bankOperation(account);

		}

	}

	public void bankOperation(Account account) {

		int choice = getChoiceOperation();
		switch (choice) {
			case 1:
				System.out.println("This is your current balance " + account.getBalance());
				bankOperation(account);
				break;

			case 2:
				System.out.println("Enter the amount to deposit");
				int depositAmount = in.nextInt();
				System.out.println("Do you want to deposit " + depositAmount + " ? (Y/N)");
				String answer = in.next();
				if (answer.equalsIgnoreCase("Y")) {
					account.setBalance(account.getBalance() + depositAmount);
					System.out.println("Deposit successful. Your current balance is " + account.getBalance());
					bankOperation(account);
				} else if (answer.equalsIgnoreCase("N")) {
					System.out.println("No deposit has been made. Your balance is " + account.getBalance());
					bankOperation(account);
				}
				else {
					System.out.println("Invalid answer");
					bankOperation(account);
				}
				break;
			case 3:
				System.out.println("Enter the amount to withdraw");
				int withdrawAmount = in.nextInt();

				if (account.getBalance() < withdrawAmount) {
					System.out.println("There is not that much money in the bank");
					bankOperation(account);
				} else if (account.getBalance() >= withdrawAmount) {
					System.out.println("Do you want to withdraw " + withdrawAmount + " ? (Y/N)");
					String answer1 = in.next();
					if (answer1.equalsIgnoreCase("Y")) {
						account.setBalance(account.getBalance() - withdrawAmount);
						System.out.println("Withdraw successful. Your current balance is " + account.getBalance());
						bankOperation(account);
					} else if (answer1.equalsIgnoreCase("N")) {
						System.out.println("No withdraw has been made. Your balance is " + account.getBalance());
						bankOperation(account);
					}
				}else {
					System.out.println("Invalid answer");
					bankOperation(account);
				}
				break;

			case 4:
				System.out.println("The process is ended in the bank" + account.getBankName());
				break;
		}

	}

	public Account checkUser(String idNumber) {
		Optional<Account> accOptional = getAccountList().stream().filter(b -> b.cardIdNumber.equals(idNumber))
				.findFirst();

		Account account = null;
		if (accOptional.isPresent()) {

			System.out.println("User : " + accOptional.get().getUserFirstName() + " " + accOptional.get().getUserLastName());
			account = accOptional.get();
			return account;

		} else {
			System.out.println("Your IdNumber is not valid ");
			return account;
		}

	}

	public String getIdNumber() {
		System.out.println("\nEnter the IdNumber Card");
		return in.nextLine();
	}

	public void bankAccess(String answer) {

		switch (answer) {
		case "0":
			String idNumber = getIdNumber();
			Account account = checkUser(idNumber);
			if (account != null) {
				bank(account);
			}
			break;
		}
	}

}
