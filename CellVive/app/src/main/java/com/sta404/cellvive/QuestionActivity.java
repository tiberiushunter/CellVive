package com.sta404.cellvive;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

/**
 Name: QuestionActivity
 */
public class QuestionActivity extends Activity {
    //A = 1 ... D = 4
    int correctAnswer;

    TextView txtVQuestion;
    Button btnAnswerA, btnAnswerB, btnAnswerC, btnAnswerD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_question);

        txtVQuestion = (TextView) findViewById(R.id.questionText);
        btnAnswerA = (Button) findViewById(R.id.btnAnswerA);
        btnAnswerB = (Button) findViewById(R.id.btnAnswerB);
        btnAnswerC = (Button) findViewById(R.id.btnAnswerC);
        btnAnswerD = (Button) findViewById(R.id.btnAnswerD);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/scifi2k2.ttf");

        txtVQuestion.setTypeface(tf);
        btnAnswerA.setTypeface(tf);
        btnAnswerB.setTypeface(tf);
        btnAnswerC.setTypeface(tf);
        btnAnswerD.setTypeface(tf);

        generateQuestion();
    }

    public void generateQuestion(){
        AssetManager am = getAssets();
        String line;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        Random rand = new Random();
        int randLine = rand.nextInt(8);
        int currentLine = 0;

        try{
            inputStream = am.open("files/questions.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            System.out.println(randLine);
            while ((line = bufferedReader.readLine()) != null) {
                if(currentLine == randLine) {
                    String[] text = line.split(",");
                    txtVQuestion.setText(text[0]);
                    btnAnswerA.setText(text[1]);
                    btnAnswerB.setText(text[2]);
                    btnAnswerC.setText(text[3]);
                    btnAnswerD.setText(text[4]);
                    correctAnswer = Integer.parseInt(text[5]);
                }
                currentLine++;
            }
        } catch (FileNotFoundException e){
            Log.d("ID", e.getMessage());
        } catch (IOException e) {
            Log.d("ID", e.getMessage());
        }
    }

    public void onClickAnswerA(View v) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if(correctAnswer == 1){
            CharSequence text = "Correct!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            setResult(Activity.RESULT_OK);}
        else {
            CharSequence text = "Incorrect!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }
    public void onClickAnswerB(View v) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if(correctAnswer == 2){
            CharSequence text = "Correct!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            setResult(Activity.RESULT_OK);}
        else {
            CharSequence text = "Incorrect!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }
    public void onClickAnswerC(View v) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if(correctAnswer == 3){
            CharSequence text = "Correct!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            setResult(Activity.RESULT_OK);}
        else {
            CharSequence text = "Incorrect!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }
    public void onClickAnswerD(View v) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        if(correctAnswer == 4){
            CharSequence text = "Correct!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            setResult(Activity.RESULT_OK);}
        else {
            CharSequence text = "Incorrect!";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }

}
