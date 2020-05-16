package com.mycompany.kelimeavigame.models;

public class Player {
    String nickname;
    String serverIP;
    String serverPort;

    public Player(String nickname, String serverIP, String serverPort) {
        this.nickname = nickname;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    
}
