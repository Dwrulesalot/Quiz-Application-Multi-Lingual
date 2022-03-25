package com.example.quiz_application_multi_lingual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm = getSupportFragmentManager();//this a good example?

    Button trueBtn;
    Button falseBtn;

    ProgressBar quizProgressBar;

    QuestionBankManager questionBankManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizProgressBar=(ProgressBar) findViewById(R.id.progressBar);
        //quizProgressBar.setMax(int);
        //quizProgressBar.setProgress(int);

        //questionBankManager.currentQuestion //need to change the fragment values here using the manager

        //below is an example, make work for this assignment
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.QuestionFragmentContainer2, QuestionFragment.class, null)
                .commit();
    }
}