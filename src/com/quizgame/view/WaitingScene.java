package com.quizgame.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class WaitingScene {
    private VBox designLayout = new VBox();
    private Label waitingImage = new Label();
    private Label waitingText = new Label("Waiting...");
    private Button chat = new Button("chat");


    public void setUp(){
        designLayout.getChildren().add(waitingImage);
        designLayout.getChildren().add(waitingText);

        designLayout.setId("background");
        waitingImage.setId("waitingImage");
        waitingText.setId("waitingText");

        waitingImage.setAlignment(Pos.CENTER);
        waitingImage.setPadding(new Insets(50));
        waitingText.setPrefSize(460,50);
        waitingText.setAlignment(Pos.CENTER);
        designLayout.getChildren().add(chat);



    }

    public VBox getDesignLayout() {
        return designLayout;
    }
    public Button getChat() {
        return chat;
    }
}
