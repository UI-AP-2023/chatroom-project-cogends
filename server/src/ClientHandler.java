import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private String  clientID;
    public ClientHandler(Socket socket){
        try {
            this.socket=socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMassage("SERVER:" + clientUsername+"has connected the chat");
        } catch (IOException e) {
            closeEveryThing(socket,bufferedWriter,bufferedReader);
        }

    }

    @Override
    public void run() {
        String massageFromClient;
        while (socket.isConnected()){
            try {
                massageFromClient=bufferedReader.readLine();
                broadcastMassage(massageFromClient);
            } catch (IOException e) {
                closeEveryThing(socket,bufferedWriter,bufferedReader);
                break;
            }
        }
    }
    public void broadcastMassage(String massageToSend){
        try{
            for (ClientHandler clientHandler:clientHandlers){
                if (!clientHandler.clientUsername.equals(this.clientUsername)){
                    clientHandler.bufferedWriter.write(massageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }
        }catch (IOException e) {
            closeEveryThing(socket,bufferedWriter,bufferedReader); throw new RuntimeException(e);
        }
    }
    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMassage("SERVER:" + clientUsername+"has left the chat");
    }
    public void closeEveryThing(Socket socket,BufferedWriter bufferedWriter,BufferedReader bufferedReader){
        removeClientHandler();
        try {
            if (bufferedReader!= null)
                bufferedReader.close();
            if (bufferedWriter!= null)
                bufferedWriter.close();
            if (socket!= null)
                socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
