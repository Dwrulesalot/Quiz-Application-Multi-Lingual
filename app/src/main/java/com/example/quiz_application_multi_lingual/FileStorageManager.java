package com.example.quiz_application_multi_lingual;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileStorageManager {
    static String fileName = "Average.txt";
    FileOutputStream fos;
    FileInputStream fis;

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
    //todo fix this
    public String getAverage(Activity context){
        String contents;
        String line;
        try {
            fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);//what dis
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)){
               /*
                line = reader.readLine();
                while (line!= null) {
                    stringBuilder.append(line).append()
                }
                */

            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                //contents = StringBuilder.toString();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //temp
        String average = "0/0";
        return average;
    }
    public void resetAverage(Activity context){
        updateAverage(context, "0/0");
    }
}
