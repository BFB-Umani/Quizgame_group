package com.quizgame;

public class Game {
    private int round;
    private DataBaseUpdated database;
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2, DataBaseUpdated database){
        this.player1 = player1;
        this.player2 = player2;
        this.database = database;
        System.out.println("New Game created.");
    }

    public void startGame(){
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        player1.start();
        player2.start();

    }
}
