package com.quizgame.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class StartScene {
    private VBox designLayout = new VBox();
    private Label quizkampen = new Label("Quizkampen");
    private HBox nameArea = new HBox();
    private Label name = new Label("Name:       ");
    private TextField textField = new TextField();
    private Button newGameButton = new Button("New Game");



    public void setUp() {
        designLayout.getChildren().add(quizkampen);
        designLayout.getChildren().add(nameArea);

        nameArea.getChildren().add(name);
        nameArea.getChildren().add(textField);

        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().add(newGameButton);
        designLayout.getChildren().add(buttonLayout);

        quizkampen.setPrefSize(300,275);
        quizkampen.setMaxWidth(Double.MAX_VALUE);
        quizkampen.setAlignment(Pos.CENTER);
        designLayout.setId("background");
        quizkampen.setId("quizKampenText");

        nameArea.setAlignment(Pos.CENTER);
        name.setId("nameText");

        buttonLayout.setAlignment(Pos.CENTER);
        newGameButton.setPrefSize(88,30);
        buttonLayout.setId("buttonLayout");
        newGameButton.setMaxWidth(Double.MAX_VALUE);


    }


    public VBox getDesignLayout() {
        return designLayout;
    }

    public Label getQuizkampen() {
        return quizkampen;
    }

    public Label getName() {
        return name;
    }

    public TextField getTextField() {
        return textField;
    }

    public Button getNewGameButton() {
        return newGameButton;
    }


}
