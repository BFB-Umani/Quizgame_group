package com.quizgame;

import java.io.*;
import java.net.Socket;
import java.util.Collections;

public class QuizServer extends Thread{
    private Socket socketToClient;
    QuizServer opponent;
    BufferedReader in;
    ObjectOutputStream out;
    String username;
    Database database = new Database();


    QuizServer(Socket socketToClient, String username) {

        this.socketToClient = socketToClient;
        this.username = username;
        try {
            out = new ObjectOutputStream(socketToClient.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socketToClient.getInputStream()));

            // out.println("WELCOME " + username);
            //out.println("MESSAGE Waiting for opponent to connect");*/
            //out.println(database.allItems.get(8).getFourAnswer().get(2));
            QuizItem item= database.getItem();
            out.writeObject(item);

        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }


    }

    @Override
    public void run() {



    }

    public void setOpponent(QuizServer opponent) {
        this.opponent = opponent;
    }

}

