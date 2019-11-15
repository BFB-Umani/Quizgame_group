package com.quizgame.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class QuizView {
    private Label questionLabel = new Label();
    private Label pointsLabel = new Label("Actual points");
    private GridPane answerLayout = new GridPane();
    private VBox designLayout = new VBox();
    private Button answerButton1 = new Button();
    private Button answerButton2 = new Button();
    private Button answerButton3 = new Button();
    private Button answerButton4 = new Button();

    public void setUp(){
        designLayout.setId("background");
        questionLabel.setId("questionLabel");
        pointsLabel.setId("pointLabel");
        designLayout.getChildren().add(pointsLabel);
        designLayout.getChildren().add(questionLabel);


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

        questionLabel.setPrefSize(350,500);
        pointsLabel.setPrefSize(50,150);



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

//        answerButton1.setMaxWidth(1000);
//        answerButton2.setMaxWidth(1000);
//        answerButton3.setMaxWidth(1000);
//        answerButton4.setMaxWidth(1000);
//
//        answerButton1.setMaxHeight(1000);
//        answerButton2.setMaxHeight(1000);
//        answerButton3.setMaxHeight(1000);
//        answerButton4.setMaxHeight(1000);

        answerButton1.setMinSize(200,100);
        answerButton2.setMinSize(200,100);
        answerButton3.setMinSize(200,100);
        answerButton4.setMinSize(200,100);

        answerLayout.setVgap(1);
        answerLayout.setHgap(1);
        answerLayout.setPadding(new Insets(5,0,0,25));
//        questionLabel.setVisible(false);
//        answerButton1.setVisible(false);
//        answerButton2.setVisible(false);
//        answerButton3.setVisible(false);
//        answerButton4.setVisible(false);
    }

    public VBox getDesignLayout() {
        return designLayout;
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
}
