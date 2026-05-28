import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;


public class TransactionHandling {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/San_Bank";
        String username = "root";
        String password = "sangit1543";

        String withdrawlQuery = "UPDATE Users SET amount = amount - ? where account_number = (?)";
        String depositeQuery = "UPDATE Users SET amount = amount + ? where account_number = (?)";

        // Loading the driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");
        }catch (ClassNotFoundException ce){
            System.out.println(ce.getMessage());
        }

        // connection buildup

        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection successfully");
            connection.setAutoCommit(false);

            try{
            PreparedStatement withdrawlStatement = connection.prepareStatement(withdrawlQuery);
            PreparedStatement depositeStatement = connection.prepareStatement(depositeQuery);

            withdrawlStatement.setDouble(1,500.00);
            withdrawlStatement.setString(2,"account123");
            depositeStatement.setDouble(1,500.00);
            depositeStatement.setString(2,"account456");
            int rowAffectedWithdrawl = withdrawlStatement.executeUpdate();
            int rowAffectedDeposite = depositeStatement.executeUpdate();

            if(rowAffectedDeposite > 0 && rowAffectedWithdrawl > 0){
                connection.commit();
                System.out.println("Transaction successful");
            }else{
                connection.rollback();
                System.out.println("Transaction failed");
            }
            } catch (SQLException e) {
                connection.rollback();
                System.out.println(e.getMessage());
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}