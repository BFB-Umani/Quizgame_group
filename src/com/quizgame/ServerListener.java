package com.quizgame;

import com.quizgame.QuizServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(12345);
        try {
            while(true) {

                /*QuizProtocol game = new QuizProtocol();*/
                QuizServer player1 = new QuizServer(listener.accept(), "player1"/*, game*/);
                QuizServer player2 = new QuizServer(listener.accept(), "player2"/*, game*/);
                player1.setOpponent(player2);
                player2.setOpponent(player1);

                //antingen stoppar vi protokollet i quizServer
                //eller startar vi en enda tråd game.start och sen syncronizerar
                //eller instansierar protokollet från QuizServer.run()
                player1.start();
                player2.start();

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
