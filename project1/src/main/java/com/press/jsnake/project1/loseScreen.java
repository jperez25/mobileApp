package com.press.jsnake.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class loseScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_screen);
    }

    public void restartLose(View v){
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
