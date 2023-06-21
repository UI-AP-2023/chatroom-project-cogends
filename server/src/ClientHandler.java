import database.Massages;
import database.Users;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private String clientID;

    public ClientHandler(Socket socket) throws IOException {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            this.clientID= bufferedReader.readLine();

            if(Users.getInstance().showUsersID().contains(this.clientID)){
                this.clientUsername=Users.getInstance().getUsername(this.clientID);
                clientHandlers.add(this);
                Massages massages = new Massages();

                for (String massage0 :massages.showMassage()){
                    this.bufferedWriter.write(massage0);
                    this.bufferedWriter.newLine();
                    this.bufferedWriter.flush();
                }
                broadcastMassage("SERVER: " + clientUsername + " has connected the chat ");
            }else {
                sendError();
            }

        } catch (IOException e) {
            closeEveryThing(socket, bufferedWriter, bufferedReader);
        }
    }

    @Override
    public void run() {
        String massageFromClient;
        while (socket.isConnected()) {
            try {
                massageFromClient = bufferedReader.readLine();
                if (massageFromClient.equals("exit")) {
                    closeEveryThing(socket,bufferedWriter,bufferedReader);
                }else {
                    if (!massageFromClient.equals("")) {
                        Massages massages = new Massages();
                        massageFromClient=this.clientUsername + " : " +massageFromClient;
                        massages.saveMassage(this.clientID,this.clientUsername,massageFromClient, LocalDateTime.now());
                    }
                    broadcastMassage(massageFromClient);
                }
            } catch (IOException e) {
                break;
            }
        }
    }
    public void broadcastMassage(String massageToSend) throws IOException {
        if (massageToSend.equals("")){
            sendConnected(this.clientUsername);
        }else {
            try {
                for (ClientHandler clientHandler : clientHandlers) {
                    if (!clientHandler.clientUsername.equals(this.clientUsername)) {
                        clientHandler.bufferedWriter.write(massageToSend);
                        clientHandler.bufferedWriter.newLine();
                        clientHandler.bufferedWriter.flush();
                    }
                }
            } catch (IOException e) {
                closeEveryThing(socket, bufferedWriter, bufferedReader);
            }
        }

    }

    public void removeClientHandler() throws IOException {
        clientHandlers.remove(this);
        broadcastMassage("SERVER:" + clientUsername + " has left the chat");
    }

    public void closeEveryThing(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) throws IOException {
        removeClientHandler();
        try {
            if (bufferedReader != null)
                bufferedReader.close();
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------
    public void sendConnected(String username) throws IOException {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.clientUsername.equals(username)) {
                clientHandler.bufferedWriter.write("connected");
                clientHandler.bufferedWriter.newLine();
                clientHandler.bufferedWriter.flush();
            }
        }
    }
    //--------------------------------------------
    public void sendError() throws IOException {
        this.bufferedWriter.write(" error");
        this.bufferedWriter.newLine();
        this.bufferedWriter.flush();
    }
    //--------------------------------------------------
}
