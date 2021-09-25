import backend.dao.AccountDao;
import backend.exceptions.DatabaseException;
import frontend.UIManager;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, DatabaseException {
        UIManager bankUi = new UIManager();
        bankUi.run();
//        bankUi.run();
//        AccountDao accountDao = new AccountDao();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter: ");
//        long num = scanner.nextLong();
//        System.out.println(accountDao.getBalanceByAccountNumber(num));
    }
}
