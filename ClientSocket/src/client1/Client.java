package client1;

import java.io.*;
import java.net.Socket;

import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;

    private String userID;
    private BufferedWriter bufferedWriter;
    private long start;

    //-----------------------------------
    public Client(Socket socket, String userID) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userID = userID;
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    //----------------------------------
    public void sendMessage() {
        try {
            bufferedWriter.write(userID);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            start = System.currentTimeMillis();
            bufferedWriter.write("");//----------------------
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                if (messageToSend.equals("exit")) {
                    bufferedWriter.write("exit");//----------------------
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    closeEverything(socket,bufferedReader,bufferedWriter);
                }
                if (messageToSend.equals("ping")) {
                    start = System.currentTimeMillis();
                    bufferedWriter.write("");
                } else {
                    bufferedWriter.write( messageToSend);
                }
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    //---------------------------------------
    public void listenForMassage() {
        new Thread(() -> {
            String msgFromGroupChat;
            while (socket.isConnected()) {
                try {
                    msgFromGroupChat = bufferedReader.readLine();
                    if (msgFromGroupChat.equals("connected")){
                        long end=System.currentTimeMillis();
                        System.out.println("ping : "+ (end-start));
                    }else {
                        System.out.println(msgFromGroupChat);
                    }

                } catch (IOException|NullPointerException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }

    //----------------------------------------
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();

            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    //-----------------------------------------


}
