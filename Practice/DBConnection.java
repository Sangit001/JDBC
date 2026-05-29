package Practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/Bank";
    private static final String username = "root";
    private static final String password = "sangit1543";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

}