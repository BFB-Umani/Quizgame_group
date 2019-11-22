package com.quizgame;

import com.quizgame.Controller.*;
import com.quizgame.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;

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

        ResultSceneController resultSceneController = new ResultSceneController(resultScene);
        resultSceneController.start();

        QuizController quizController = new QuizController(quizView);
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

    public void getMsg() {
        Object fromServer;
        String fromSrv;
        try {
            while ((fromServer = quizClient.in.readObject()) != null) {
                if (fromServer instanceof QuizItem) {
                    QuizItem question = (QuizItem) fromServer;


                } else if (fromServer instanceof String) {
                    String message = (String) fromServer;
                    System.out.println("Client: " + message);
                } else if (fromServer instanceof Integer[]) {
                    Integer[] points = (Integer[]) fromServer;
                    System.out.println(points[0]);
                }
                break;
            }
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


        public void goToChoseSubjectScene () {
            scene.setRoot(choosingSubjectScene.getDesignLayout());
        }

        public void goToQuizScene () {
            scene.setRoot(quizView.getDesignLayout());
        }

        public static void main (String[]args){
            launch(args);
        }


    }

