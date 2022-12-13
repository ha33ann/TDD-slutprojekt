package bank;

public class Account {

	String userFirstName;
	String userLastName;
	String cardIdNumber;
	String cardPinNumber;
	int balance;
	boolean isLocked;
	String bankName;

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Account(String userFirstName, String userLastName, String cardIdNumber, String cardPinNumber, int balance,
			String bankName) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.cardIdNumber = cardIdNumber;
		this.cardPinNumber = cardPinNumber;
		this.balance = balance;
		this.bankName = bankName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getCardIdNumber() {
		return cardIdNumber;
	}

	public void setCardIdNumber(String cardIdNumber) {
		this.cardIdNumber = cardIdNumber;
	}

	public String getCardPinNumber() {
		return cardPinNumber;
	}

	public void setCardPinNumber(String cardPinNumber) {
		this.cardPinNumber = cardPinNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
