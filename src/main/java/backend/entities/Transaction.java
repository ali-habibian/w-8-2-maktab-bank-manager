package backend.entities;

import java.sql.Time;

public class Transaction {
	private int id;
	private double amount;
	private Time time;
	private Long withdrawNumber;
	private Long depositNumber;

	public Transaction(int id, double amount, Time time, Long withdrawNumber, Long depositNumber) {
		this.id = id;
		this.amount = amount;
		this.time = time;
		this.withdrawNumber = withdrawNumber;
		this.depositNumber = depositNumber;
	}

	public int getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public Time getTime() {
		return time;
	}

	public Long getWithdrawNumber() {
		return withdrawNumber;
	}

	public Long getDepositNumber() {
		return depositNumber;
	}

}
