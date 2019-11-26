package com.quizgame.view;

import com.quizgame.QuizResult;
import com.quizgame.Round;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResultScene {
    private VBox designLayout = new VBox();
    private Label resultText = new Label("Result");
    private HBox playerLayout = new HBox();

    private VBox resultLayout = new VBox();
    private VBox playerOne = new VBox();
    private VBox playerTwo = new VBox();

    private Label playerOneText = new Label();
    private Label playerTwoText = new Label();

    public void setUp(){
        designLayout.getChildren().add(resultText);
        designLayout.getChildren().add(playerLayout);
        playerLayout.getChildren().add(resultLayout);
        playerLayout.getChildren().add(playerOne);
        playerLayout.getChildren().add(playerTwo);

        playerOne.getChildren().add(playerOneText);
        playerTwo.getChildren().add(playerTwoText);

        designLayout.setId("background");
        resultText.setId("resultText");
        playerOne.setId("playerOne");
        playerTwo.setId("playerTwo");
        resultText.setPrefSize(480,100);
        resultText.setPadding(new Insets(40));
        resultText.setAlignment(Pos.CENTER);

        playerOne.setPrefWidth(200);
        playerTwo.setPrefWidth(200);

        resultLayout.setAlignment(Pos.BASELINE_LEFT);
        resultLayout.setPrefSize(150,100);
        resultLayout.setSpacing(40);
        resultLayout.setPadding(new Insets(50,50,50,-100));

        playerLayout.setPadding(new Insets(50,50,50,140));

        playerOne.setSpacing(40);
        playerTwo.setSpacing(40);
    }

    public void showResult(QuizResult quizResult){
        playerOneText.setText(quizResult.player1Name);
        playerTwoText.setText(quizResult.player2Name);

        for (Round round : quizResult.rounds) {
           createRound(round);
        }
        createTotalResultButton(quizResult);
    }

    private void createTotalResultButton(QuizResult quizResult){
        Button total = new Button("Total");
        int playerOneTotalScore = 0;
        int playerTwoTotalScore = 0;
        int totalNumberOfQuestions = 0;
        for (int i = 0; i <quizResult.rounds.size() ; i++) {
            playerOneTotalScore +=quizResult.rounds.get(i).player1Score; // player1Score got from server - actual score every round.
            playerTwoTotalScore +=quizResult.rounds.get(i).player2Score;

            totalNumberOfQuestions +=quizResult.rounds.get(i).questionsPerRound;
        }
        Button totalResult1 = new Button(playerOneTotalScore + "/" + totalNumberOfQuestions );
        Button totalResult2 = new Button(playerTwoTotalScore + "/" + totalNumberOfQuestions);

        resultLayout.getChildren().add(total);
        playerOne.getChildren().add(totalResult1);
        playerTwo.getChildren().add(totalResult2);

        totalResult1.setMinSize(60,30);
        totalResult2.setMinSize(60,30);
        total.setMinSize(60,30);

        totalResult1.setPrefWidth(80);
        totalResult2.setPrefWidth(80);
        total.setPrefWidth(80);
    }

    private void createRound(Round round){
        Button roundButton = new Button("Round "+round.round);
        resultLayout.getChildren().add(roundButton);

        Button player1Result = new Button(round.player1Score + "/" + round.questionsPerRound);
        playerOne.getChildren().add(player1Result);

        Button player2Result = new Button(round.player2Score + "/" + round.questionsPerRound);
        playerTwo.getChildren().add(player2Result);

        player1Result.setMinSize(60,30);
        player2Result.setMinSize(60,30);
        roundButton.setMinSize(60,30);

        roundButton.setPrefWidth(80);
        player1Result.setPrefWidth(80);
        player2Result.setPrefWidth(80);

    }

    public VBox getDesignLayout() {
        return designLayout;
    }
}
