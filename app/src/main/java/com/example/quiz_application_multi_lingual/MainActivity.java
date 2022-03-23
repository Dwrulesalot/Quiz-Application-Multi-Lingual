package com.example.quiz_application_multi_lingual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //below is an example, make work for this assignment
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.QuestionFragmentContainer2, QuestionFragment.class, null)
                .commit();
    }
}