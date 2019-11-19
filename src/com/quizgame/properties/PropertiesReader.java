package com.quizgame.properties;


import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesReader {
    protected Properties p = new Properties();

    public void loadProperties(String path){

        try {
            p.load(new FileInputStream(path));
        } catch (Exception e) {
            System.out.println(path+" can not be found.");
        }
    }



}
