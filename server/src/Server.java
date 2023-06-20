import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }
    public void startServer(){
        try {
          //  System.out.println("befor while");
            while (!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("a new client has connected ");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
        closeServerSocket();        }
    }
    public void closeServerSocket(){
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
