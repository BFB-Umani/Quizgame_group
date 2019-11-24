package com.quizgame;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class QuizServer extends Thread{
    private Socket socketToClient;
    private QuizServer opponent;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String username;
    private int playerNumber;


    QuizServer(Socket socketToClient, String username) {

        this.socketToClient = socketToClient;
        this.username = username;

    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socketToClient.getOutputStream());
            in = new ObjectInputStream(socketToClient.getInputStream());

            String thisMsg;
            QuizProtocol qp = new QuizProtocol();
            out.writeObject(playerNumber);
            while((thisMsg =  (String)in.readObject()) != null) {
                System.out.println("Server: " + thisMsg);
                out.writeObject(qp.processQuestion(thisMsg));

            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Player died: " + e);
        }

    }

    public void setNamn(String name) {
        this.username = name;
    }

    public void setOpponent(QuizServer opponent) {
        this.opponent = opponent;
    }
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    public QuizServer getOpponent() {
        return this.opponent;
    }

}

