package com.quizgame;

import com.quizgame.properties.ClientPropertiesReader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ClientPropertiesReader clientPropertiesReader = new ClientPropertiesReader();


    public void connect() throws IOException{
        socket = new Socket(clientPropertiesReader.getHostName(),clientPropertiesReader.getPortNr() );
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

    }


    public void sendNameToServer(String name){
        TransferObject transferObject = new TransferObject();
        transferObject.text = name;
        try{
        out.writeObject(transferObject);
        out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
