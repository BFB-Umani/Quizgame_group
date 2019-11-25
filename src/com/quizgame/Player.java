package com.quizgame;

import java.io.*;
import java.net.Socket;

public class Player extends Thread{
    private Socket socketToClient;
    private Player opponent;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String username;


    public Player(Socket socketToClient, String username) {

        this.socketToClient = socketToClient;
        this.username = username;

        try {
            out = new ObjectOutputStream(socketToClient.getOutputStream());
            in = new ObjectInputStream(socketToClient.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try{
        TransferObject transferObject = (TransferObject) in.readObject();

        setPlayerName(transferObject.text);
        }catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
    }

    public void setPlayerName(String name) {
        this.username = name;
        System.out.println(name);
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

}

