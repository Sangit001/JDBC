package Practice;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {
        DBConnection.getConnection();

        Scanner scanner = new Scanner(System.in);

        Service accountService = new Service(scanner);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully");
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return;
        }

        while(true){
            System.out.println("Welcome to ========== MINI Bank ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Exit");

            System.out.println("Choose option");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){

                case 1:
                    accountService.createAccount();
                    break;
                case 2:
                    accountService.checkBalance();
                    break;
                case 3:
                    accountService.depositMoney();
                    break;
                case 4:
                    accountService.withdrawMoney();
                    break;
                case 5:
                    System.out.println("Thank you.....");
                    return;

                default:
                    System.out.println("Invalid input");
            }



        }




    }
}