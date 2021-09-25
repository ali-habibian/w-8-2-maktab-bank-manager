package frontend;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import backend.exceptions.ManagerException;
import backend.manager.BankManager;

public class UIManager {
	private BankManager bankManager;
	private Scanner input;

	public UIManager() {
		bankManager = new BankManager();
		input = new Scanner(System.in);
	}

	private void showMenu() {
		System.out.println("1- register user");
		System.out.println("2- add account for user");
		System.out.println("3- transfer money");
		System.out.println("4- show transactions by user id");
		System.out.println("5- show transactions by account number");
		System.out.println("6- exit");
		System.out.println("enter your value");
	}

	public void run() {
		while (true) {


			showMenu();

			int select = input.nextInt();
			if (select == 1) {
				System.out.println("Enter user name: ");
				String name = input.next();
				try {
					bankManager.registerUser(name);
				} catch (ManagerException e) {
					System.out.println(e.getMessage());
				}
			}

			if (select == 2) {
				System.out.println("Enter user id: ");
				int id = input.nextInt();
				System.out.println("Enter balance: ");
				double balance = input.nextDouble();
				try {
					bankManager.addAccount(id, balance);
				} catch (ManagerException | SQLIntegrityConstraintViolationException e) {
					System.out.println(e.getMessage());
				}
			}

			if (select == 3) {
				System.out.println("Enter source account number: ");
				long sourceAccount = input.nextLong();

				System.out.println("Enter destination account number: ");
				long destinationAccount = input.nextLong();

				System.out.println("Enter amount: ");
				double amount = input.nextDouble();

				try {
					bankManager.transferMoney(sourceAccount, destinationAccount, amount);
				} catch (ManagerException e) {
					System.out.println(e.getMessage());
				}
			}

			if (select == 4) {
				System.out.println("Enter user id: ");
				int id = input.nextInt();
				try {
					bankManager.showTransactionsByUserID(id);
				} catch (ManagerException e) {
					System.out.println(e.getMessage());
				}
			}

			if (select == 5) {
				System.out.println("Enter account number: ");
				long sourceAccount = input.nextLong();
				try {
					bankManager.showTransactionsByAccountNumber(sourceAccount);
				} catch (ManagerException e) {
					System.out.println(e.getMessage());
				}
			}

			if (select == 6)
				break;
		}
	}
}
