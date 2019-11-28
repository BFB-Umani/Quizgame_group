package com.quizgame.view;

import com.quizgame.Round;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ResultScene {
    private VBox designLayout = new VBox();
    private Label resultText = new Label("Result");
    private HBox playerLayout = new HBox();

    private VBox resultLayout = new VBox();
    private VBox playerOne = new VBox();
    private VBox playerTwo = new VBox();
    private VBox cButton = new VBox();

    private Label playerOneText = new Label();
    private Label playerTwoText = new Label();

    private Button continueB = new Button("Continue");
    private List<Round> resultButton;
    private Button chat = new Button("chat");


    public List<Round> getResultButton() {
        return resultButton;
    }

    public void setUp(){
        designLayout.getChildren().add(resultText);
        designLayout.getChildren().add(playerLayout);
        playerLayout.getChildren().add(resultLayout);
        playerLayout.getChildren().add(playerOne);
        playerLayout.getChildren().add(playerTwo);

        playerOne.getChildren().add(playerOneText);
        playerTwo.getChildren().add(playerTwoText);

        // test layout
        cButton.getChildren().add(continueB);
        designLayout.getChildren().add(cButton);
        cButton.setAlignment(Pos.BOTTOM_CENTER);

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

        continueB.setMinSize(120,60);
        continueB.setMaxSize(120,60);
        continueB.setId("button");

        playerOne.setSpacing(40);
        playerTwo.setSpacing(40);

        designLayout.getChildren().add(chat);
    }

    public Label getPlayerOneText() {
        return playerOneText;
    }

    public Label getPlayerTwoText() {
        return playerTwoText;
    }

    public void createDynamic(int antal) {
        resultButton = new ArrayList<>();
        if(antal > 3) {
            ScrollPane s1 = new ScrollPane(playerLayout);
            designLayout.getChildren().add(s1);
            s1.setFitToHeight(true);
            s1.setFitToWidth(true);
            s1.setId("scrollpane");
            System.out.println("adding scrollpane");
        }
        for(int i = 0; i < antal; i++) {
            Button player1Result = new Button();
            playerOne.getChildren().add(player1Result);
            Button player2Result = new Button();
            playerTwo.getChildren().add(player2Result);
            resultButton.add(new Round(player1Result, player2Result));
            Button roundButton = new Button("Round " + String.valueOf(i + 1));
            resultLayout.getChildren().add(roundButton);
            player1Result.setMinSize(60,30);
            player2Result.setMinSize(60,30);
            roundButton.setMinSize(60,30);

            roundButton.setPrefWidth(80);
            player1Result.setPrefWidth(80);
            player2Result.setPrefWidth(80);
        }


    }

    public Button getContinueB() {
        return continueB;
    }

    public void createTotalResultButton(){
        Button total = new Button("Total");
        int playerOneTotalScore = 0;
        int playerTwoTotalScore = 0;

        for (int i = 0; i <resultButton.size() ; i++) {
            try {
                String point1 = resultButton.get(i).getPlayer1Score().getText();
                String point2 = resultButton.get(i).getPlayer2Score().getText();
                playerOneTotalScore += Integer.parseInt(point1);
                playerTwoTotalScore += Integer.parseInt(point2);
            }
            catch(NumberFormatException e) {
                System.out.println("number format error");
            }
        }
        Button totalResult1 = new Button(String.valueOf(playerOneTotalScore));
        Button totalResult2 = new Button(String.valueOf(playerTwoTotalScore));

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

    public VBox getDesignLayout() {
        return designLayout;
    }
    public Button getChat() {
        return chat;
    }
}