package com.quizgame.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WaitingScene {

    private VBox designLayout = new VBox();
    private Label waitingImage = new Label();
    private Label waitingText = new Label("Waiting...");
    private Button b1 = new Button("Continue");
    private Button okButton = new Button("OK");
    private Label textArea = new Label("opponent must answer the questions first");

    public void setUp(){
        designLayout.getChildren().add(waitingImage);
        designLayout.getChildren().add(waitingText);
        designLayout.getChildren().add(b1);

        designLayout.setId("background");
        waitingImage.setId("waitingImage");
        waitingText.setId("waitingText");

        waitingImage.setAlignment(Pos.CENTER);
        waitingImage.setPadding(new Insets(50));
        waitingText.setPrefSize(460,50);
        waitingText.setAlignment(Pos.CENTER);
        b1.setAlignment(Pos.BOTTOM_CENTER);
        b1.setPadding(new Insets(250));
        b1.setMinSize(88,30);
        b1.setMaxSize(88,30);
        okButton.setMinSize(88,30);
        textArea.setMinSize(20,30);
    }

    public VBox getDesignLayout() {
        return designLayout;
    }

    public Button getB1() {
        return b1;
    }

    public void setB1(Button b1) {
        this.b1 = b1;
    }

    public Button getOkButton() {
        return okButton;
    }

    public void setOkButton(Button okButton) {
        this.okButton = okButton;
    }

    public Label getTextArea() {
        return textArea;
    }

    public void setTextArea(Label textArea) {
        this.textArea = textArea;
    }
}
