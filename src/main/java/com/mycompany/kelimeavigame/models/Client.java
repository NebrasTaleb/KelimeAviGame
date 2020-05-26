package com.mycompany.kelimeavigame.models;

import CommunicationModels.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import utils.Game;
import utils.ServerResponseHandler;

/* THE CODES (AND THEIR COMMENTS) IN THIS FILE WERE TAKEN FROM :
JanGaraba-CodeExamples -> Java Text 2nd Ed -> Examples -> Chap3 -> MultiEchoClient.java
Youtube channel : covers11 at : https://www.youtube.com/watch?v=nUI4zO6abH0
Medium article : Blocking I/O and non-blocking I/O at : https://medium.com/coderscorner/tale-of-client-server-and-socket-a6ef54a74763
Youtube channel : David Dobervich at : https://www.youtube.com/watch?v=ZIzoesrHHQo
Youtube channel : zaneacademy at : https://www.youtube.com/watch?v=1up-oHjCcis
 */
public class Client {

    public String nickname;
    public Socket socket;
    public ObjectOutputStream objectSender;
    private Message message;
    public static ServerResponseHandler serverResponseHandler;

    public Client(String nickname) throws IOException {
        this.nickname = nickname;

        try {
            socket = new Socket(Game.clientConnectionInfo.serverIP, Game.clientConnectionInfo.serverPort);
            objectSender = new ObjectOutputStream(socket.getOutputStream());
            serverResponseHandler = new ServerResponseHandler(socket);
            // execute the serverResponseHandler on different thread
            new Thread(serverResponseHandler).start();
            // send client nickname to the server
            message = new Message("join", nickname);
            objectSender.writeObject(message);

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private void disconnect() throws IOException {
        try {
            if (socket != null) {
                socket.close();
            }
            System.out.println(nickname + " disconnected");

        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
