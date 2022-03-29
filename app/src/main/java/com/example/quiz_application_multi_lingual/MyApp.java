package com.example.quiz_application_multi_lingual;

import android.app.Application;

public class MyApp extends Application {

    QuestionBankManager questionBankManager;

    int maxQuestions;
    int progress;
    int correctAnswers;

    FileStorageManager storageManager = new FileStorageManager();

    public MyApp(){
        questionBankManager = new QuestionBankManager();

        maxQuestions = 3;
        progress = 0;
        correctAnswers = 0;

    }

    public void resetQuestionBank(){
        questionBankManager = new QuestionBankManager();

        maxQuestions = 3;
        progress = 0;
        correctAnswers = 0;
    }

    public void resetQuestionBank(int numMaxQuestions){

        questionBankManager = new QuestionBankManager(numMaxQuestions);

        maxQuestions = numMaxQuestions;
        progress = 0;
        correctAnswers = 0;
    }

}
