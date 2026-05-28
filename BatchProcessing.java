import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner;

public class BatchProcessing {
   public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/IBM_COMPANY";
        String username = "root";
        String password = "sangit1543";
        String query = "INSERT INTO employee (name,salary) values (?,?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");

        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        // creating connection
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("connection successful");
            Scanner scanner = new Scanner(System.in);
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            while(true) {
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
                System.out.println("Enter salary: ");
                double salary = scanner.nextDouble();

                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, salary);
                preparedStatement.addBatch();

                System.out.println("Want to add more? [Y/N]");
                scanner.nextLine();
                String decission = scanner.nextLine();
                if (decission.toUpperCase().equals("N")) {
                    break;
                }
            }

            int[] batchResult = preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Successful");

        }catch (SQLException e){
           throw new RuntimeException(e);
        }




    }
}