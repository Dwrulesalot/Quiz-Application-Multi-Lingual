package com.example.quiz_application_multi_lingual;

import android.app.Activity;
import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorageManager {
    static String fileName = "Average.txt";
    FileOutputStream fos;

    public void updateAverage(Activity context, String newAverage){
        try{
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(newAverage.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    public String getAverage(){
        //temp 
        String average = "0/0";
        return average;
    }
    public void resetAverage(Activity context){
        updateAverage(context, "0/0");
    }
}
