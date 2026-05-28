
import java.sql.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;


public class HRSystem {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        final String url = "jdbc:mysql://localhost:3306/HRSystem";
        final String username = "root";
        final String password = "sangit1543";


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException ce){
            System.out.println(ce.getMessage());
        }

        try{
            Connection conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();
            // Main Menu

            while (true) {
                System.out.println("===== Welcome to Hotel San =====");
                Scanner scanner = new Scanner(System.in);
                System.out.println("1. Reserve a room");
                System.out.println("2. View reservation");
                System.out.println("3. Get room number");
                System.out.println("4. Update reservation");
                System.out.println("5. Delete reservation");
                System.out.println("0. Exit");
                System.out.println("Choose an option.");
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        reserverRoom(conn, scanner);
                        break;

                    case 2:
                        viewReservation(conn, scanner);

                        break;

                    case 3:
                        getRoomNumber(conn, scanner);

                        break;

                    case 4:
                        updateReservation(conn, scanner);

                        break;

                    case 5:
                        deleteReservation(conn, scanner);

                        break;

                    case 0:
                        stmt.close();
                        conn.close();
                        return;

                    default:
                        System.out.println("Index out of bound");
                        break;

                }

            }
            }catch (SQLException se){
        System.out.println(se.getMessage());
    }




    }
    private static void reserverRoom(Connection conn, Scanner scanner){
        System.out.println("Enter your name");
        String name = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Room number");
        int roomNumber = scanner.nextInt();
        System.out.println("Phone number");
        String phoneNumber = scanner.next();
        scanner.nextLine();

        try{

        Statement stmt = conn.createStatement();
        String insert = "Insert into reservation (name,room_number,contact_number) values('"+name +"',"+ roomNumber + ",'" + phoneNumber+"')";
        int rowAffect = stmt.executeUpdate(insert);

        if(rowAffect > 0){
            System.out.println("Insertion successful.");
            System.out.println("Room booked successfully");
        }else{
            System.out.println("Nothing changes.");
        }

        }catch (SQLException se){
            System.out.println(se.getMessage());
        }
    }

    // ++++ View Reservation ++++

    private static void viewReservation(Connection conn, Scanner scanner){
        System.out.println("Enter room id.");
        int roomId = scanner.nextInt();
        String select = "select id,name, room_number, contact_number from reservation where room_number = "+roomId;
        try{
            Statement statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(select);


            if(resultset.next()){
                String name = resultset.getString("name");
                int roomNumber = resultset.getInt("room_number");
                String contactNumber = resultset.getString("contact_number");

                System.out.println("Name: "+name);
                System.out.println("Room ID: "+roomNumber);
                System.out.println("Contact Number: "+ contactNumber);

            }

        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    private static void getRoomNumber(Connection conn, Scanner scanner){
        System.out.println("Enter your name");
        String name = scanner.next();
        scanner.nextLine();
        String getRoom = "select room_number from reservation where name = '" +name+"'" ;
        try{
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(getRoom);
        if(resultSet.next()){
            int roomNumber = resultSet.getInt("room_number");
            System.out.println("Your room number is: "+roomNumber);
        }else{
            System.out.println("Room not found");
        }

        }catch(SQLException se){
            System.out.println(se.getMessage());
        }

    }

    private static void updateReservation(Connection conn, Scanner scanner){

        System.out.println("Please enter Room ID to update");
        int updateRoom = scanner.nextInt();
        System.out.println("Enter name: ");
        String name = scanner.next();
        scanner.nextLine();
        System.out.println("Enter phone number: ");
        String number = scanner.next();
        scanner.nextLine();
        String updateQuery = "update reservation set name ='"+name+"'"+ ", contact_number = '"+ number +"'";

        try{
            Statement stmt = conn.createStatement();
            int rowAffected = stmt.executeUpdate(updateQuery);
            System.out.println("Updated successfully");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }

    }

    private static void deleteReservation(Connection conn, Scanner scanner){
        System.out.println("Enter room id to delete.");
        int roomId = scanner.nextInt();
        String deleteQuery ="DELETE FROM reservation WHERE room_number = " + roomId;;
        try{
            Statement stmt = conn.createStatement();
            int rowAffected = stmt.executeUpdate(deleteQuery);
            System.out.println("Deleted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}