package com.quizgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class QuizServer extends Thread{
    private Socket socketToClient;

    QuizServer(Socket socketToClient) {
        this.socketToClient = socketToClient;
    }

    @Override
    public void run() {

        try {
            PrintWriter out = new PrintWriter(socketToClient.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socketToClient.getInputStream()));

            String input;
           /* while((input = in.readLine()) != null) {
                out.println("hej");
            } */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
