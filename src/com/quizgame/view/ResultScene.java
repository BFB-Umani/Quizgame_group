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
    private VBox playerOne = new VBox();
    private VBox playerTwo = new VBox();
    private Label playerOneText = new Label("Player 1");
    private Label playerTwoText = new Label("Player 2");
    private Button roundOneResult1 = new Button("1/3");
    private Button roundTwoResult1 = new Button("2/3");
    private Button roundOneResult2 = new Button("0/3");
    private Button roundTwoResult2 = new Button("3/3");
    private Button totalResult1 = new Button("2/6");
    private Button totalResult2 = new Button("5/6");

    public void setUp(){
        designLayout.getChildren().add(resultText);
        designLayout.getChildren().add(playerLayout);
        playerLayout.getChildren().add(playerOne);
        playerLayout.getChildren().add(playerTwo);
        playerOne.getChildren().add(playerOneText);
        playerOne.getChildren().add(roundOneResult1);
        playerOne.getChildren().add(roundTwoResult1);
        playerOne.getChildren().add(totalResult1);
        playerTwo.getChildren().add(playerTwoText);
        playerTwo.getChildren().add(roundOneResult2);
        playerTwo.getChildren().add(roundTwoResult2);
        playerTwo.getChildren().add(totalResult2);

        designLayout.setId("background");
        resultText.setId("resultText");
        playerOne.setId("playerOne");
        playerTwo.setId("playerTwo");
        resultText.setPrefSize(480,100);
        resultText.setPadding(new Insets(40));
        resultText.setAlignment(Pos.CENTER);

        playerOne.setPrefWidth(200);
        playerTwo.setPrefWidth(200);

        playerLayout.setPadding(new Insets(50,50,50,140));

        playerOne.setSpacing(40);
        playerTwo.setSpacing(40);

        roundOneResult1.setPrefSize(60,30);
        roundOneResult2.setPrefSize(60,30);
        totalResult1.setPrefSize(60,30);
        roundTwoResult1.setPrefSize(60,30);
        roundTwoResult2.setPrefSize(60,30);
        totalResult2.setPrefSize(60,30);




    }


    public VBox getDesignLayout() {
        return designLayout;
    }
}
