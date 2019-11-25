package com.quizgame;

import com.quizgame.Controller.QuizController;
import com.quizgame.QuizServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(12345);
        try {
            while(true) {

                QuizServer player1 = new QuizServer(listener.accept(), "player1");
                QuizServer player2 = new QuizServer(listener.accept(), "player2");
                player1.setOpponent(player2);
                player2.setOpponent(player1);
                player1.setCurrentPlayer(player1);
                player1.setPlayerNumber(1);
                player2.setPlayerNumber(2);
                player1.start();
                player2.start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
