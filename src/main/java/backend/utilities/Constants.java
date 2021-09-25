package backend.utilities;

public class Constants {
    public static final String DB_URL = "jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6440001";
    public static final String DB_USERNAME = "sql6440001";
    public static final String DB_PASSWORD = "FA6VQGLMGg";

    public static final String REGISTER_USER_QUERY =
            "INSERT INTO sql6440001.user (name) VALUES(?)";

    public static final String GET_USER_ACCOUNTS_BY_USER_ID_QUERY =
            "SELECT id, user_id, account_number, balance FROM sql6440001.account WHERE user_id = ?";

    public static final String ADD_ACCOUNT_QUERY =
            "INSERT INTO sql6440001.account (user_id, account_number, balance) VALUES(?, ?, ?)";

    public static final String GET_BALANCE_QUERY =
            "SELECT balance FROM sql6440001.account WHERE account_number = ?";

    public static final String UPDATE_BALANCE_QUERY =
            "UPDATE sql6440001.account SET balance=? WHERE account_number=?";

    public static final String ADD_TRANSACTION_QUERY =
            "INSERT INTO sql6440001.transaction (amount, `time`, withdraw_number, deposit_number) " +
                    "VALUES(?, ?, ?, ?)";

//    public static final String SHOW_TRANSACTION_BY_ACCOUNT_NUMBER_QUERY =
//            "SELECT id, amount, `time`, withdraw_number, deposit_number FROM sql6440001.TRANSACTION " +
//                    "WHERE id = ?";

    public static final String SHOW_TRANSACTION_BY_ACCOUNT_NUMBER_QUERY =
            "SELECT id, amount, `time`, withdraw_number, deposit_number FROM sql6440001.transaction " +
                    "WHERE withdraw_number = ?";
}
