 import java.sql.*;
import java.util.Scanner;
 public class PrepareStatement {
    static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/hotel_san";
        String username = "root";
        String password = "sangit1543";
        String readQuery = "select * from food_menu";
        String writeQuery = "Insert into food_menu (food_id,food_name,food_price) value(?,?,?)";

        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
       Connection connection =  DriverManager.getConnection(url,username,password);
            System.out.println("Connection successful.");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter food id: ");
            int foodId = scanner.nextInt();
            System.out.println("Enter food name: ");
            scanner.nextLine();
            String foodName = scanner.nextLine();
            System.out.println("Enter food price: ");
            int foodPrice = scanner.nextInt();

            PreparedStatement preparedStatement = connection.prepareStatement(writeQuery);

            preparedStatement.setInt(1,foodId);
            preparedStatement.setString(2,foodName);
            preparedStatement.setInt(3,foodPrice);


            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected > 0){

                System.out.println("Insertion successfully");

            }else{
                System.out.println("Insertion failed.");
            }

//
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                int foodIds = resultSet.getInt("food_id");
//                String foodNames = resultSet.getString("food_name");
//                int foodPrices = resultSet.getInt("food_price");
//
//                System.out.println("S.N----- FoodName ----- Price.");
//                System.out.println(foodIds +"      " + foodNames +"      "+ foodPrices);
//
//            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

    }
}