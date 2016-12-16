package com.sta404.cellvive;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 Name: HighScoreActivity
 */
public class HighScoreActivity extends Activity {
    ArrayList<String> scoresList = new ArrayList<String>();
    TextView txtPosition1,
            txtPosition2,
            txtPosition3,
            txtPosition4,
            txtPosition5,
            txtPosition6,
            txtPosition7,
            txtPosition8,
            txtPosition9,
            txtPosition10;

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

        setContentView(R.layout.activity_high_score);



        TextView title = (TextView) findViewById(R.id.titleText);
        TextView txtVBack = (TextView) findViewById(R.id.txtVBack);

        txtPosition1 = (TextView) findViewById(R.id.txtPosition1);
        txtPosition2 = (TextView) findViewById(R.id.txtPosition2);
        txtPosition3 = (TextView) findViewById(R.id.txtPosition3);
        txtPosition4 = (TextView) findViewById(R.id.txtPosition4);
        txtPosition5 = (TextView) findViewById(R.id.txtPosition5);
        txtPosition6 = (TextView) findViewById(R.id.txtPosition6);
        txtPosition7 = (TextView) findViewById(R.id.txtPosition7);
        txtPosition8 = (TextView) findViewById(R.id.txtPosition8);
        txtPosition9 = (TextView) findViewById(R.id.txtPosition9);
        txtPosition10 = (TextView) findViewById(R.id.txtPosition10);

        Typeface tfTitle = Typeface.createFromAsset(getAssets(), "fonts/scifi2ku.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/scifi2k2.ttf");

        title.setTypeface(tfTitle);
        txtVBack.setTypeface(tf);

        txtPosition1.setTypeface(tf);
        txtPosition2.setTypeface(tf);
        txtPosition3.setTypeface(tf);
        txtPosition4.setTypeface(tf);
        txtPosition5.setTypeface(tf);
        txtPosition6.setTypeface(tf);
        txtPosition7.setTypeface(tf);
        txtPosition8.setTypeface(tf);
        txtPosition9.setTypeface(tf);
        txtPosition10.setTypeface(tf);

        readScoresFromFile("highscores.txt");
        Collections.sort(scoresList);

        generateHighScoreTable();
    }

    public void readScoresFromFile(String fileName) {
        Context context = this;
        String line;
        BufferedReader in = null;
        try {
            File fp = new File(context.getFilesDir(), fileName);
            in = new BufferedReader(new FileReader(fp));
            while ((line = in.readLine()) != null) {
                scoresList.add(line);
            }
        } catch (FileNotFoundException e) {
            Log.d("ID", e.getMessage());
        } catch (IOException e) {
            Log.d("ID", e.getMessage());
        }
    }

    public void generateHighScoreTable(){
        try{
            txtPosition1.setText("1st: " + scoresList.get(0));
        } catch (IndexOutOfBoundsException e){
            txtPosition1.setText("1st: -");
        }
        try{
            txtPosition2.setText("2nd: " + scoresList.get(1));
        } catch (IndexOutOfBoundsException e){
            txtPosition2.setText("2nd: -");
        }
        try{
            txtPosition3.setText("3rd: " + scoresList.get(2));
        } catch (IndexOutOfBoundsException e){
            txtPosition3.setText("3rd: -");
        }
        try{
            txtPosition4.setText("4th " + scoresList.get(3));
        } catch (IndexOutOfBoundsException e){
            txtPosition4.setText("4th -");
        }
        try{
            txtPosition5.setText("5th " + scoresList.get(4));
        } catch (IndexOutOfBoundsException e){
            txtPosition5.setText("5th -");
        }
        try{
            txtPosition6.setText("6th: " + scoresList.get(5));
        } catch (IndexOutOfBoundsException e){
            txtPosition6.setText("6th: -");
        }
        try{
            txtPosition7.setText("7th: " + scoresList.get(6));
        } catch (IndexOutOfBoundsException e){
            txtPosition7.setText("7th: -");
        }
        try{
            txtPosition8.setText("8th: " + scoresList.get(7));
        } catch (IndexOutOfBoundsException e){
            txtPosition8.setText("8th: -");
        }
        try{
            txtPosition9.setText("9th: " + scoresList.get(8));
        } catch (IndexOutOfBoundsException e){
            txtPosition9.setText("9th: -");
        }
        try{
            txtPosition10.setText("10th: " + scoresList.get(9));
        } catch (IndexOutOfBoundsException e){
            txtPosition10.setText("10th: -");
        }
    }

    public void onClick(View v) {
        finish();
    }

}
