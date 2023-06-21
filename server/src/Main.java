import database.Users;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket=new ServerSocket(1234);
        Users.getInstance().saveUsers();
        Server server = new Server(serverSocket);
        server.startServer();
    }
}