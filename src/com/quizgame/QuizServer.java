package com.quizgame;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class QuizServer extends Thread{
    private Socket socketToClient;
    QuizServer opponent;
    ObjectOutputStream out;
    ObjectInputStream in;
    String username;


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
            QuizProtocol qp = new QuizProtocol(this);
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
        System.out.println("In setNamn: " + username);
    }

    public void setOpponent(QuizServer opponent) {
        this.opponent = opponent;
    }

}

