import java.io.*;
import java.sql.*;

public class ImageHandling {
    static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ImageStore";
        String username = "root";
        String password = "sangit1543";
        String path = "/Users/sangitpandey/Documents/image/GPTIMG.png";
        String folder_path = "/Users/sangitpandey/Documents/image/";
        String insertQuery = "Insert into Images (ImageData) values(?)";
        String readQuery = "SELECT ImageData FROM Images WHERE ImageID = (?)";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully.");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful");

            PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setInt(1,1);
            ResultSet resultSet= preparedStatement.executeQuery();

            if(resultSet.next()){
                byte [] imageData = resultSet.getBytes("ImageData");
                String imagePath = folder_path+"extractedImage.png";
                OutputStream outputStream = new FileOutputStream(imagePath);
                outputStream.write(imageData);
            }else{
                System.out.println("File not found");
            }






            //            FileInputStream fileInputStream = new FileInputStream(path);
//            byte[] imageData = new byte[fileInputStream.available()];
//            fileInputStream.read(imageData);
//            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
//            preparedStatement.setBytes(1,imageData);
//            int rowAffected = preparedStatement.executeUpdate();
//
//            if(rowAffected > 0){
//                System.out.println("insertion succesful");
//            }
//            else{
//                System.out.println("Failed");
//            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}