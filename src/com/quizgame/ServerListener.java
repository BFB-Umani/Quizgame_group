package com.quizgame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    public static void main(String[] args) {
        while(true) {
            try {
                ServerSocket socket = new ServerSocket(55555);
                Socket socketToClient = socket.accept();
                QuizServer start = new QuizServer(socketToClient);
                start.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
