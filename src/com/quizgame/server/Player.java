package com.quizgame.server;

import com.quizgame.*;
import com.quizgame.properties.ServerPropertiesReader;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player extends Thread {
    private Socket socketToClient;
    private Player opponent;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String username;
    private Game game;
    private List<QuizItem> questions;
    private int round = 0;
    int testChecKround = 0;


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
                else if(object instanceof Integer) {
                    setRound();
                    sendScore((Integer) object);
                    System.out.println(username + " " + getRound());
                    System.out.println(opponent.username + " " + opponent.getRound());
                    if(getRound() == opponent.getRound()) {
                        System.out.println("both at round " + ++testChecKround);
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

    public void setGame(Game game) { //??????????????
        this.game = game;
    }

    public void sendSubjectsToClient(List<String> subjectList) {
        ChooseSubjectObject chooseSubjectObject = new ChooseSubjectObject();
        chooseSubjectObject.subjects = subjectList;
        try {
            out.writeObject(chooseSubjectObject);
            System.out.println("sending subjects: " + username);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendScore(int points) {
        Map<String, Integer> stats = new HashMap<>();
        stats.put(username, points);
        stats.forEach((k, v) -> System.out.println("Namn: " + k + " Poäng:" + v));
        try {
            opponent.out.writeObject(stats);
            System.out.println("sending map: " + username);
            opponent.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendQuestionsToClient(List<QuizItem> questions){
        QuestionsBySubjectObject questionsBySubjectObject = new QuestionsBySubjectObject();
        questionsBySubjectObject.questions = questions;
        try {
            out.writeObject(questionsBySubjectObject);
            System.out.println("sending questions " + username);
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
            System.out.println("sending questions " + username);
            opponent.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int getRound() {
        return round;
    }

    public void setRound() {
        this.round++;
    }
}

