package com.quizgame;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

public class QuizServer extends Thread{
    private Socket socketToClient;
    QuizServer opponent;
    BufferedReader in;
    ObjectOutputStream out;
    String username;
    Database database = new Database();
    QuizProtocol game;



    QuizServer(Socket socketToClient, String username/*, QuizProtocol game*/) {

        this.socketToClient = socketToClient;
        this.username = username;
        /*this.game = game;*/
        try {
            out = new ObjectOutputStream(socketToClient.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socketToClient.getInputStream()));

            //out.println("WELCOME " + username);
            //out.println("MESSAGE Waiting for opponent to connect");*/



            List<QuizItem> toClient= database.getItemPack(); //det får inte vara här men i protokollet
            out.writeObject(toClient);

        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }


    }


    public void run() {

    //antingen hela protokollet eller vi instansierar protokollet här
        /*QuizProtocol game = new QuizProtocol();*/
        System.out.println("nu startar protokollet för" + username);

    }

    public void setNamn(String name) {
        this.username = name;
    }

    public void setOpponent(QuizServer opponent) {
        this.opponent = opponent;
    }

}

