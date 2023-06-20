import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Users {
    private static Users instance;
    private  Users() {

    }

    public static Users getInstance() {
        if (instance == null) {
            instance = new Users() {

            };
        }
        return instance;
    }
    public void saveUsers(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chatroom-db","server","1234");
            String SQLCom  =String.format("INSERT INTO `users`(`userID`, `username`) VALUES ('%s','%s')","Id1","fereshteh");
            Statement s = connection.prepareStatement(SQLCom);
            s.execute(SQLCom);
            //connection.close();
            //-----------------------------------------------------
             SQLCom  =String.format("INSERT INTO `users`(`userID`, `username`) VALUES ('%s','%s')","Id2","fatemeh");
             s = connection.prepareStatement(SQLCom);
            s.execute(SQLCom);
           // connection.close();
             //-----------------------------------------------------
             SQLCom  =String.format("INSERT INTO `users`(`userID`, `username`) VALUES ('%s','%s')","Id3","TA1");
             s = connection.prepareStatement(SQLCom);
             s.execute(SQLCom);
            // connection.close();
             //----------------------------------------------------
             SQLCom  =String.format("INSERT INTO `users`(`userID`, `username`) VALUES ('%s','%s')","Id4","TA2");
             s = connection.prepareStatement(SQLCom);
             s.execute(SQLCom);
            //------------------------
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //--------------------------
    public ArrayList<String> showUsers(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chatroom-db","server","1234");
            String SQLCom  ="SELECT `userID` FROM `users`";
            Statement s = connection.prepareStatement(SQLCom);
            ResultSet resultSet =  s.executeQuery(SQLCom);
            ArrayList<String> users = new ArrayList<>();
            while (resultSet.next()){
                users.add(resultSet.getString("userID"));
            }
            connection.close();
            return users;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
