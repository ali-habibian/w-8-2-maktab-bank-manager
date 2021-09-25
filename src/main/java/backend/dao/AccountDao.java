package backend.dao;


import backend.entities.Account;
import backend.exceptions.DatabaseException;
import backend.utilities.Constants;

import java.sql.*;
import java.util.Date;

public class AccountDao {
    public void addAccount(Account account) throws DatabaseException {
        Date date = new Date();
        int userID = account.getUserId();
        long accountNumber = date.getTime();
        double balance = account.getBalance();

        try (
                Connection connection = DbConnection.createConnection();
                PreparedStatement ps = connection.prepareStatement(Constants.ADD_ACCOUNT_QUERY);
        ) {

            ps.setInt(1, userID);
            ps.setLong(2, accountNumber);
            ps.setDouble(3, balance);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Can not add account. \n Error: " + e.getMessage());
        }
    }

    public double getBalanceByAccountNumber(long accountNumber) throws DatabaseException {
        double balance = 0;
        try (
                Connection connection = DbConnection.createConnection();
                PreparedStatement ps = connection.prepareStatement(Constants.GET_BALANCE_QUERY);
        ) {

            ps.setLong(1, accountNumber);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Can not get balance \n Error: " + e.getMessage());
        }

        return balance;
    }

    public void transferMoney(long sourceNumber, long destinationNumber, double amount) throws DatabaseException {
        double sourceAccountBalance = getBalanceByAccountNumber(sourceNumber) - amount;
        double destinationAccountBalance = getBalanceByAccountNumber(destinationNumber) + amount;

        try (
                Connection connection = DbConnection.createConnection();
                PreparedStatement psForSource = connection.prepareStatement(Constants.UPDATE_BALANCE_QUERY);
                PreparedStatement psForDestination = connection.prepareStatement(Constants.UPDATE_BALANCE_QUERY);
        ) {
            psForSource.setDouble(1, sourceAccountBalance);
            psForSource.setLong(2, sourceNumber);
            psForDestination.setDouble(1, destinationAccountBalance);
            psForDestination.setLong(2, destinationNumber);

            psForSource.executeUpdate();
            psForDestination.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Can not transfer money. \n Error: " + e.getMessage());
        }
    }
}
