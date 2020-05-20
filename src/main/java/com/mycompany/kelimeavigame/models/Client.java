package com.mycompany.kelimeavigame.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import utils.Game;
import utils.ServerResponseHandler;

/* THE CODES (AND THEIR COMMENTS) IN THIS FILE WERE TAKEN FROM :
JanGaraba-CodeExamples -> Java Text 2nd Ed -> Examples -> Chap3 -> MultiEchoClient.java
Youtube channel : covers11 at : https://www.youtube.com/watch?v=nUI4zO6abH0
Medium article : Blocking I/O and non-blocking I/O at : https://medium.com/coderscorner/tale-of-client-server-and-socket-a6ef54a74763
Youtube channel : David Dobervich at : https://www.youtube.com/watch?v=ZIzoesrHHQo
 */
public class Client {

    private String nickname;
    private Socket socket;
    private BufferedReader receiver;
    private PrintWriter sender;
    private ByteBuffer byteBuffer;

    public Client(String nickname) throws IOException {
        this.nickname = nickname;

        try {
            socket = new Socket(Game.clientConnectionInfo.serverIP, Game.clientConnectionInfo.serverPort);
            ServerResponseHandler serverResponseHandler = new ServerResponseHandler(socket);
            sender = new PrintWriter(socket.getOutputStream(), true);
            // send client nickname to the server
            sender.println(nickname);
            // execute the serverResponseHandler on different thread
            new Thread(serverResponseHandler).start();

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } 
//        finally {
//            try {
//                System.out.println("\nClosing connection...");
//                disconnect();
//            } catch (IOException ioEx) {
//                System.out.println("Unable to disconnect!");
//                System.exit(1);
//            }
//        }
    }

    private void disconnect() throws IOException {
        try {
            if (socket != null) {
                socket.close();
            }
            System.out.println(nickname + " closed");

        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
