package com.sta404.cellvive.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sta404.cellvive.R;

/**
 Name: MenuActivity
 */
public class MenuActivity extends Activity {
    TextView txtVTitle, txtVNewGame, txtVHighScore, txtVAbout;

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
        setContentView(R.layout.activity_menu);

        txtVTitle = (TextView) findViewById(R.id.titleText);
        txtVNewGame = (TextView) findViewById(R.id.txtVNewGame);
        txtVHighScore = (TextView) findViewById(R.id.txtVHighScore);
        txtVAbout = (TextView) findViewById(R.id.txtVAbout);

        Typeface tfTitle = Typeface.createFromAsset(getAssets(), "fonts/scifi2ku.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/scifi2k2.ttf");

        txtVTitle.setTypeface(tfTitle);
        txtVNewGame.setTypeface(tf);
        txtVHighScore.setTypeface(tf);
        txtVAbout.setTypeface(tf);

    }

    public void onClickPlay(View v) {
        Intent intent = new Intent(this, CellViveActivity.class);
        this.startActivity(intent);
    }
    public void onClickHighScore(View v) {
        Intent intent = new Intent(this, HighScoreActivity.class);
        this.startActivity(intent);
    }
    public void onClickAbout(View v) {
        Intent intent = new Intent(this, AboutActivity.class);
        this.startActivity(intent);
    }
}
