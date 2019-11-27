package com.quizgame.view;

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
    private Label playerOneText = new Label("Player 1");
    private Label playerTwoText = new Label("Player 2");
    private Button roundOne = new Button("Round 1");
    private Button roundTwo = new Button("Round 2");
    private Button total = new Button("Total");
    private Button roundOneResult1 = new Button("");
    private Button roundTwoResult1 = new Button("");
    private Button roundOneResult2 = new Button("");
    private Button roundTwoResult2 = new Button("");
    private Button totalResult1 = new Button("");
    private Button totalResult2 = new Button("");
    private Button continueB = new Button("Continue");

    public Label getPlayerOneText() {
        return playerOneText;
    }

    public Label getPlayerTwoText() {
        return playerTwoText;
    }

    public Button getRoundOneResult1() {
        return roundOneResult1;
    }

    public Button getRoundTwoResult1() {
        return roundTwoResult1;
    }

    public Button getRoundOneResult2() {
        return roundOneResult2;
    }

    public Button getRoundTwoResult2() {
        return roundTwoResult2;
    }

    public Button getTotalResult1() {
        return totalResult1;
    }

    public Button getTotalResult2() {
        return totalResult2;
    }

    public void setUp(){
        designLayout.getChildren().add(resultText);
        designLayout.getChildren().add(playerLayout);
        playerLayout.getChildren().add(resultLayout);
        playerLayout.getChildren().add(playerOne);
        playerLayout.getChildren().add(playerTwo);
        playerOne.getChildren().add(playerOneText);
        resultLayout.getChildren().add(roundOne);
        resultLayout.getChildren().add(roundTwo);
        resultLayout.getChildren().add(total);
        designLayout.getChildren().add(continueB);
        playerOne.getChildren().add(roundOneResult1);
        playerOne.getChildren().add(roundTwoResult1);
        playerOne.getChildren().add(totalResult1);
        playerTwo.getChildren().add(playerTwoText);
        playerTwo.getChildren().add(roundOneResult2);
        playerTwo.getChildren().add(roundTwoResult2);
        playerTwo.getChildren().add(totalResult2);

        designLayout.setId("background");
        resultText.setId("resultText");
        roundOne.setId("roundOne");
        roundTwo.setId("roundTwo");
        total.setId("total");
        playerOne.setId("playerOne");
        playerTwo.setId("playerTwo");
        continueB.setId("button");
        resultText.setPrefSize(480,100);
        resultText.setPadding(new Insets(40));
        resultText.setAlignment(Pos.CENTER);

        playerOne.setPrefWidth(200);
        playerTwo.setPrefWidth(200);

        resultLayout.setAlignment(Pos.BASELINE_LEFT);
        resultLayout.setPrefSize(150,100);
        resultLayout.setSpacing(40);
        resultLayout.setPadding(new Insets(50,50,50,-100));


        roundOne.setPrefWidth(200);
        roundTwo.setPrefWidth(200);
        total.setPrefWidth(200);


        roundOne.setMinSize(60,30);
        roundTwo.setMinSize(60,30);
        total.setMinSize(60,30);

        playerLayout.setPadding(new Insets(50,50,50,140));

        playerOne.setSpacing(40);
        playerTwo.setSpacing(40);

        roundOneResult1.setMinSize(60,30);
        roundOneResult2.setMinSize(60,30);
        totalResult1.setMinSize(60,30);
        roundTwoResult1.setMinSize(60,30);
        roundTwoResult2.setMinSize(60,30);
        totalResult2.setMinSize(60,30);
        continueB.setMinSize(120,60);
        continueB.setMaxSize(120,60);
        continueB.setPadding(new Insets(200 ,300,200,400));



    }


    public VBox getDesignLayout() {
        return designLayout;
    }
}
