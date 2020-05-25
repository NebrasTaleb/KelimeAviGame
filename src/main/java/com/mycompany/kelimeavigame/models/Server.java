package com.mycompany.kelimeavigame.models;

import CommunicationModels.Message;
import com.mycompany.kelimeavigame.views.ServerWaitingWindow;
import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import utils.Game;

/* THE CODES (AND THEIR COMMENTS) IN THIS FILE WERE TAKEN FROM :
JanGaraba-CodeExamples -> Java Text 2nd Ed -> Examples -> Chap3 -> MultiEchoServerNIO.java 
Youtube channel : covers11 at : https://www.youtube.com/watch?v=AofvCRyvkAk
Youtube channel : David Dobervich at : https://www.youtube.com/watch?v=ZIzoesrHHQo
Youtube channel : zaneacademy at : https://www.youtube.com/watch?v=1up-oHjCcis
 */
public class Server {

    public static List<Player> connectedPlayers = new ArrayList<Player>();
    private static int playingOrder = 0;
    public static String nickname;
    private ExecutorService threadsManager = Executors.newFixedThreadPool(9);
    private Player player;

    public Server(String nickname) {
        Server.nickname = nickname;
        Game.connectedPlayersNicknames.add(nickname);
        System.out.println("Server named " + Server.nickname + " has created a game.");
        System.out.println("# of connected players:" + connectedPlayers.size());
        try {
            ServerSocket serverSocket = new ServerSocket(Game.serverConnectionInfo.serverPort);
            while (true) {
                Socket socket = serverSocket.accept();
                //   create new player object 
                player = new Player(socket);
                //   get client request
                Message clientRequest = (Message) player.objectReceiver.readObject();

                if (clientRequest.header.equals("join")) {
                    // the client sent his/her nickname to server for joining the game
                    joinNewPlayer(clientRequest);
                }
            }

        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }

    private void joinNewPlayer(Message clientRequest) throws IOException {
        // check if the number of players is less than 9 players (the server is the tenth)
        if (connectedPlayers.size() < 9) {
            //   get the nickname that was sent from the client
            String playerNickname = (String) clientRequest.content;
            //  check if the nickname has been taken before or not
            if (!Game.connectedPlayersNicknames.stream().anyMatch(playerNickname::equalsIgnoreCase) && !Game.serverNickname.equalsIgnoreCase(playerNickname)) {
                // if it hasn't been taken:
                player.nickname = playerNickname;
                // add the player to the connectedPlayers collection
                connectedPlayers.add(player);
                // add player's nickname to the global list
                Game.connectedPlayersNicknames.add(playerNickname);
                // let threadsManager manages the client's thread
                threadsManager.execute(player);
                // update players list with the newly entered player's nickname
                updatePlayersList(playerNickname);
                // send playersList to the player to notify him/her that he/she was accepted and can navigate to ClientWaitingWindow and display the passed list of players' nicknames
                player.objectSender.writeObject(new Message("playersList", Game.connectedPlayersNicknames));
                // update all players with the new connection (i.e. player)
                updateAllPlayers(playerNickname);
                System.out.println("# of connected players:" + connectedPlayers.size());
            } else {
                // i.e. the received nickname has been taken before, so tell the client that
                player.objectSender.writeObject(new Message("error", "This Nickname is in use, pick another one please!"));
            }
        } else {
            player.objectSender.writeObject(new Message("error", "Sorry! the game is full"));
        }
    }

    private void updateAllPlayers(String newPlayerNickname) throws IOException {
        Player player;
        for (int index = 0; index < (connectedPlayers.size() - 1); index++) {
            // don't update the newly joined player, only the others
            player = connectedPlayers.get(index);
            player.objectSender.writeObject(new Message("playerJoined", newPlayerNickname));
        }
    }

    private void closeSocket(SelectionKey key, Socket socket) {
        try {
            if (socket != null) {
                socket.close();

            }
        } catch (IOException ioEx) {
            System.out.println(
                    "*** Unable to close socket! ***");
        }
    }

    private void updatePlayersList(String newNickname) {
        ServerWaitingWindow.addNewPlayerToList(newNickname);
    }

}
