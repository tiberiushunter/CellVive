package com.sta404.cellvive;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends Activity {

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



        TextView question = (TextView) findViewById(R.id.questionText);
        Button btnAnswerA = (Button) findViewById(R.id.btnAnswerA);
        Button btnAnswerB = (Button) findViewById(R.id.btnAnswerB);
        Button btnAnswerC = (Button) findViewById(R.id.btnAnswerC);
        Button btnAnswerD = (Button) findViewById(R.id.btnAnswerD);

        Typeface tfTitle = Typeface.createFromAsset(getAssets(), "fonts/scifi2ku.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/scifi2k2.ttf");

        question.setTypeface(tfTitle);
        btnAnswerA.setTypeface(tf);
        btnAnswerB.setTypeface(tf);
        btnAnswerC.setTypeface(tf);
        btnAnswerD.setTypeface(tf);
    }


    public void onClickAnswerA(View v) {
        setResult(Activity.RESULT_OK);
        finish();
    }
    public void onClickAnswerB(View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
    public void onClickAnswerC(View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
    public void onClickAnswerD(View v) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

}
