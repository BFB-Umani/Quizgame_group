package com.quizgame.Controller;

import com.quizgame.client.QuizClient;
import com.quizgame.view.ChatScene;
import javafx.scene.input.KeyCode;

public class ChatController {
    ChatScene chatScene;
    QuizClient quizClient;

    public ChatController(ChatScene chatScene, QuizClient quizClient){
        this.chatScene = chatScene;
        this.quizClient = quizClient;
    }

    public void start() {
        chatScene.setUp();
        chatScene.getEnterMessageField().requestFocus();
//        chatScene.getSecondStage().setOnCloseRequest(l -> {
//            chatScene.getSecondStage().hide();
//        });

        chatScene.getButton().setOnAction(l -> {
            chatScene.getDisplayAllMessages().appendText(quizClient.getStartScene().getTextField().getText()
                    +": " +chatScene.getEnterMessageField().getText() + "\n");
            quizClient.getServerConnection().sendChat(chatScene.getEnterMessageField().getText());
            chatScene.getEnterMessageField().setText("");

        });

        chatScene.getEnterMessageField().setOnMouseClicked(l -> {
            chatScene.getEnterMessageField().setText("");
        });

        chatScene.getEnterMessageField().setOnKeyPressed(l -> {
            if(l.getCode() == KeyCode.ENTER) {
                chatScene.getDisplayAllMessages().appendText(quizClient.getStartScene().getTextField().getText()
                        +": " +chatScene.getEnterMessageField().getText() + "\n");
                quizClient.getServerConnection().sendChat(chatScene.getEnterMessageField().getText());
                chatScene.getEnterMessageField().setText("");
            }
        });
    }

}
