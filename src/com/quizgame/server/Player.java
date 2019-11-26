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
    private List<QuizItem> questions;
    private int round = 0;


    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

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
                    questions = game.getQuestionsBySubjects(chosenSubjectObject.subject);
                    sendQuestionsToClient(questions);
                }
                else if(object instanceof Boolean) {
                    setRound(1);
                    System.out.println(username + " " + getRound());
                    System.out.println(opponent.username + " " + opponent.getRound());
                    if(getRound() == 1 && opponent.getRound() == 1) {
                        System.out.println("both at round 1");
                        game.anotherSubject();
                    }
                    else {
                        sendOpponentQuestion(questions);
                    }

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
            System.out.println("sending subjects");
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
            System.out.println("sending questions");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void sendOpponentQuestion(List<QuizItem> questions) {
        QuestionsBySubjectObject questionsBySubjectObject = new QuestionsBySubjectObject();
        questionsBySubjectObject.questions = questions;
        try {
            opponent.out.writeObject(questionsBySubjectObject);
            System.out.println("sending questions");
            opponent.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

