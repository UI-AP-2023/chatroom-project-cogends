import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println("waiting ");
        ServerSocket serverSocket=new ServerSocket(1234);
<<<<<<< HEAD
        while (true){
            Socket clientSocket=serverSocket.accept();
            System.out.println("server accepted");
        }
=======
        Server server = new Server(serverSocket);
        server.startServer();
>>>>>>> d7e37eca5c358695b2db7101fcbe8d222661ffb6
    }
}