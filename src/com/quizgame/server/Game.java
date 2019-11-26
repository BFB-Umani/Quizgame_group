package com.quizgame.server;

import com.quizgame.QuizItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {
    private int round;
    private DataBaseUpdated database;
    private Player player1;
    private Player player2;
    private int numberOfPlayerReady;

    public Game(Player player1, Player player2, DataBaseUpdated database){
        this.player1 = player1;
        this.player2 = player2;
        this.database = database;
        System.out.println("New Game created.");
    }

    public void startGame(){
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        player1.start();
        player2.start();
        player1.setGame(this); // Give the player reference to the game
        player2.setGame(this);//????????????????????/
    }

    public void playerReady() {
            List<String> threeSubjects = new ArrayList<>();
        numberOfPlayerReady++;

        if(numberOfPlayerReady == 2){  // when both two players accept new game
            Collections.shuffle(database.getSubjectList());
            threeSubjects.add(database.getSubjectList().get(0));
            threeSubjects.add(database.getSubjectList().get(1));
            threeSubjects.add(database.getSubjectList().get(2));
            player1.sendSubjectsToClient(threeSubjects);
        }
    }

    public void anotherSubject() {
        List<String> threeSubjects = new ArrayList<>();
        Collections.shuffle(database.getSubjectList());
        threeSubjects.add(database.getSubjectList().get(0));
        threeSubjects.add(database.getSubjectList().get(1));
        threeSubjects.add(database.getSubjectList().get(2));
        player2.sendSubjectsToClient(threeSubjects);
    }
    public List<QuizItem> getQuestionsBySubjects(String subject){
        return database.getItemsBySubject(subject);
    }
}
