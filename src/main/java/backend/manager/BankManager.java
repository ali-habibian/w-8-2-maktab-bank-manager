package backend.manager;

import backend.dao.AccountDao;
import backend.dao.TransactionDao;
import backend.dao.UserDao;
import backend.entities.Account;
import backend.entities.Transaction;
import backend.entities.User;
import backend.exceptions.DatabaseException;
import backend.exceptions.ManagerException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class BankManager {
    private UserDao userDao;
    private AccountDao accountDao;
    private TransactionDao transactionDao;

    public BankManager() {
        userDao = new UserDao();
        accountDao = new AccountDao();
        transactionDao = new TransactionDao();
    }

    public void registerUser(String name) throws ManagerException {
        User user = new User(null, name);
        try {
            userDao.registerUser(user);
        } catch (DatabaseException e) {
            e.printStackTrace();
            throw new ManagerException(String.format("Can not add user to database.%n Error: %s", e.getMessage()));
        }
    }

    public void addAccount(int userId, double balance) throws ManagerException, SQLIntegrityConstraintViolationException {

        if (balance <= 0)
            throw new ManagerException(String.format("Can not add account.%n Error: %s", "Balance is invalid."));

        Account account = new Account(null, userId, null, balance);
        try {
            accountDao.addAccount(account);
        } catch (DatabaseException e) {
            e.printStackTrace();
            throw new ManagerException(String.format("Can not add account to database.%n Error: %s", e.getMessage()));
        }
    }

    public void transferMoney(long sourceNumber, long destinationNumber, double amount) throws ManagerException {
        try {

            if (amount > accountDao.getBalanceByAccountNumber(sourceNumber))
                throw new ManagerException(
                        String.format("Can not transfer money.%n Error: %s", "Balance is not enough"));

            accountDao.transferMoney(sourceNumber, destinationNumber, amount);
            transactionDao.addNewTransaction(sourceNumber, destinationNumber, amount);

        } catch (DatabaseException e) {
            e.printStackTrace();
            throw new ManagerException(String.format("Can not transfer money.%n Error: %s", e.getMessage()));
        }
    }

    public void showTransactionsByUserID(int userID) throws ManagerException {
        ArrayList<Transaction> transactions;
        try {
            transactions = transactionDao.showTransactionsByUserID(userID);
            if (!transactions.isEmpty()) {
                for (Transaction transaction :
                        transactions) {
                    System.out.println("id: " + transaction.getId() +
                            " Time: " + transaction.getTime() +
                            " source account number: " + transaction.getWithdrawNumber() +
                            " amount: " + transaction.getAmount());
                }
            } else
                System.out.println("No transaction started from this.");
        } catch (DatabaseException e) {
            e.printStackTrace();
            throw new ManagerException(String.format("Can not show transaction.\n Error: %s", e.getMessage()));
        }
    }

    public void showTransactionsByAccountNumber(long accountNumber) throws ManagerException {
        ArrayList<Transaction> transactions;
        try {
            transactions = transactionDao.showTransactionsByAccountNumber(accountNumber);
            if (!transactions.isEmpty()) {
                for (Transaction transaction :
                        transactions) {
                    System.out.println("id: " + transaction.getId() +
                            " Time: " + transaction.getTime() +
                            " source account number: " + transaction.getWithdrawNumber() +
                            " amount: " + transaction.getAmount());
                }
            } else
                System.out.println("No transaction.");
        } catch (DatabaseException e) {
            e.printStackTrace();
            throw new ManagerException(String.format("Can not show transaction.\n Error: %s", e.getMessage()));
        }
    }
}
