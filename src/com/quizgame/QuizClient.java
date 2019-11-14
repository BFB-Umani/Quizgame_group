package com.quizgame;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class QuizClient {
    public QuizClient(){
        String hostname = "192.168.1.72";
        int portNr = 12345;
        try(
                Socket quizSocket = new Socket(hostname,portNr);
                PrintWriter out = new PrintWriter(quizSocket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(quizSocket.getInputStream()));

                ) {



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QuizClient quizClient = new QuizClient();
    }
}
