package com.quizgame.view;

import com.quizgame.QuizClient;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChoosingSubjectScene {

    private VBox designLayout = new VBox();
    private Label chooseSubject = new Label("Choose your subject");
    private VBox buttonArea = new VBox();
    private Button subjectButton1 = new Button("test1");
    private Button subjectButton2 = new Button("test2");
    private Button subjectButton3 = new Button("test3");

    public Label getChooseSubject() {
        return chooseSubject;
    }

    public void setUp(){
        designLayout.getChildren().add(chooseSubject);
        designLayout.getChildren().add(buttonArea);
        buttonArea.getChildren().add(subjectButton1);
        buttonArea.getChildren().add(subjectButton2);
        buttonArea.getChildren().add(subjectButton3);

        chooseSubject.setPrefSize(300,275);
        chooseSubject.setMaxWidth(Double.MAX_VALUE);
        chooseSubject.setAlignment(Pos.CENTER);
        designLayout.setId("background");
        chooseSubject.setId("chooseSubject");

        buttonArea.setAlignment(Pos.CENTER);
        buttonArea.setSpacing(30);
        subjectButton1.setPrefSize(150,30);
        subjectButton2.setPrefSize(150,30);
        subjectButton3.setPrefSize(150,30);

    }

    public VBox getDesignLayout() {
        return designLayout;
    }

    public Button getSubjectButton1() {
        return subjectButton1;
    }
    public Button getSubjectButton2() {
        return subjectButton2;
    }
    public Button getSubjectButton3() {
        return subjectButton3;
    }

}
