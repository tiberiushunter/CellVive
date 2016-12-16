package com.sta404.cellvive;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends Activity {

    String question = "Which of these is an animal?";

    String answerA = "Cactus";
    String answerB = "Bear";
    String answerC = "Tree";
    String answerD = "Mushroom";

    //A = 1 ... D = 4
    int correctAnswer = 2;

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

        TextView txtVQuestion = (TextView) findViewById(R.id.questionText);
        Button btnAnswerA = (Button) findViewById(R.id.btnAnswerA);
        Button btnAnswerB = (Button) findViewById(R.id.btnAnswerB);
        Button btnAnswerC = (Button) findViewById(R.id.btnAnswerC);
        Button btnAnswerD = (Button) findViewById(R.id.btnAnswerD);

        Typeface tfTitle = Typeface.createFromAsset(getAssets(), "fonts/scifi2ku.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/scifi2k2.ttf");

        txtVQuestion.setTypeface(tfTitle);
        btnAnswerA.setTypeface(tf);
        btnAnswerB.setTypeface(tf);
        btnAnswerC.setTypeface(tf);
        btnAnswerD.setTypeface(tf);

        txtVQuestion.setText(question);
        btnAnswerA.setText(answerA);
        btnAnswerB.setText(answerB);
        btnAnswerC.setText(answerC);
        btnAnswerD.setText(answerD);
    }


    public void onClickAnswerA(View v) {
        if(correctAnswer == 1)
            setResult(Activity.RESULT_OK);
        else
            setResult(Activity.RESULT_CANCELED);
        finish();
    }
    public void onClickAnswerB(View v) {
        if(correctAnswer == 2)
            setResult(Activity.RESULT_OK);
        else
            setResult(Activity.RESULT_CANCELED);
        finish();
    }
    public void onClickAnswerC(View v) {
        if(correctAnswer == 3)
            setResult(Activity.RESULT_OK);
        else
            setResult(Activity.RESULT_CANCELED);
        finish();
    }
    public void onClickAnswerD(View v) {
        if(correctAnswer == 4)
            setResult(Activity.RESULT_OK);
        else
            setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
