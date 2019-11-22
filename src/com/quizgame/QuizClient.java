package com.quizgame;

import com.quizgame.Controller.*;
import com.quizgame.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class QuizClient extends Application {

    private static int PORT = 12345;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Scene scene;
    private ChoosingSubjectScene choosingSubjectScene;
    private WaitingScene waitingScene;
    private ResultScene resultScene;
    private QuizView quizView;
    private QuizClient quizClient;
    private Object quest;
    private QuizController quizController;
    private ResultSceneController resultSceneController;


    public QuizClient() {    //NO TOUCH THIS!!!!
    }

    public QuizClient(String ip) {
        try {
            socket = new Socket(ip, PORT);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {

        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        quizClient = new QuizClient("127.0.0.1");


        stage.setTitle("Quizgame");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/images/quizIcon.png"));

        quizView = new QuizView();
        StartScene startScene = new StartScene();
        choosingSubjectScene = new ChoosingSubjectScene();
        waitingScene = new WaitingScene();
        resultScene = new ResultScene();

        scene = new Scene(startScene.getDesignLayout(), 480, 620);
        scene.getStylesheets().add(QuizClient.class.getResource("Style.css").toExternalForm());
        stage.setScene(scene);

        StartSceneController startSceneController = new StartSceneController(startScene, this);
        startSceneController.start();


        ChoosingSubjectSceneController choosingSubjectSceneController = new ChoosingSubjectSceneController(choosingSubjectScene, this);
        choosingSubjectSceneController.start();

        WaitingSceneController waitingSceneController = new WaitingSceneController(waitingScene);
        waitingSceneController.start();

        resultSceneController = new ResultSceneController(resultScene, this);
        resultSceneController.start();

        quizController = new QuizController(quizView, this);
        quizController.start();

        stage.show();


    }

    public void sendMsg(String output) {
        System.out.println(output);
        try {
            quizClient.out.writeObject(output);
            getMsg();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPoints(int points) {
        try {
            resultSceneController.loadScore(points);
            quizClient.out.writeObject(points);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMsg() {
        Object fromServer;
        try {
            while ((fromServer = quizClient.in.readObject()) != null) {
                if (fromServer instanceof List) {
                    System.out.println("I got Quizitem");
                    quest = fromServer;
                    System.out.println(quest);
                    quizController.loadQuestion(quest);

                } else if (fromServer instanceof String) {
                    String message = (String) fromServer;
                    System.out.println(message);
                    System.out.println("I got STring item");
                } else if (fromServer instanceof int[]) {
                    int[] gameInfo = (int[]) fromServer;
                    System.out.println(gameInfo[0]);
                    System.out.println(gameInfo[1]);
                    System.out.println("I got int item");
                    quizController.loadGameInfo(gameInfo);
                } else if (fromServer instanceof Integer) {
                    System.out.println("I got a single int item");
                }
                break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void goToChoseSubjectScene() {
        scene.setRoot(choosingSubjectScene.getDesignLayout());
    }

    public void goToQuizScene() {
        scene.setRoot(quizView.getDesignLayout());
    }

    public void goToResult() {
        scene.setRoot(resultScene.getDesignLayout());
    }

    public static void main(String[] args) {
        launch(args);
    }

}

