import java.sql.*;
import java.util.ArrayList;

public class Users {
    private static Users instance;
    private Users() {

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
            //-----------------------------------------------------
             SQLCom  =String.format("INSERT INTO `users`(`userID`, `username`) VALUES ('%s','%s')","Id2","fatemeh");
             s = connection.prepareStatement(SQLCom);
             s.execute(SQLCom);
             //-----------------------------------------------------
             SQLCom  =String.format("INSERT INTO `users`(`userID`, `username`) VALUES ('%s','%s')","Id3","TA1");
             s = connection.prepareStatement(SQLCom);
             s.execute(SQLCom);
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
    public ArrayList<String> showUsersID(){

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
    public String getUsername(String userID){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/chatroom-db","server","1234");

            String SQLCom  =String.format("SELECT `username` FROM `users` WHERE (userID ='%s')",userID);
            Statement s = connection.prepareStatement(SQLCom);
            ResultSet resultSet =  s.executeQuery(SQLCom);
            String username="";
            while (resultSet.next()){
                username = resultSet.getString("username");
            }

            connection.close();

            return username;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
