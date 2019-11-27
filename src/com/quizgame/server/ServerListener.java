package com.quizgame.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerListener {
    private static DataBaseUpdated database = new DataBaseUpdated();

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(12345);

        try {
            while(true) {

                Player player1 = new Player(listener.accept(), "player1");
                System.out.println("First player connected");
                Player player2 = new Player(listener.accept(), "player2");
                System.out.println("Second player connected");
                Game game = new Game(player1,player2,database);
                game.startGame();

            }
        }
        catch (IOException e) {
            System.out.println("client down");
        }
    }
}
