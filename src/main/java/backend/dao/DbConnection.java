package backend.dao;

import backend.utilities.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                Constants.DB_URL,
                Constants.DB_USERNAME,
                Constants.DB_PASSWORD
        );
        System.out.println("Connected...");
        return connection;
    }
}
