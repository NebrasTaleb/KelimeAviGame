package com.mycompany.kelimeavigame.models;

public class Rules {
    int gameWidth;
    int gameHeight;
    int numOfBlocks;
    int winningPoints;
    int numOfGames;
    int numOf2X;
    int numOf3X;

    public Rules(int gameWidth, int gameHeight, int numOfBlocks, int winningPoints, int numOfGames, int numOf2X, int numOf3X) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.numOfBlocks = numOfBlocks;
        this.winningPoints = winningPoints;
        this.numOfGames = numOfGames;
        this.numOf2X = numOf2X;
        this.numOf3X = numOf3X;
    }
    
}