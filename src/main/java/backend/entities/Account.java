package backend.entities;

public class Account {
	private Integer id;
	private int userId;
	private Long number;
	private double balance;

	public Account(Integer id, int userId, Long number, double balance) {
		this.userId = userId;
		this.number = number;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public long getNumber() {
		return number;
	}

	public double getBalance() {
		return balance;
	}

	public void increaseBalance(double amount) {
		balance += amount;
	}

	public void decreaseBalance(double amount) {
		balance -= amount;
	}

	public int getUserId() {
		return userId;
	}
}
