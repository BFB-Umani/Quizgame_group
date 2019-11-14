package com.quizgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBase {
    public List<Question> questionList = new ArrayList<>();

    public void loadQuestions(){
    questionList.add(new Question("Sport", "Where will Olympic Game 2022 be held?", "Tokyo", Arrays.asList("Beijing","New York","Stockholm")));
    questionList.add(new Question("Animal","Which animal does not belong in this group?","Fish",Arrays.asList("Dog","Cat","Pig")));
    questionList.add(new Question("Color","Which color does not belong in this group","Black",Arrays.asList("Yellow","Red","Orange")));
    questionList.add(new Question("Food","Which food contains the least sugar?","Apple",Arrays.asList("Chocolate","Condensed milk","Juice")));
    }

}
