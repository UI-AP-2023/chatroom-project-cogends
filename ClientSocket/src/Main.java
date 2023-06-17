import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket clientSocket=new Socket("127.0.0.1",1234);
        System.out.println("connected");
        Scanner sc=new Scanner(System.in);
        PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);
        String input="";
        do{
            input=sc.nextLine();
            out.println(input);
        }
        while (!Objects.equals(input, "finish"));
        System.out.println("proses finish ");
        out.close();

    }
}
