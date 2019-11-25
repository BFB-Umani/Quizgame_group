package com.quizgame.Controller;

import com.quizgame.QuizClient;
import com.quizgame.view.WaitingScene;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WaitingSceneController {
    private WaitingScene waitingScene;
    private QuizClient quizClient = new QuizClient();
    public WaitingSceneController(WaitingScene waitingScene){
        this.waitingScene = waitingScene;
    }

    public void start() {
        waitingScene.getB1().setOnAction(actionEvent -> {
            System.out.println();
            if(!quizClient.getDoneRound()) {
                Stage dialogStage = new Stage();
                VBox vBox = new VBox(waitingScene.getTextArea(), waitingScene.getOkButton());
                waitingScene.getOkButton().setCursor(Cursor.HAND);

                waitingScene.getOkButton().setOnMouseClicked(actionEvent2 -> {
                    dialogStage.close();
                });
                dialogStage.setScene(new Scene(vBox));
                dialogStage.show();
            }
            else {
                quizClient.goToQuizScene();
            }
        });
        waitingScene.setUp();
    }
}
