package com.quizgame.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class QuizView {
    private VBox designLayout = new VBox();
    private Label questionLabel = new Label();
    private GridPane answerLayout = new GridPane();
    private Label scoreCounter = new Label();
    private Button answerButton1 = new Button();
    private Button answerButton2 = new Button();
    private Button answerButton3 = new Button();
    private Button answerButton4 = new Button();
    private Button continueButton = new Button("Continue");
    private Button chat = new Button("chat");



    public void setUp(){
        designLayout.getChildren().add(scoreCounter);
        HBox questionPane = new HBox();
        designLayout.setId("background");
        designLayout.getChildren().add(questionPane);
        designLayout.getChildren().add(answerLayout);
        answerLayout.getChildren().add(answerButton1);
        answerLayout.getChildren().add(answerButton2);
        answerLayout.getChildren().add(answerButton3);
        answerLayout.getChildren().add(answerButton4);

        GridPane.setRowIndex(answerButton1,0);
        GridPane.setColumnIndex(answerButton1,0);
        GridPane.setRowIndex(answerButton2,0);
        GridPane.setColumnIndex(answerButton2,1);
        GridPane.setRowIndex(answerButton3,1);
        GridPane.setColumnIndex(answerButton3,0);
        GridPane.setRowIndex(answerButton4,1);
        GridPane.setColumnIndex(answerButton4,1);

        answerLayout.setPrefSize(400,500);

        questionPane.setId("questionBackground");
        questionPane.setPrefSize(400,500);
        questionPane.getChildren().add(questionLabel);
        questionLabel.setPrefSize(280,100);
        questionLabel.setWrapText(true);
        questionLabel.setAlignment(Pos.CENTER);
        questionPane.setPadding(new Insets(100));

        scoreCounter.setText("Score: 0");
        scoreCounter.setId("scoreCounter");
        scoreCounter.setPrefSize(70,50);



        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(50);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        answerLayout.getColumnConstraints().addAll(column0,column1);

        RowConstraints row0 = new RowConstraints();
        row0.setPercentHeight(50);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(50);
        answerLayout.getRowConstraints().addAll(row0,row1);

        answerButton1.setMinSize(200,100);
        answerButton2.setMinSize(200,100);
        answerButton3.setMinSize(200,100);
        answerButton4.setMinSize(200,100);

        answerLayout.setVgap(1);
        answerLayout.setHgap(1);
        answerLayout.setPadding(new Insets(20,0,20,25));

        HBox continueLayout = new HBox();
        designLayout.getChildren().add(continueLayout);
        continueLayout.getChildren().add(continueButton);
        continueLayout.setPrefSize(400,100);
        continueLayout.setAlignment(Pos.CENTER);
        continueButton.setAlignment(Pos.CENTER);
        continueButton.setId("continueButton");
        continueButton.setMinSize(100,50);
        continueLayout.setPadding(new Insets(20));

        designLayout.getChildren().add(chat);

//        questionLabel.setVisible(false);
//        answerButton1.setVisible(false);
//        answerButton2.setVisible(false);
//        answerButton3.setVisible(false);
//        answerButton4.setVisible(false);
    }

    public VBox getDesignLayout() {
        return designLayout;
    }
    public Button getChat() {
        return chat;
    }

    public Label getQuestionLabel() {
        return questionLabel;
    }

    public GridPane getAnswerLayout() {
        return answerLayout;
    }

    public Button getAnswerButton1() {
        return answerButton1;
    }

    public Button getAnswerButton2() {
        return answerButton2;
    }

    public Button getAnswerButton3() {
        return answerButton3;
    }

    public Button getAnswerButton4() {
        return answerButton4;
    }

    public Button getContinueButton() {
        return continueButton;
    }


    public Label getScoreCounter() {
        return scoreCounter;
    }
}
