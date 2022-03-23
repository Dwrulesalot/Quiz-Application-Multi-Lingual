package com.example.quiz_application_multi_lingual;

public class Question {
    String text;
    boolean answer;
    //can I use a different data type here for color?
    String color;

    public Question() {
        text = "";
        answer = false;
        color = "";
    }
    public Question(String t, boolean a, String c) {
        text = t;
        answer = a;
        color = c;
    }
}
