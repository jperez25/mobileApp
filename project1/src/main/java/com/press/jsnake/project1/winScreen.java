package com.press.jsnake.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class winScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);
    }

    public void restartWin(View v){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
