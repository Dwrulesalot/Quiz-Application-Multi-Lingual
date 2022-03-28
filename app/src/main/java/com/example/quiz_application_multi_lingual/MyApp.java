package com.example.quiz_application_multi_lingual;

import android.app.Application;

public class MyApp extends Application {

    QuestionBankManager questionBankManager = new QuestionBankManager();

    int maxQuestions = 3;
    int progress = 0;

}
