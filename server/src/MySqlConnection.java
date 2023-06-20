import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnection {
    public void dataBase() throws SQLException, ClassNotFoundException {
        String URL = "jdbc:mysql://localhost/chatroom-db";

        String username = "server";
        String password = "1234";
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(URL,username,password);

        String SQLCom  ="";
        Statement s = connection.prepareStatement(SQLCom);
        s.execute(SQLCom);
        connection.close();
        System.out.println("finish");
    }
}
