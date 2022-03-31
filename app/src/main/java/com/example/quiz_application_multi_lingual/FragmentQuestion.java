package com.example.quiz_application_multi_lingual;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentQuestion extends Fragment {

    private static final String ARG_QUESTION_ID = "questionIDParam";
    private static final String ARG_COLOR_ID = "colorIDParam";

    int questionID=0;
    int colorID=0;

    public FragmentQuestion() {
    }

    public static FragmentQuestion newInstance(int questionResourceID, int colorResourceID) {
        FragmentQuestion fragment = new FragmentQuestion();
        Bundle args = new Bundle();
        args.putInt(ARG_QUESTION_ID, questionResourceID);
        args.putInt(ARG_COLOR_ID, colorResourceID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionID = getArguments().getInt(ARG_QUESTION_ID);
            colorID = getArguments().getInt(ARG_COLOR_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        TextView questionTextView = v.findViewById(R.id.questionTextView);
        ConstraintLayout constraintLayout = v.findViewById(R.id.currentQuestionLayout);
        constraintLayout.setBackgroundResource(colorID);
        questionTextView.setText(questionID);
        return v;
    }
}