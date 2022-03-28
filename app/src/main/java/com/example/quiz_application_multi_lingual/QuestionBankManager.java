package com.example.quiz_application_multi_lingual;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class QuestionBankManager {

    ArrayList<Question> QuestionBank = new ArrayList<Question>();
    //maybe should make a secondary arraylist to remove questions from?

    //these Strings/questions need to be in the string resource list in future with each of their translations
    Question q1 = new Question(R.string.question1, true, 0);
    Question q2 = new Question(R.string.question2, false, 0);
    Question q3 = new Question(R.string.question3, false, 0);

    Question currentQuestion;

    int[] colorArray = {R.color.purple_200, R.color.purple_500, R.color.purple_700, R.color.teal_200, R.color.teal_700, R.color.red, R.color.pink};

    Random randomGenerator = new Random();

    public QuestionBankManager(){
        //will eventually need to save individual question data and also pull from saved data here?

        //temp
        QuestionBank.add(q1);
        QuestionBank.add(q2);
        QuestionBank.add(q3);

        //newQuestion();

    }

    public boolean checkAnswer(boolean a){
        if(a==currentQuestion.answer){
            return true;
        }
        else {
            return false;
        }
    }

    public int colorChange(int c){

        int newColor = randomGenerator.nextInt(7);

        //if it's a different color
        if(colorArray[newColor]!=c){
            return colorArray[newColor];
        }
        else {
            //recursive till it's a different color than originally - hopefully not overkill/error prone
            return colorChange(c);
        }

    }

    public void newQuestion(){

        //should check if array is empty first
        int randomNewQuestion = randomGenerator.nextInt(QuestionBank.size());
        currentQuestion = QuestionBank.get(randomNewQuestion);
        QuestionBank.remove(randomNewQuestion);
        currentQuestion.colorID = colorChange(currentQuestion.colorID);

        Log.d("Ass3", "QuestionBankManager.newQuestion(): QuestionBank.size()= "+QuestionBank.size());

        /*
        //loops through ArrayList to first remove
        for(int i = 0; i<QuestionBank.size(); i++){
            if(currentQuestion==QuestionBank.get(i)){
                QuestionBank.remove(i);
            }
        }

         */


    }

}
