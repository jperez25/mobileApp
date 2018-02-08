package com.press.jsnake.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //our buttons and textView
    private static Button leftButton;
    private static Button rightButton;
    private static TextView score;
    private static int scr;
    private static int clicks;

    //the counter
    private static int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set score
        scr = 0;
        score = findViewById(R.id.points);
        String thePoints = "Points: "+scr;
        score.setText(thePoints);

        //call Randomize
        randomize();

    }

    @Override
    protected void onResume(){
        super.onResume();

        //set score/clicks once we come back from win/lose screens
        scr = 0;
        clicks = 0;
        score = findViewById(R.id.points);
        String thePoints = "Points: "+scr;
        score.setText(thePoints);

        //call Randomize
        randomize();
    }

    private void randomize() {
        //our random numbers go from 0 to 100
        int firstNumber = (int)(Math.random()*100);
        int secondNumber = (int)(Math.random()*100);

        //find buttons
        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);

        //set text
        leftButton.setText(Integer.toString(firstNumber) );
        rightButton.setText(Integer.toString(secondNumber) );
    }

    public void whichOneIsgreater(View v){
        clicks += 1;
        Button btn = (Button)findViewById(v.getId());
        int num = Integer.parseInt( btn.getText().toString() );

        //call comes from left
        if(num == Integer.parseInt(leftButton.getText().toString()) ){
            if(num > Integer.parseInt(rightButton.getText().toString())){
                scr += 1;
                String points = "Points: "+scr;
                score.setText(points);

            }
            else{
                scr -= 1;
                String points = "Points: "+scr;
                score.setText(points);
            }
        }
        //call comes from right
        else{
            if(num > Integer.parseInt(leftButton.getText().toString())){
                scr += 1;
                String points = "Points: "+scr;
                score.setText(points);

            }
            else{
                scr -= 1;
                String points = "Points: "+scr;
                score.setText(points);
            }
        }
        //call randomize
        randomize();
        //Check if the won or lost
        if (scr >= 10){ //This is for wins
            Intent myIntent = new Intent(this, winScreen.class);
            startActivity(myIntent);
        }
        if(clicks >= 15){//this is for loses
            Intent myIntent = new Intent(this, loseScreen.class);
            startActivity(myIntent);
        }
    }




}
