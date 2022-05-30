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

public class MainActivity extends AppCompatActivity implements ChangeMaxQuestionsDialogFragment.DialogClickListener{
    FragmentManager fm = getSupportFragmentManager();
    Button trueBtn;
    Button falseBtn;
    ProgressBar quizProgressBar;
    QuestionBankManager questionBankManager;
    int maxQuestions;
    int progress;
    int correctAnswers;
    AlertDialog.Builder builder;
    FileStorageManager storageManager;

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

    //Checks if the correct answer was clicked (true/false)
    //Handles dialog for Quiz results
    private void answerClicked(boolean answer) {
        progress++;
        quizProgressBar.setProgress(progress);

        if(!(progress>maxQuestions)){
            if(questionBankManager.checkAnswer(answer)){
                Toast.makeText(getApplicationContext(), R.string.correct, Toast.LENGTH_SHORT).show();
                correctAnswers++;
            }
            else {
                Toast.makeText(getApplicationContext(), R.string.incorrect, Toast.LENGTH_SHORT).show();
            }
            changeQuestion();
        }

        if(progress==maxQuestions) {
            builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.resultsTitle);
            builder.setMessage(getString(R.string.resultsMSG1)+" "+correctAnswers+" "+getString(R.string.resultsMSG2)+" "+maxQuestions);
            builder.setNegativeButton(R.string.resultsIgnore,new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    resetQuiz();
                }
            });
            builder.setPositiveButton(R.string.resultsSave,  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onSaveClick();
                }
            });
            builder.show();
        }
    }

    //Shows the currentQuestion from the questionBankManager on the fragment
    public void getQuestion(){
        FragmentQuestion fragmentQuestion = FragmentQuestion.newInstance(questionBankManager.currentQuestion.textID, questionBankManager.currentQuestion.colorID);
        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.beginTransaction().replace(R.id.QuestionFragmentContainer, fragmentQuestion).commit();
    }

    //Changes the currentQuestion from the questionBankManager and displays the question on our fragment
    public void changeQuestion(){
        questionBankManager.newQuestion();
        FragmentQuestion fragmentQuestion = FragmentQuestion.newInstance(questionBankManager.currentQuestion.textID, questionBankManager.currentQuestion.colorID);
        fm.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fm.beginTransaction().replace(R.id.QuestionFragmentContainer, fragmentQuestion).commit();
    }

    //Saves/Stores the current quiz's score.
    public void onSaveClick() {
        String average = storageManager.getAverage(this);
        String[] bothNum;
        bothNum = average.split("/", 2);
        int lifeTimeCorrectAnswers = Integer.parseInt(bothNum[0]);
        int lifeTimeTotalQuestions = Integer.parseInt(bothNum[1]);
        lifeTimeCorrectAnswers+=correctAnswers;
        lifeTimeTotalQuestions+=maxQuestions;
        average = Integer.toString(lifeTimeCorrectAnswers)+"/"+Integer.toString(lifeTimeTotalQuestions);
        storageManager.updateAverage(this, average);
        resetQuiz();
    }

    //Resets the current quiz
    public void resetQuiz(){
        questionBankManager = new QuestionBankManager(maxQuestions);
        getQuestion();
        progress = 0;
        correctAnswers = 0;
        quizProgressBar.setMax(maxQuestions);
        quizProgressBar.setProgress(progress);
    }

    //Menu Creation
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    //Menu Functionality for three options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem){
        super.onOptionsItemSelected(menuItem);
        builder = new AlertDialog.Builder(this);
        switch (menuItem.getItemId()){
            case R.id.getAverage:
                builder.setTitle(R.string.getAverageTitle);
                builder.setMessage(getString(R.string.getAverageMessage)+" " + storageManager.getAverage(this));
                builder.setCancelable(true);
                builder.setNegativeButton(R.string.ok,null);
                builder.show();
                break;
            case R.id.changeNumberOfQuestions:
                onClickMenuChangeMaxQuestions();
                break;
            case R.id.resetSavedAverage:
                builder.setTitle(R.string.resetAverageTitle);
                builder.setMessage(R.string.resetAverageMessage);
                builder.setCancelable(true);
                builder.setPositiveButton(R.string.yes,  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        storageManager.resetAverage(MainActivity.this);
                    }
                });
                builder.setNegativeButton(R.string.no,null);
                builder.show();
                break;
        }
        return true;
    }

    //Makes a new Instance of ChangeMaxQuestionsDialogFragment and displays over the activity
    public void onClickMenuChangeMaxQuestions(){
        ChangeMaxQuestionsDialogFragment dialogFragment = ChangeMaxQuestionsDialogFragment.newInstance();
        dialogFragment.show(fm, ChangeMaxQuestionsDialogFragment.tag);
        dialogFragment.listener = this;
    }

    //ChangeMaxQuestionsDialogFragment listener
    //If user input is a # 1-9 sets the max questions to the input
    @Override
    public void dialogListenerOkClicked(int input) {
        Log.d("Ass3", "MainActivity.dialogListenerOkClicked(int input): input= "+input);
        if(input==-1){
            builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.changeMaxQuestionsErrorTitle);
            builder.setMessage(R.string.changeMaxQuestionsErrorMessage);
            builder.setCancelable(true);
            builder.setNegativeButton(R.string.ok,null);
            builder.show();
        }else{
            maxQuestions = input;
            resetQuiz();
        }
    }

    //ChangeMaxQuestionsDialogFragment listener
    @Override
    public void dialogListenerCancelClicked() {
    }
}