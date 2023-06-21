import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Massages {
    public void saveMassage(String userID, String username, String massage, LocalDateTime time){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chatroom-db","server","1234");
            String SQLCom  =String.format("INSERT INTO `massages`(`userID`, `username`, `massage`, `time`) VALUES ('%s','%s','%s','%s')",userID,username,massage,time);
            Statement s = connection.prepareStatement(SQLCom);
            s.execute(SQLCom);
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<String> showMassage(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chatroom-db","server","1234");
            String SQLCom  ="SELECT `username`, `massage` FROM `massages` ORDER BY `massages`.`time` ASC";
            Statement s = connection.prepareStatement(SQLCom);
            ResultSet resultSet =  s.executeQuery(SQLCom);
            ArrayList<String> massages = new ArrayList<>();
            while (resultSet.next()){
                massages.add(resultSet.getString("massage"));
            }
            connection.close();
            return massages;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
