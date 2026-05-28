import java.sql.*;
public class Main {
    public static void main(String[] args){

        String url = "jdbc:mysql://localhost:3306/students";
        String username = "root";
        String password = "sangit1543";
        String query = "SELECT * FROM STUDENT";

        try{
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connection successful.");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double marks = rs.getDouble("marks");
                System.out.println("");
                System.out.println("==========================");

                System.out.println("Name : "+ name);
                System.out.println("Id : "+id);
                System.out.println("Marks : "+marks);
            }
            rs.close();
            stmt.close();
            conn.close();

        }catch (SQLException e){

        }

        try{
        Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Successfully loaded.");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }


        }
}