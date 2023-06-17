import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("waiting ");
        ServerSocket serverSocket=new ServerSocket(6666);
        Socket clientSocket=serverSocket.accept();
        System.out.println("server accepted");
        InputStreamReader reader=new InputStreamReader(clientSocket.getInputStream());
        BufferedReader in=new BufferedReader(reader);
        String answer="";
        // System.out.println(answer);
        while (( answer=in.readLine())!=null) {
            if(answer.equals("finish")){
                answer += "tamom shoood";
                System.out.println(answer);
                break;
            }
            else {
                answer += "  --> Etelaei Nadaram! ";
                System.out.println(answer);}
        }
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}