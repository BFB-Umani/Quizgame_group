package com.quizgame.Controller;

import com.quizgame.QuizClient;
import com.quizgame.view.QuizView;
import com.quizgame.view.WaitingScene;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WaitingSceneController {
    private WaitingScene waitingScene;
    private QuizClient quizClient;
    private boolean boolStart;
    public WaitingSceneController(WaitingScene waitingScene, QuizClient quizClient){
        this.waitingScene = waitingScene;
        this.quizClient = quizClient;
    }

    public void start() {
        waitingScene.getB1().setOnAction(actionEvent -> {
            System.out.println(quizClient.getDoneRound());
            quizClient.getMsg();
            if(!boolStart) {
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

                System.out.println("im in waiting else statement");
                quizClient.goToQuizScene();
            }

        });
        waitingScene.setUp();
    }

    public void setStart(boolean boolStart) {
        this.boolStart = boolStart;
    }

}
