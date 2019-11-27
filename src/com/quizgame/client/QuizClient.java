package com.quizgame.client;

import com.quizgame.Controller.*;
import com.quizgame.QuizItem;
import com.quizgame.properties.ServerPropertiesReader;
import com.quizgame.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class QuizClient extends Application {
    private Scene scene;
    private ChoosingSubjectScene choosingSubjectScene;
    private WaitingScene waitingScene;
    private ResultScene resultScene;
    private QuizView quizView;
    private ServerConnection serverConnection;
    private QuizController quizController;
    private ChoosingSubjectSceneController choosingSubjectSceneController;
    private StartScene startScene;
    private ResultSceneController resultSceneController;

    public QuizClient() {    //NO TOUCH THIS!!!!
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Quizgame");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/images/quizIcon.png"));
        ServerPropertiesReader serverPropertiesReader = new ServerPropertiesReader();
        quizView = new QuizView();
        startScene = new StartScene();
        choosingSubjectScene = new ChoosingSubjectScene();
        waitingScene = new WaitingScene();
        resultScene = new ResultScene();

        scene = new Scene(startScene.getDesignLayout(),480,620);
        scene.getStylesheets().add(QuizClient.class.getResource("Style.css").toExternalForm());
        stage.setScene(scene);

        StartSceneController startSceneController = new StartSceneController(startScene,this);
        startSceneController.start();

        choosingSubjectSceneController = new ChoosingSubjectSceneController(choosingSubjectScene,this);
        choosingSubjectSceneController.start();

        WaitingSceneController waitingSceneController = new WaitingSceneController(waitingScene, this);
        waitingSceneController.start();

        quizController = new QuizController(quizView,this);
        quizController.start();

        resultSceneController = new ResultSceneController(resultScene, this);
        resultSceneController.start();
        resultScene.createDynamic(serverPropertiesReader.getRoundsPerGame());

        serverConnection = new ServerConnection(this);
        serverConnection.connect();
        serverConnection.start();

        stage.setOnCloseRequest(t -> {
            stage.close();
            System.exit(0);
        });

        stage.show();
    }

    public void goToChoseSubjectScene(List<String> subjectsList){
        scene.setRoot(choosingSubjectScene.getDesignLayout());
        choosingSubjectSceneController.showSubjects(subjectsList);

    }

    public QuizController getQuizController() {
        return quizController;
    }

    public void goToQuizScene(List<QuizItem> questions) {
        scene.setRoot(quizView.getDesignLayout());
        quizController.loadQuestions(questions);

    }

    public void goToWaitingScene(){
        scene.setRoot(waitingScene.getDesignLayout());
    }

    public void goToResultScene(){
        scene.setRoot(resultScene.getDesignLayout());
    }

    public ServerConnection getServerConnection(){
        return serverConnection;
    }

    public StartScene getStartScene() {
        return startScene;
    }

    public ResultScene getResultScene() {
        return resultScene;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
