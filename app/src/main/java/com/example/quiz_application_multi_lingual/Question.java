package com.example.quiz_application_multi_lingual;

//
public class Question {

    int textID;
    boolean answer;
    int colorID;

    public Question() {
        textID = 0;
        answer = false;
        colorID = 0;
    }
    public Question(int t, boolean a, int c) {
        textID = t;
        answer = a;
        colorID = c;
    }
}
