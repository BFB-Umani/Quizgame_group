package com.quizgame.server;

import com.quizgame.*;

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
    int testCheckRound = 0;
    int testScoreOut = 0;
    private Map<String, Integer> stats = new HashMap<>();
    private boolean roundComplete = false;

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
                } else if (object instanceof ChosenSubjectObject) {
                    ChosenSubjectObject chosenSubjectObject = (ChosenSubjectObject) object;
                    questions = game.getQuestionsBySubjects(chosenSubjectObject.subject);
                    sendQuestionsToClient(questions);
                } else if (object instanceof Integer) {
                    setRound();
                    sendScore((Integer) object);
                    if (getRound() == opponent.getRound()) {
                    } else {
                        sendOpponentQuestion(questions);
                    }
                } else if (object instanceof Boolean) {
                    this.roundComplete = (boolean) object;
                    if (roundComplete && opponent.roundComplete) {
                        game.anotherSubject();
                        roundComplete = false;
                        opponent.roundComplete = false;
                    }
                }
                else if(object instanceof String) {
                    String msg = username + ": " + object;
                    opponent.out.writeObject(msg);
                    opponent.out.flush();
                }
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("player " + username + " disconnected");
                break;
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

    public void sendScore(int points) {
        stats.put(username, points);
        testScoreOut++;
        System.out.println(testScoreOut);
        try {
            if (testScoreOut == 1 && opponent.testScoreOut == 1) {
                out.reset();
                out.writeObject(opponent.stats);
                opponent.out.reset();
                opponent.out.writeObject(stats);
                opponent.out.flush();
                out.flush();
                testScoreOut = 0;
                opponent.testScoreOut = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendQuestionsToClient(List<QuizItem> questions) {
        QuestionsBySubjectObject questionsBySubjectObject = new QuestionsBySubjectObject();
        questionsBySubjectObject.questions = questions;
        try {
            out.writeObject(questionsBySubjectObject);
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

