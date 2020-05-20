package com.mycompany.kelimeavigame.models;

import com.mycompany.kelimeavigame.views.WaitingWindow;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;
import utils.Game;
import utils.GameUtils;

/* THE CODES (AND THEIR COMMENTS) IN THIS FILE WERE TAKEN FROM :
JanGaraba-CodeExamples -> Java Text 2nd Ed -> Examples -> Chap3 -> MultiEchoServerNIO.java 
Youtube channel : covers11 at : https://www.youtube.com/watch?v=AofvCRyvkAk
Youtube channel : David Dobervich at : https://www.youtube.com/watch?v=ZIzoesrHHQo
 */
public class Server {

    private BufferedReader receiver;
    private PrintWriter sender;
    private static Collection<Player> connectedPlayers = new ArrayList<Player>();
    private static int playingOrder = 0;
    public static String nickname;
    private static ArrayList<String> takenNicknames = new ArrayList<String>();
    private ExecutorService threadsManager = Executors.newFixedThreadPool(9);

    public Server(String nickname) {
        Server.nickname = nickname;
        System.out.println("Server named " + Server.nickname + " has created a game.");
        System.out.println("# of connected players:" + connectedPlayers.size());
        try {
            ServerSocket serverSocket = new ServerSocket(Game.serverConnectionInfo.serverPort);
            while (true) {
                Socket socket = serverSocket.accept();
                // check if the number of players is less than 9 players (the server is the tenth)
                //   create new player object 
                Player player = new Player(socket);
                if (connectedPlayers.size() < 9) {
                    //   get the nickname that was sent from the client 
                    String playerNickname = player.receiver.readLine();
                    //  check if the nickname has been taken before or not
                    if (!takenNicknames.contains(playerNickname)) {
                        // if it hasn't been taken:
                        player.nickname = playerNickname;
                        // add the player to the connectedPlayers collection
                        connectedPlayers.add(player);
                        // let threadsManager manages the client's thread
                        threadsManager.execute(player);
                        // add nickname to the list
                        takenNicknames.add(playerNickname);
                        // update players list with the newly entered player's nickname
                        updatePlayersList(playerNickname);
                        // update all players with the new connection (i.e. player)
                        ////      updateAllPlayers();

                    } else {
                        // i.e. the received nickname has been taken before, so tell the client that
                        player.sender.println("This Nickname is in use, pick another one please!");
                    }
                } else {
                    player.sender.println("Sorry! the game is full");
                }
            }

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.exit(1);
        }
    }

    private void closeSocket(SelectionKey key, Socket socket) {
        try {
            if (socket != null) {
                socket.close();
                // remove it from the connectedPlayers collection
                connectedPlayers.removeIf(player -> player.socket == socket);
            }
        } catch (IOException ioEx) {
            System.out.println(
                    "*** Unable to close socket! ***");
        }
    }

    private void updatePlayersList(String newNickname) {
        WaitingWindow.addNewPlayerToList(newNickname);
    }

}
