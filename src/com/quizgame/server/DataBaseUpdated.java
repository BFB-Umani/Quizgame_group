package com.quizgame.server;

import com.quizgame.QuizItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DataBaseUpdated {
    private List<QuizItem> quizItems = new ArrayList<>();

    public DataBaseUpdated() {
        loadQuestionsFromFile();
    }

    public void loadQuestionsFromFile() {
        String input;
        String[] parts;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/questions.txt"));
            while ((input = bufferedReader.readLine()) != null) {
                input = input.trim();
                parts = input.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                String[] allAnswers = parts[2].split("\\*");
                QuizItem quizItem = new QuizItem(parts[0].trim(), parts[1].trim(), Arrays.asList(allAnswers), parts[3].trim());
                quizItems.add(quizItem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getSubjectList() {
        List<String> subjectList = new ArrayList<>();
        for (int i = 0; i < quizItems.size(); i++) {
            String subject = quizItems.get(i).getSubject();
            if (!subjectList.contains(subject)) {
                subjectList.add(subject);
            }
        }
        return subjectList;
    }


    public List<QuizItem> getItemsBySubject(String subjectInput) {
        List <QuizItem> questions = quizItems.stream().filter(q -> q.getSubject().equals(subjectInput)).collect(Collectors.toList());
        Collections.shuffle(questions);
        return questions;
    }


}
