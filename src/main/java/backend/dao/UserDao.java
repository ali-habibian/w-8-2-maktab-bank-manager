package backend.dao;

import backend.entities.Account;
import backend.entities.User;
import backend.exceptions.DatabaseException;
import backend.utilities.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    public void registerUser(User user) throws DatabaseException {
        try (
                Connection connection = DbConnection.createConnection();
                PreparedStatement ps = connection.prepareStatement(Constants.REGISTER_USER_QUERY);
        ) {
            ps.setString(1, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Can not register user. \n Error: " + e.getMessage());
        }
    }

    public ArrayList<Account> getUserAccountsByUserID(int userID) throws DatabaseException {
        ArrayList<Account> accounts = new ArrayList<>();
        try (
                Connection connection = DbConnection.createConnection();
                PreparedStatement ps = connection.prepareStatement(Constants.GET_USER_ACCOUNTS_BY_USER_ID_QUERY);
        ) {
            ps.setInt(1, userID);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                long accountNumber = resultSet.getLong("account_number");
                double balance = resultSet.getDouble("balance");

                Account account = new Account(id, userID, accountNumber, balance);
                accounts.add(account);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Can not get accounts. \n Error: " + e.getMessage());
        }

        return accounts;
    }
}
