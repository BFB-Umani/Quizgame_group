package com.quizgame.client;

import com.quizgame.*;
import com.quizgame.properties.ClientPropertiesReader;
import com.quizgame.properties.ServerPropertiesReader;
import javafx.application.Platform;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ServerConnection extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ClientPropertiesReader clientPropertiesReader = new ClientPropertiesReader();
    private ServerPropertiesReader prop = new ServerPropertiesReader();
    private QuizClient quizClient;
    private List<QuizItem> questions;
    private SetNameObject setNameObject = new SetNameObject();
    private List<String> threeSubjects;
    private int counter = 0;


    public ServerConnection(QuizClient quizClient) {
        this.quizClient = quizClient;
    }


    public void connect() throws IOException {
        socket = new Socket(clientPropertiesReader.getHostName(), clientPropertiesReader.getPortNr());
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void sendNameToServer(String name) {
        setNameObject.text = name;
        try {
            out.writeObject(setNameObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSubjectToServer(String subject) {
        ChosenSubjectObject chosenSubjectObject = new ChosenSubjectObject();
        chosenSubjectObject.subject = subject;
        try {
            out.writeObject(chosenSubjectObject);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendResultComplete(boolean done) {
        try {
            out.writeObject(done);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRoundComplete(int score) {
        try {
            out.writeObject(score);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendChat(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SetNameObject getSetNameObject() {
        return setNameObject;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Object object = in.readObject();
                if (object instanceof ChooseSubjectObject) {
                    threeSubjects = ((ChooseSubjectObject) object).subjects;
                    System.out.println("im here");
                    Platform.runLater(() -> quizClient.goToChoseSubjectScene(threeSubjects));
                } else if (object instanceof QuestionsBySubjectObject) {
                    questions = ((QuestionsBySubjectObject) object).questions;
                    Platform.runLater(() -> quizClient.goToQuizScene(questions));
                } else if (object instanceof Map) {
                    Map<String, Integer> stats = (Map<String, Integer>) object;
                    String firstKey = stats.keySet().stream().findFirst().get();
                    int firstValue = stats.get(firstKey);

                    quizClient.getResultScene().getPlayerTwoText().setText(firstKey);
                    quizClient.getResultScene().getResultButton().get(counter).getPlayer2Score().setText(String.valueOf(firstValue));

                    counter++;
                    if(counter == prop.getRoundsPerGame()) {
                        quizClient.getResultScene().createTotalResultButton();
                        quizClient.getResultScene().getContinueB().setText("quit");
                    }
                    Platform.runLater(() -> quizClient.goToResultScene());

                } else if (object instanceof String) {
                    Platform.runLater(() -> quizClient.getChatScene().getDisplayAllMessages().appendText( (String) object + "\n"));
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
