package com.example.quiz_application_multi_lingual;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class QuestionBankManager {

    ArrayList<Question> QuestionBank = new ArrayList<Question>();
    ArrayList<Question> allQuestions = new ArrayList<Question>();

    Question q1 = new Question(R.string.question1, true, 0);
    Question q2 = new Question(R.string.question2, false, 0);
    Question q3 = new Question(R.string.question3, false, 0);
    Question q4 = new Question(R.string.question4, true, 0);
    Question q5 = new Question(R.string.question5, true, 0);
    Question q6 = new Question(R.string.question6, true, 0);
    Question q7 = new Question(R.string.question7, true, 0);
    Question q8 = new Question(R.string.question8, true, 0);
    Question q9 = new Question(R.string.question9, true, 0);

    Question currentQuestion;
    int[] colorArray = {R.color.purple_200, R.color.purple_500, R.color.purple_700, R.color.teal_200, R.color.teal_700, R.color.red, R.color.pink};
    Random randomGenerator = new Random();

    //Default constructor
    public QuestionBankManager(){
        resetQuestionArray();
        addToQuestionBank(3);
        newQuestion();
    }

    //Constructor that takes a # of questions to have in the quiz
    public QuestionBankManager(int questionAmount){
        resetQuestionArray();
        addToQuestionBank(questionAmount);
        newQuestion();
    }


    public void resetQuestionArray (){
        allQuestions.clear();
        allQuestions.add(q1);
        allQuestions.add(q2);
        allQuestions.add(q3);
        allQuestions.add(q4);
        allQuestions.add(q5);
        allQuestions.add(q6);
        allQuestions.add(q7);
        allQuestions.add(q8);
        allQuestions.add(q9);
    }

    //Uses randomGenerator to randomize order of questions
    //Ensures no duplicate questions in a quiz
    public void addToQuestionBank(int questionAmount){
        int randomNum;

        for(int i=0; i<questionAmount;i++){
            randomNum = randomGenerator.nextInt(allQuestions.size());
            Question newQuestion = allQuestions.get(randomNum);
            QuestionBank.add(newQuestion);
            allQuestions.remove(randomNum);
        }
        resetQuestionArray();
    }

    //Verifies if answer is correct
    public boolean checkAnswer(boolean a){
        if(a==currentQuestion.answer){
            return true;
        }
        else {
            return false;
        }
    }

    //Changes the Current question background to a different color on question change
    public int colorChange(int oldColorID){
        int newColor = randomGenerator.nextInt(colorArray.length);
        //if it's a different color
        if(colorArray[newColor]!=oldColorID){
            return colorArray[newColor];
        }
        else {
            //recursive until it's a different color
            return colorChange(oldColorID);
        }
    }

    //Replaces current question with a new question that has not been used yet
    public void newQuestion(){
        //Checks if array is empty first
        if(QuestionBank.size()!=0) {
            int randomNewQuestion = randomGenerator.nextInt(QuestionBank.size());
            int oldColorID;
            //If this isn't the first question
            if(currentQuestion!=null) {
                oldColorID = currentQuestion.colorID;
            }
            else{
                oldColorID = colorArray[0];
            }
            currentQuestion = QuestionBank.get(randomNewQuestion);
            QuestionBank.remove(randomNewQuestion);
            currentQuestion.colorID = colorChange(oldColorID);
        }
    }
}
