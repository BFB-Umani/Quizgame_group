package com.quizgame.properties;

public class ClientPropertiesReader extends PropertiesReader {
    public ClientPropertiesReader(){
        loadProperties("resources/config/client.properties");
    }
    public String getHostName(){
        return p.getProperty("host_name","127.0.0.1");
    }
    public int getPortNr(){
        return Integer.parseInt(p.getProperty("port_nr","12345"));
    }
}
