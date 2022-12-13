package bank;

import java.util.Optional;
import java.util.Scanner;

public class BankMain {

	static Bank bank = new Bank();

	static Scanner in = new Scanner(System.in);

	public static String getAnswer() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	public static void main(String[] args) {
		System.out.println("\nEnter 0 to enter your card. ");
		String answer = getAnswer();
		bank.bankAccess(answer);

	}

}
