package com.press.jsnake.war;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by jsnak on 4/23/2018.
 */

public class lose extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose);

        Button btn = (Button) findViewById(R.id.Main_Menu);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(lose.this, Start.class);
                startActivity(myIntent);
            }
        });
        Button playagain = (Button) findViewById(R.id.playagain2);

        playagain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(lose.this, MainActivity.class);
                startActivity(myIntent);
            }
        });


    }}