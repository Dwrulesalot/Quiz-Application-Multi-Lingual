package com.example.quiz_application_multi_lingual;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ChangeMaxQuestionsDialogFragment extends DialogFragment {

    public DialogClickListener listener;
    public static final String tag = "tag";

    public interface DialogClickListener{
        void dialogListenerOkClicked(int input);
        void dialogListenerCancelClicked();
    }

    public ChangeMaxQuestionsDialogFragment() {
    }

    //New Instance of a ChangeMaxQuestionsDialogFragment Fragment (the popup when you click to change max questions)
    public static ChangeMaxQuestionsDialogFragment newInstance() {
        ChangeMaxQuestionsDialogFragment fragment = new ChangeMaxQuestionsDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_change_max_questions_dialog, container, false);
        EditText editText = v.findViewById(R.id.maxQuestionInput);

        Button ok = v.findViewById(R.id.fragmentDialogOkButton);
        ok.setOnClickListener(new View.OnClickListener(){
            //OnClick that sends user input to MainActivity listener
            @Override
            public void onClick(View view){
                if (editText.getText().toString().isEmpty()){
                    listener.dialogListenerOkClicked(-1);
                }
                else if (Integer.parseInt(editText.getText().toString())<1 || Integer.parseInt(editText.getText().toString())>9 ){
                    listener.dialogListenerOkClicked(-1);
                }
                else{
                    listener.dialogListenerOkClicked(Integer.parseInt(editText.getText().toString()));
                    dismiss();
                }
            }
        });
        Button cancel = v.findViewById(R.id.fragmentDialogCancelButton);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                listener.dialogListenerCancelClicked();
                dismiss();
            }
        });
        return v;
    }
}