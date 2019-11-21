package com.quizgame;

import javafx.animation.ScaleTransition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class QuizServer extends Thread {
    private Socket socketToClient;
    private String userName;
    private QuizServer opponent;
    private DataBaseUpdated dataBaseUpdated = new DataBaseUpdated();

    //int portNr = 12345;

    public QuizServer(Socket socketToClient, String userName) {
        this.socketToClient = socketToClient;
        this.userName = userName;
        try {
            //ServerSocket serverSocket = new ServerSocket(portNr);
            //Socket socketToClient = serverSocket.accept();
            PrintWriter out = new PrintWriter(socketToClient.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketToClient.getInputStream()));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setOpponent(QuizServer opponent) {
        this.opponent = opponent;
    }
}
