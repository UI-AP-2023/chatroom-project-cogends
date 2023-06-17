import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println("waiting ");
        ServerSocket serverSocket=new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}