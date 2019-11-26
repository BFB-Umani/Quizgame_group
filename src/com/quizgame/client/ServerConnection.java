package com.quizgame.client;

import com.quizgame.*;
import com.quizgame.properties.ClientPropertiesReader;
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
    private QuizClient quizClient;
    private List<QuizItem> questions;
    private SetNameObject setNameObject = new SetNameObject();



    public ServerConnection(QuizClient quizClient){
        this.quizClient = quizClient;
    }


    public void connect() throws IOException{
        socket = new Socket(clientPropertiesReader.getHostName(),clientPropertiesReader.getPortNr() );
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

    }


    public void sendNameToServer(String name){
        setNameObject.text = name;
        try{
        out.writeObject(setNameObject);
            System.out.println("Sending name to server");
        out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendSubjectToServer(String subject){
        ChosenSubjectObject chosenSubjectObject = new ChosenSubjectObject();
        chosenSubjectObject.subject = subject;
        try{
            out.writeObject(chosenSubjectObject);
            System.out.println("sending subject to server");
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void sendRoundComplete(int score) {
        try {
            out.writeObject(score);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while (true) {

            try {
                Object object = in.readObject();
                if(object instanceof ChooseSubjectObject){
                    List<String> threeSubjects = ((ChooseSubjectObject) object).subjects;
                    System.out.println("Client el subjects");
                    Platform.runLater(() -> quizClient.goToChoseSubjectScene(threeSubjects)); // main thread runs this when it has time.
                }
                else if(object instanceof QuestionsBySubjectObject){
                    questions =((QuestionsBySubjectObject) object).questions;
                    System.out.println("Client el questions");
                    Platform.runLater(() -> quizClient.goToQuizScene(questions)); // just in case that main thread is busy. Thank you google!
                }
                if(object instanceof Map) {
                    System.out.println("Client el Map");
                    Map<String, Integer> stats = (Map<String, Integer>) object;
                    String firstKey = stats.keySet().stream().findFirst().get();
                    int firstValue = stats.get(firstKey);
                    Platform.runLater(() -> quizClient.getResultScene().getPlayerTwoText().setText(firstKey) ); // Tillfällig
                    Platform.runLater(()-> quizClient.getResultScene().getRoundOneResult2().setText(firstValue + "/x")); // Tillfällig
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
