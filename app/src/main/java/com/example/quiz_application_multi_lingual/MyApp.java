package com.example.quiz_application_multi_lingual;

import android.app.Application;

public class MyApp extends Application {

    //Quiz defaults
    QuestionBankManager questionBankManager = new QuestionBankManager();
    int maxQuestions = 3;
    int progress = 0;
    int correctAnswers = 0;
    FileStorageManager storageManager = new FileStorageManager();

    public MyApp(){
    }
}
