package com.example.quiz_application_multi_lingual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm = getSupportFragmentManager();//this a good example?

    Button trueBtn;
    Button falseBtn;

    ProgressBar quizProgressBar;

    QuestionBankManager questionBankManager;

    //
    int maxQuestions = 3;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizProgressBar=(ProgressBar) findViewById(R.id.progressBar);
        //quizProgressBar.setMax(int);
        //quizProgressBar.setProgress(int);

        //questionBankManager.currentQuestion //need to change the fragment values here using the manager

        /*
        example initial fragment

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.QuestionFragmentContainer, FragmentQuestion.class, null)
                .commit();


         */

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



    //simple version to get it just switch to next question
    private void answerClicked(boolean answer, View view) {

        //todo:
        // toast if in/correct answer
        // change question/color
        //update progress bar
        // summarize/save current quiz progress



        //should I do the below in question manager?//how do I change the questions between themselves?
        FragmentQuestion fragmentQuestion = FragmentQuestion.newInstance(R.string.question1, R.color.red);


        FragmentQuestion f = (FragmentQuestion)fm.findFragmentById(R.id.QuestionFragmentContainer);

        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.beginTransaction().add(R.id.QuestionFragmentContainer, fragmentQuestion).commit();




    }
}