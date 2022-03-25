package com.example.quiz_application_multi_lingual;

import java.util.ArrayList;

public class QuestionBankManager {

    ArrayList<Question> QuestionBank;

    Question q1 = new Question("Toronto is the capital city of Ontario.", true,"purple_200");
    Question q2 = new Question("Montreal is the capital city of Quebec.", false,"purple_500");
    Question q3 = new Question("Ottawa is the capital city of Quebec.", true,"purple_700");

    Question currentQuestion;
    int currentProgress;
    int quizMaxQuestions;//do I need this?

    public QuestionBankManager(){
        //will eventually need to save individual question data and also pull from saved data here

        //temp
        QuestionBank.add(q1);
        QuestionBank.add(q2);
        QuestionBank.add(q3);

        currentProgress=0;
        quizMaxQuestions=3;

        currentQuestion = q1;

    }

    public boolean checkAnswer(boolean a){
        if(a==currentQuestion.answer){
            return true;
        }
        else {
            return false;
        }
    }

    public void changeQuestion(){
        //how to change this at random without reusing questions?
        //if currentQuestion=
    }


}
