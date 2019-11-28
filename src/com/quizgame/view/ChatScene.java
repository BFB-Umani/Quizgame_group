package com.quizgame.view;

import com.quizgame.Controller.QuizController;
import com.quizgame.client.QuizClient;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChatScene {
    Stage secondStage = new Stage();
    Button button = new Button("Send");
    ScrollPane scrollPane = new ScrollPane();
    TextField enterMessageField = new TextField();
    TextArea displayAllMessages = new TextArea();

    public Stage getSecondStage() {
        return secondStage;
    }

    public TextField getEnterMessageField() {
        return enterMessageField;
    }

    public TextArea getDisplayAllMessages() {
        return displayAllMessages;
    }

    public Button getButton() {
        return button;
    }

    public void setUp() {
        enterMessageField.setEditable(true);
        displayAllMessages.setPrefHeight(500);
        displayAllMessages.setEditable(false);
        scrollPane.setContent(displayAllMessages);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        displayAllMessages.setPrefWidth(650);

        VBox vBoxChat = new VBox(enterMessageField);
        vBoxChat.setPadding(new Insets(10, 10, 10, 10));

        VBox vBoxChatIncoming = new VBox(displayAllMessages);
        vBoxChatIncoming.setPadding(new Insets(10, 10, 10, 10));

        VBox vBoxEnter = new VBox(button);
        vBoxEnter.setPadding(new Insets(10, 10, 10, 10));

        GridPane rootPane = new GridPane();
        rootPane.add(vBoxChatIncoming, 0, 0);
        rootPane.add(vBoxChat, 0, 1);
        rootPane.add(vBoxEnter, 1, 1);

        Scene scene = new Scene(rootPane, 400, 300, Color.WHITE);
        secondStage.setScene(scene);
        secondStage.setTitle("Chat");


    }

}
