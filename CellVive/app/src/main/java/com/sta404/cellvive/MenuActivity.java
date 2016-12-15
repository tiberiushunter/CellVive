package com.sta404.cellvive;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MenuActivity extends Activity {

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



        TextView title = (TextView) findViewById(R.id.titleText);
        TextView txvNewGame = (TextView) findViewById(R.id.txtVNewGame);    //TODO proper names
        TextView txvHighScore = (TextView) findViewById(R.id.txtVHighScore);
        TextView txvRate = (TextView) findViewById(R.id.txtVRate);
        TextView txvAbout = (TextView) findViewById(R.id.txtVAbout);

        Typeface tfTitle = Typeface.createFromAsset(getAssets(), "fonts/scifi2ku.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/scifi2k2.ttf");

        title.setTypeface(tfTitle);
        txvNewGame.setTypeface(tf);
        txvHighScore.setTypeface(tf);
        txvRate.setTypeface(tf);
        txvAbout.setTypeface(tf);

    }

    public void onClickPlay(View v) {
        Intent intent = new Intent(this, CellViveActivity.class);
        this.startActivity(intent);
    }
    public void onClickHighScore(View v) {
        Intent intent = new Intent(this, HighScoreActivity.class);
        this.startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
