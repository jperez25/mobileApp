package com.press.jsnake.war;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinkedList<Integer> deck = new LinkedList<>();
    LinkedList<Integer> comDeck = new LinkedList<>();
    LinkedList<Integer> meDeck = new LinkedList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //add 52 cards to the deck
        for(int i = 1; i<=53 ; i++){
            deck.add(i);
        }
        //shuffle deck
        Collections.shuffle(deck);
        //partition deck into two
        for(int i = 1; i<=26 ; i++){
            comDeck.add(deck.get(i));
            meDeck.add(deck.get(26+i));
        }

        TextView player = findViewById(R.id.playerText);
        TextView computer = findViewById(R.id.computerText);

        String str = player.getText().toString();
        str += " " + meDeck.size();
        player.setText(str);

        str = computer.getText().toString();
        str += " " + comDeck.size();
        computer.setText(str);
    }

    public void play(View view) {
        //check if there is a winner

        //player is the winner
        if (meDeck.size() == 52){
            //display alert and take back to dashboard
            Toast toast = Toast.makeText(getApplicationContext(), "Player wins", Toast.LENGTH_SHORT);

        }
        //com is the winner
        if (comDeck.size() == 52){
            //display alert and take back to dashboard
            Toast toast = Toast.makeText(getApplicationContext(), "Com wins", Toast.LENGTH_SHORT);

        }


        //set card image for com and player
        ImageView imgPlayer = findViewById(R.id.imgPlayer);
        ImageView imgCom = findViewById(R.id.imgCom);

        String nameOfImage = new SetImageHelper().getCardName(meDeck.get(1));
        //find resources by name
        //imgPlayer.setImageResource();
        //check what card is bigger(value)
        if(meDeck.get(1) > comDeck.get(1)){
            meDeck.add(comDeck.get(1));
            comDeck.remove(1);
        }
        //special condition
        else if(meDeck.get(1) == comDeck.get(1)){
            //next three cards are put on hold

            //compare next card on top from both decks
            if(ifTied(1)){
                meDeck.add(comDeck.get(1));
                comDeck.remove(1);
            }
            else{
                comDeck.add(meDeck.get(1));
                meDeck.remove(1);
            }

            //whoever wins gets all cards

        }
        else{
            comDeck.add(meDeck.get(1));
            meDeck.remove(1);
        }

        //set deck size text com and player
        TextView player = findViewById(R.id.playerText);
        TextView computer = findViewById(R.id.computerText);

        String str = "Player " + meDeck.size();
        player.setText(str);

        str = "Computer " + comDeck.size();
        computer.setText(str);
    }

    public boolean ifTied(int index){
        if(meDeck.get(index+4) > comDeck.get(index+4)){
            meDeck.add(comDeck.get(index+1));
            meDeck.add(comDeck.get(index+2));
            meDeck.add(comDeck.get(index+3));
            meDeck.add(comDeck.get(index+4));
            comDeck.remove(index+1);
            comDeck.remove(index+2);
            comDeck.remove(index+3);
            comDeck.remove(index+4);

            return  true;
        }
        //special condition
        else if(meDeck.get(index+4) == comDeck.get(index+4)){
            //next three cards are put on hold

            //compare next card on top from both decks
            ifTied(index+4);

            //whoever wins gets all cards

        }
        else{
            comDeck.add(meDeck.get(index+1));
            comDeck.add(meDeck.get(index+2));
            comDeck.add(meDeck.get(index+3));
            comDeck.add(meDeck.get(index+4));
            meDeck.remove(index+1);
            meDeck.remove(index+2);
            meDeck.remove(index+3);
            meDeck.remove(index+4);


            return false;
        }
        return false;
    }


}
