package com.quizgame;

import javafx.scene.control.Button;

public class Round {
    public int questionsPerRound;
    private Button player1Score;
    private Button player2Score;

    public  Round(Button player1Score, Button player2Score) {
        this.player1Score = player1Score;
        this.player2Score = player2Score;
    }

    public Button getPlayer1Score() {
        return player1Score;
    }

    public Button getPlayer2Score() {
        return player2Score;
    }
}
