import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDATA {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "sangit1543";
        String query = "Insert into student (id, name, marks) values (103,'hari', 77.7);";
        String delete = "delete from student where id = 103;";
        String update = "update student set name ='hari' where id = 102;";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            int affectCount = st.executeUpdate(update);

            if (affectCount > 0) {
                System.out.println("Deleted.");
            } else {
                System.out.println("Nothing affected.");
            }
            conn.close();
            st.close();
            System.out.println("Connection closed.");

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }

        // loading driver manager
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
        } catch (ClassNotFoundException ce) {
            System.out.println(ce.getMessage());
        }
    }
}