package Practice;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.math.BigDecimal;

public class Service {

    private Scanner scanner;

    public Service(Scanner scanner){
        this.scanner = scanner;
    }

    public void createAccount(){
        String query = "INSERT INTO accounts (account_holder,balance)values (?,?)";
        String getId = "SELECT account_id FROM accounts where account_holder = ?";
        try{
            Connection connection = DBConnection.getConnection();

            PreparedStatement prepareStatement = connection.prepareStatement(query);
            System.out.println("Enter account holder name.");
            String name = scanner.nextLine();
            System.out.println("Enter initial balance");
            BigDecimal balance = scanner.nextBigDecimal();
            scanner.nextLine();
            prepareStatement.setString(1,name);
            prepareStatement.setBigDecimal(2,balance);
            int rowAffected = prepareStatement.executeUpdate();
            if(rowAffected > 0 ){
                System.out.println("Created successfully");
            }else{
                System.out.println("Failed, Try again");
            }
            PreparedStatement preparedStatement2 = connection.prepareStatement(getId);
            preparedStatement2.setString(1,name);
            ResultSet resultSet = preparedStatement2.executeQuery();
            if(resultSet.next()){
            int accID = resultSet.getInt("account_id");
                System.out.println("Your account id is: " + accID + ", Sharing id can harm your account");
            }
            else{
                System.out.println("Can't get id.");
            }



            prepareStatement.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void checkBalance(){
        String query = "SELECT balance FROM accounts WHERE account_id = ?";
        try{
            Connection connection = DBConnection.getConnection();
            System.out.println("Enter account holder id");
            int accID = scanner.nextInt();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,accID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
            BigDecimal balance = resultSet.getBigDecimal("balance");
                System.out.println(accID+" has "+ balance);
            }else{
                System.out.println("Account not found.");
            }

            connection.close();
            preparedStatement.close();

        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void depositMoney(){
        String query = "UPDATE accounts SET balance = balance +  ? WHERE account_id = ?";
    try{
            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
        System.out.println("Enter account id");
        int accID = scanner.nextInt();
        System.out.println("Enter deposite amount");
        BigDecimal depositeAmount = scanner.nextBigDecimal();

        if(depositeAmount.compareTo(BigDecimal.ZERO)<=0){
            System.out.println("Amount must be greater than 0");
            return;
        }

        preparedStatement.setBigDecimal(1,depositeAmount);
        preparedStatement.setInt(2,accID);
        int rowAffected = preparedStatement.executeUpdate();

        if(rowAffected > 0){
            System.out.println("Balance deposited successfully");
            System.out.println("Thank you");
        }else{
            System.out.println("Something went wrong");
        }
        preparedStatement.close();
        connection.close();

    }catch (SQLException e){
        System.out.println(e);
    }
    }

    public void withdrawMoney(){
        String query = "UPDATE accounts SET balance = balance - ? WHERE account_id = ? AND balance >= ?";
        String chekcAcc = "SELECT account_id FROM accounts WHERE account_id = ?";

        try{
            Connection connection = DBConnection.getConnection();
            System.out.println("Enter account id");
            int accId = scanner.nextInt();
            PreparedStatement preparedStatement2 = connection.prepareStatement(chekcAcc);
            preparedStatement2.setInt(1,accId);
            ResultSet resultSet = preparedStatement2.executeQuery();
            int checkId = 0;
            if(resultSet.next()){
             checkId = resultSet.getInt("account_id");
            }

            scanner.nextLine();


            if(accId == checkId){
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                System.out.println("Account found.");
                System.out.println("Enter withdraw amount.");
                BigDecimal withdrawAmount = scanner.nextBigDecimal();
                scanner.nextLine();

                if(withdrawAmount.compareTo(BigDecimal.ZERO) <=0 ){
                    System.out.println("Amount can't be less than 0");
                    return;
                }
                preparedStatement.setBigDecimal(1, withdrawAmount);
                preparedStatement.setInt(2,accId);
                preparedStatement.setBigDecimal(3,withdrawAmount);

                int rowAffected = preparedStatement.executeUpdate();
                if(rowAffected > 0 ){
                    System.out.println("Withdraw successful");
                }else{
                    System.out.println("Failed,Something went wrong");
                }
                preparedStatement.close();
            }else{
                System.out.println("Account not found.");
                return;
            }
            connection.close();
            preparedStatement2.close();
        }catch (SQLException e){
            System.out.println(e);
        }
    }



}