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
    int point;


    QuizServer(Socket socketToClient, String username) {

        this.socketToClient = socketToClient;
        this.username = username;

    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socketToClient.getOutputStream());
            in = new ObjectInputStream(socketToClient.getInputStream());

            Object thisMsg;
            QuizProtocol qp = new QuizProtocol(this);
            while((thisMsg =  in.readObject()) != null) {
                System.out.println("Server: " + thisMsg);
                if(thisMsg instanceof String) {
                    out.writeObject(qp.processQuestion((String) thisMsg));
                }
                else if(thisMsg instanceof Integer) {
                    int points = (int) thisMsg;
                    System.out.println("You got this many points :" + points);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Player died: " + e);
        }

    }

    public void setNamn(String name) {
        this.username = name;
        System.out.println("In setNamn: " + username);
    }

    public void setPoints(int point) {
        this.point = point;

    }

    public void setOpponent(QuizServer opponent) {
        this.opponent = opponent;
    }

}

