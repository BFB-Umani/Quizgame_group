package com.quizgame.server;

import com.quizgame.*;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Player extends Thread {
    private Socket socketToClient;
    private Player opponent;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String username;
    private Game game;


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
        while (true) {

            try {
                Object object = in.readObject();
                if (object instanceof SetNameObject) {
                    SetNameObject setNameObject = (SetNameObject) object;
                    setPlayerName(setNameObject.text);
                    game.playerReady();
                }
                else if(object instanceof ChosenSubjectObject){
                    ChosenSubjectObject chosenSubjectObject = (ChosenSubjectObject) object;
                    List<QuizItem> questions = game.getQuestionsBySubjects(chosenSubjectObject.subject);
                    sendQuestionsToClient(questions);

                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPlayerName(String name) {
        this.username = name;
        System.out.println(name);
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void sendSubjectsToClient(List<String> subjectList) {
        ChooseSubjectObject chooseSubjectObject = new ChooseSubjectObject();
        chooseSubjectObject.subjects = subjectList;
        try {
            out.writeObject(chooseSubjectObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendQuestionsToClient(List<QuizItem> questions){
        QuestionsBySubjectObject questionsBySubjectObject = new QuestionsBySubjectObject();
        questionsBySubjectObject.questions = questions;
        try {
            out.writeObject(questionsBySubjectObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

