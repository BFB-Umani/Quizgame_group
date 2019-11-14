package com.quizgame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        while(true){
            try{
                final Socket socketToClient = serverSocket.accept();
                QuizThread quizThread= new QuizThread(socketToClient);
                quizThread.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
