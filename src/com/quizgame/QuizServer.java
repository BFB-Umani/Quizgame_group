package com.quizgame;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class QuizServer extends Thread {
    private Socket socketToClient;
    QuizClient quizclient;
    private QuizServer opponent;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String username;
    private int playerNumber;
    private boolean doneRound = false;


    QuizServer(Socket socketToClient, String username) {

        this.socketToClient = socketToClient;
        this.username = username;

    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            out = new ObjectOutputStream(socketToClient.getOutputStream());
            in = new ObjectInputStream(socketToClient.getInputStream());
            String thisMsg;
            QuizProtocol qp = new QuizProtocol(this);
            out.writeObject(playerNumber);
            while((thisMsg =  (String)in.readObject()) != null) {
                System.out.println("Server: " + thisMsg);
                out.writeObject(qp.processQuestion(thisMsg));
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Player died: " + currentThread().getName());
        }

    }


    public <T> void sendRound(T fromProto) {
        try {
            opponent.out.writeObject(fromProto);
            System.out.println("Sent round to: " + opponent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNamn(String name) {
        Thread.currentThread().setName(name);
    }

    public String getNamn() {
        return Thread.currentThread().getName();
    }

    public void setOpponent(QuizServer opponent) {
        this.opponent = opponent;
    }
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setDoneRound(boolean doneRound) {
        this.doneRound = doneRound;
    }
    public boolean getDoneRound() {
        return this.doneRound;
    }

}

