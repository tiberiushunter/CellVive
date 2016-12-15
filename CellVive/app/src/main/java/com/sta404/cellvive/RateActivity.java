package com.sta404.cellvive;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RateActivity extends Activity {

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

        setContentView(R.layout.activity_rate);



        TextView title = (TextView) findViewById(R.id.titleText);
        TextView txtVBack = (TextView) findViewById(R.id.txtVBack);

        Typeface tfTitle = Typeface.createFromAsset(getAssets(), "fonts/scifi2ku.ttf");
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/scifi2k2.ttf");

        title.setTypeface(tfTitle);
        txtVBack.setTypeface(tf);

    }

    public void onClick(View v) {
        finish();
    }

}
