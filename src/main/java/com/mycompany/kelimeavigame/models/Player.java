package com.mycompany.kelimeavigame.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/* THE CODES (AND THEIR COMMENTS) IN THIS FILE WERE TAKEN FROM :
Youtube channel : David Dobervich at : https://www.youtube.com/watch?v=ZIzoesrHHQo
 */
public class Player implements Runnable {

    String nickname;
    int playingOrder;
    int score;
    int numOfWinningGames;
    Socket socket;
    BufferedReader receiver;
    PrintWriter sender;

    public Player(Socket socket) throws IOException {
        this.nickname = "";
        this.playingOrder = 0; // it is 0 initially and when server starts the game it will be changed
        this.score = 0;
        this.numOfWinningGames = 0;
        this.socket = socket;
        receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        sender = new PrintWriter(socket.getOutputStream(), true);
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
        sender.println("Welcome Mr. " + nickname);
        try {
            while (true) {
                // wait for any client requests
                String clientRequest = receiver.readLine();

                //   if (clientRequest.contains(nickname)) {
                //   }
            }

        } catch (IOException exception) {

        } finally {
            sender.close();
            try {
                socket.close();
                receiver.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
