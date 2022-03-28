package com.example.quiz_application_multi_lingual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm = getSupportFragmentManager();//this a good example?

    Button trueBtn;
    Button falseBtn;

    ProgressBar quizProgressBar;

    QuestionBankManager questionBankManager;

    //set max from menu in future and add functionality to QuestionBankManager
    int maxQuestions;
    int progress;

    int correctAnswers = 0;

    //need to save this later
    int lifeTimeCorrectAnswers;
    int lifeTimeTotalQuestions;



    //todo need to save state to ensure it works when being rotated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionBankManager = ((MyApp)getApplication()).questionBankManager;
        maxQuestions = ((MyApp)getApplication()).maxQuestions;
        progress = ((MyApp)getApplication()).progress;


        quizProgressBar=(ProgressBar) findViewById(R.id.progressBar);
        quizProgressBar.setMax(maxQuestions);
        quizProgressBar.setProgress(progress);

        if(progress==0) {
            changeQuestion();//this gets called when state changes, need to fix this /add if statement to avoid - figure out way to run only once
        }
        trueBtn = (Button) findViewById(R.id.trueButton);
        trueBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answerClicked(true, v);
            }
        });

        falseBtn = (Button) findViewById(R.id.falseButton);
        falseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answerClicked(false, v);
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("quizProgress", progress);
        savedInstanceState.putInt("quizMaxQuestions", maxQuestions);
        savedInstanceState.putInt("quizScore", correctAnswers);

        //savedInstanceState.putAll(savedInstanceState);

        //savedInstanceState.putInt("currentQuestionID", questionBankManager.currentQuestion.textID);//does this need to be here?
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        progress = savedInstanceState.getInt("quizProgress");
        maxQuestions = savedInstanceState.getInt("quizMaxQuestions");
        correctAnswers = savedInstanceState.getInt("quizScore");



        quizProgressBar.setMax(maxQuestions);
        quizProgressBar.setProgress(progress);

    }


    private void answerClicked(boolean answer, View view) {

        //todo:
        // change question/color
        // summarize/save current quiz progress

        progress++;
        quizProgressBar.setProgress(progress);

        if(!(progress>=maxQuestions)){
            //returns true if correct answer, false if incorrect
            if(questionBankManager.checkAnswer(answer)){
                Toast.makeText(getApplicationContext(), "Correct!!!", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(getApplicationContext(), "Incorrect.", Toast.LENGTH_SHORT).show();

            }

            changeQuestion();
        }
        //Give dialogue popup with stats
        else {
            //
        }


    }

    public void changeQuestion(){
        questionBankManager.newQuestion();

        FragmentQuestion fragmentQuestion = FragmentQuestion.newInstance(questionBankManager.currentQuestion.textID, questionBankManager.currentQuestion.colorID);
        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.beginTransaction().replace(R.id.QuestionFragmentContainer, fragmentQuestion).commit();

    }



}