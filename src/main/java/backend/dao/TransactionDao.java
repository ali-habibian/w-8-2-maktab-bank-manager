package backend.dao;


import backend.entities.Account;
import backend.entities.Transaction;
import backend.exceptions.DatabaseException;
import backend.utilities.Constants;

import java.sql.*;
import java.util.ArrayList;

public class TransactionDao {

    public void addNewTransaction(long sourceNumber, long destinationNumber, double amount) throws DatabaseException {

        try (
                Connection connection = DbConnection.createConnection();
                PreparedStatement ps = connection.prepareStatement(Constants.ADD_TRANSACTION_QUERY);
        ) {
            ps.setDouble(1, amount);
            ps.setTime(2, new Time(System.currentTimeMillis()));
            ps.setLong(3, sourceNumber);
            ps.setLong(4, destinationNumber);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Can not add transaction. \n Error: " + e.getMessage());
        }
    }

    public ArrayList<Transaction> showTransactionsByUserID(int userID) throws DatabaseException {
        ArrayList<Transaction> transactions = new ArrayList<>();

        UserDao userDao = new UserDao();
        ArrayList<Account> accounts = userDao.getUserAccountsByUserID(userID);
        try (
                Connection connection = DbConnection.createConnection();
                PreparedStatement ps = connection.prepareStatement(Constants.SHOW_TRANSACTION_BY_ACCOUNT_NUMBER_QUERY);
        ) {
            for (Account account : accounts) {
                ps.setLong(1, account.getNumber());

                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    double amount = resultSet.getDouble("amount");
                    Time time = resultSet.getTime("time");
                    long withdrawNumber = resultSet.getLong("withdraw_number");
                    long depositNumber = resultSet.getLong("deposit_number");
                    Transaction transaction = new Transaction(id, amount, time, withdrawNumber, depositNumber);
                    transactions.add(transaction);
                }
                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Can not showTransactionsByUserID. \n Error: " + e.getMessage());
        }
        return transactions;
    }

    public ArrayList<Transaction> showTransactionsByAccountNumber(long accountNumber) throws DatabaseException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (
                Connection connection = DbConnection.createConnection();
                PreparedStatement ps = connection.prepareStatement(Constants.SHOW_TRANSACTION_BY_ACCOUNT_NUMBER_QUERY);
        ) {
            ps.setLong(1, accountNumber);
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    double amount = resultSet.getDouble("amount");
                    Time time = resultSet.getTime("time");
                    long withdrawNumber = resultSet.getLong("withdraw_number");
                    long depositNumber = resultSet.getLong("deposit_number");
                    Transaction transaction = new Transaction(id, amount, time, withdrawNumber, depositNumber);
                    transactions.add(transaction);
                }
                resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Can not showTransactionsByUserID. \n Error: " + e.getMessage());
        }

        return transactions;
    }

}
