package com.mycompany.kelimeavigame.models;

import CommunicationModels.Message;
import com.mycompany.kelimeavigame.views.ServerWaitingWindow;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import utils.Game;
import utils.GameUtils;

/* THE CODES (AND THEIR COMMENTS) IN THIS FILE WERE TAKEN FROM :
Youtube channel : David Dobervich at : https://www.youtube.com/watch?v=ZIzoesrHHQo
Youtube channel : zaneacademy at : https://www.youtube.com/watch?v=1up-oHjCcis
 */
public class Player implements Runnable {

    String nickname;
    int playingOrder;
    int score;
    int numOfWinningGames;
    Socket socket;
    public final ObjectInputStream objectReceiver;
    public final ObjectOutputStream objectSender;
    private Message clientRequest;
    private Boolean isPlayerListening = true;

    public Player(Socket socket) throws IOException {
        this.nickname = "";
        this.playingOrder = 0; // it is 0 initially and when server starts the game it will be changed
        this.score = 0;
        this.numOfWinningGames = 0;
        this.socket = socket;
        objectSender = new ObjectOutputStream(socket.getOutputStream());
        objectReceiver = new ObjectInputStream(socket.getInputStream());
    }

    public void assignPlayingOrder(int order) {
        this.playingOrder = order;
    }

    public void increaseScore(int addedScore) {
        this.score += addedScore;
    }

    public void winGame() {
        this.numOfWinningGames++;
    }

    @Override
    public void run() {
        System.out.println("run() method : player " + nickname + " connceted by the " + socket);
        try {
            while (isPlayerListening) {
                // wait for any client requests
                clientRequest = (Message) objectReceiver.readObject();
                // check client request keyword to determine what operation should be executed
                checkClientRequestHeader(clientRequest);
            }

        } catch (IOException exception) {
            exception.printStackTrace();

        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();

        }
//        finally {
//            try {
//                disconnect();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    }

    private void checkClientRequestHeader(Message clientRequest) throws IOException {
        if (clientRequest.header.equals("playersList")) {
            // the client wants the players list
            sendPlayersListToPlayer(clientRequest);

        } else if (clientRequest.header.equals("gameInfo")) {
            // the client wants the game info
            sendGameInfoToPlayer(clientRequest);

        } else if (clientRequest.header.equals("exit")) {
            disjoinPlayer(clientRequest);
        }
    }

    private void sendPlayersListToPlayer(Message cilentRequest) throws IOException {

    }

    private void sendGameInfoToPlayer(Message clientRequest) throws IOException {
        // send this player the game info
        objectSender.writeObject(new Message("gameInfo", Game.gameRules));
    }

    private void disjoinPlayer(Message clientRequest) throws IOException {
        disconnect();
        // remove it from the connectedPlayers List
        Server.connectedPlayers.removeIf(player -> player.nickname.equals((String) clientRequest.content));
        // remove it from the displayed list
        ServerWaitingWindow.removePlayerFromList((String) clientRequest.content);
        // remove it from the connectedPlayersNicknames list
        Game.connectedPlayersNicknames.remove((String) clientRequest.content);
        // notify all connected players that the player disjoined from the game by sending them the new list
        notifyAllPlayers();
        System.out.println("# of connected players:" + Server.connectedPlayers.size());
    }

    private void notifyAllPlayers() throws IOException {
        Player player;
        for (int index = 0; index < Server.connectedPlayers.size(); index++) {
            // notify all connected players only (i.e. without the one who disjoined)
            player = Server.connectedPlayers.get(index);
            System.out.println(player.nickname + " will be notified that a player disjoined!");
            System.out.println("nicknames list contains this " + Game.connectedPlayersNicknames + " before sending");
            var toSendArrayList = new ArrayList<String>();
            GameUtils.copyListToList(Game.connectedPlayersNicknames, toSendArrayList);
            player.objectSender.writeObject(new Message("playerDisjoined", toSendArrayList));
        }

    }

    public void disconnect() throws IOException {
        try {
            isPlayerListening = false;
            objectReceiver.close();
            socket.close();
            System.out.println(nickname + " player disconnected at server");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
