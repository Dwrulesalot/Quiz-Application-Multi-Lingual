package com.example.quiz_application_multi_lingual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm = getSupportFragmentManager();

    Button trueBtn;
    Button falseBtn;

    ProgressBar quizProgressBar;

    QuestionBankManager questionBankManager;

    //set max from menu in future and add functionality to QuestionBankManager
    int maxQuestions;
    int progress;
    int correctAnswers;

    //need to save this later
    int lifeTimeCorrectAnswers;
    int lifeTimeTotalQuestions;

    AlertDialog.Builder builder;

    FileStorageManager storageManager;

    //todo need to save state to ensure it works when being rotated
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionBankManager = ((MyApp)getApplication()).questionBankManager;
        maxQuestions = ((MyApp)getApplication()).maxQuestions;
        progress = ((MyApp)getApplication()).progress;
        correctAnswers = ((MyApp)getApplication()).correctAnswers;
        storageManager = ((MyApp)getApplication()).storageManager;

        quizProgressBar=(ProgressBar) findViewById(R.id.progressBar);
        quizProgressBar.setMax(maxQuestions);
        quizProgressBar.setProgress(progress);

        getQuestion();

        trueBtn = (Button) findViewById(R.id.trueButton);
        trueBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answerClicked(true);
            }
        });

        falseBtn = (Button) findViewById(R.id.falseButton);
        falseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answerClicked(false);
            }
        });

        builder = new AlertDialog.Builder(this);
    }

    @Override
    protected void onDestroy() {
    super.onDestroy();
        ((MyApp)getApplication()).maxQuestions = maxQuestions;
        ((MyApp)getApplication()).progress = progress;
        ((MyApp)getApplication()).correctAnswers = correctAnswers;

        ((MyApp)getApplication()).questionBankManager = questionBankManager;
        ((MyApp)getApplication()).storageManager = storageManager;
    }


    private void answerClicked(boolean answer) {

        //todo:
        // summarize/save current quiz progress

        progress++;
        quizProgressBar.setProgress(progress);

        if(!(progress>maxQuestions)){

            if(questionBankManager.checkAnswer(answer)){
                Toast.makeText(getApplicationContext(), "Correct!!!", Toast.LENGTH_SHORT).show();
                correctAnswers++;
            }
            else {
                Toast.makeText(getApplicationContext(), "Incorrect.", Toast.LENGTH_SHORT).show();
            }
            changeQuestion();
        }

        if(progress==maxQuestions) {

            builder = new AlertDialog.Builder(this);
            builder.setTitle("Results");
            builder.setMessage("Your score is: "+correctAnswers+" out of "+maxQuestions);
            builder.setNegativeButton("IGNORE",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resetQuiz();
                }
            });
            builder.setPositiveButton("SAVE",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onSaveClick();
                }
            });
            builder.show();
        }
    }

    public void getQuestion(){
        FragmentQuestion fragmentQuestion = FragmentQuestion.newInstance(questionBankManager.currentQuestion.textID, questionBankManager.currentQuestion.colorID);
        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.beginTransaction().replace(R.id.QuestionFragmentContainer, fragmentQuestion).commit();
    }

    public void changeQuestion(){
        questionBankManager.newQuestion();

        FragmentQuestion fragmentQuestion = FragmentQuestion.newInstance(questionBankManager.currentQuestion.textID, questionBankManager.currentQuestion.colorID);
        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.beginTransaction().replace(R.id.QuestionFragmentContainer, fragmentQuestion).commit();

    }


    public void onSaveClick() {
        //need this to overwrite/save to filesystem
        lifeTimeCorrectAnswers+=correctAnswers;
        lifeTimeTotalQuestions+=maxQuestions;

        //need to get the current average - will be a string
        //split/splice the string and change to two ints
        //add to totals
        //change back to String
        //storageManager.updateAverage(this, newAverage);

        resetQuiz();
    }

    public void resetQuiz(){
        questionBankManager = new QuestionBankManager(maxQuestions);
        getQuestion();
        progress = 0;
        correctAnswers = 0;

        quizProgressBar.setMax(maxQuestions);
        quizProgressBar.setProgress(progress);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem){
        super.onOptionsItemSelected(menuItem);
        builder = new AlertDialog.Builder(this);
        switch (menuItem.getItemId()){
            case R.id.getAverage:
                builder.setTitle("Average");
                builder.setMessage("Your Correct Answers / Total Questions Answered : "+ storageManager.getAverage(this));
                builder.setCancelable(true);
                builder.setNegativeButton("OK",null);
                builder.show();
                break;
            case R.id.changeNumberOfQuestions:
                //todo: Fragment dialog here
                //need write to file for this to work
                break;
            case R.id.resetSavedAverage:
                builder.setTitle("Caution!");
                builder.setMessage("This will permanently delete your previous Average. Are you sure?");
                builder.setCancelable(true);
                builder.setPositiveButton("YES",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        storageManager.resetAverage(MainActivity.this);
                    }
                });
                builder.setNegativeButton("NO",null);
                builder.show();
                break;
        }
        return true;
    }

}