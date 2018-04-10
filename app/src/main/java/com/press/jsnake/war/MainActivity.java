package com.press.jsnake.war;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //    LinkedList<Integer> deck = new LinkedList<>();
    Deck comDeck;
    Deck playerDeck;
    TextView player;
    TextView computer;
    Card currentPlayerCard;
    Card currentComCard;
    ImageView playerCardImg;
    ImageView comCardImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create deck and fill it
        Deck deck = new Deck();
        comDeck = new Deck();
        playerDeck = new Deck();
        deck.fill();

        //shuffle deck
        deck.shuffle();

        //partition deck into two
        while(!deck.isEmpty()) {
            comDeck.add(deck.draw());
            playerDeck.add(deck.draw());
        }

        player = findViewById(R.id.playerText);
        computer = findViewById(R.id.computerText);

        String str = player.getText().toString();
        str += " " + playerDeck.size();
        player.setText(str);

        str = computer.getText().toString();
        str += " " + comDeck.size();
        computer.setText(str);


        playerCardImg = findViewById(R.id.playerCardImg);
        comCardImg = findViewById(R.id.comCardImg);
    }

    public void play(View view) {
//        TextView playerCard = findViewById(R.id.playerCard);
//        TextView comCard = findViewById(R.id.comCard);

        currentPlayerCard = playerDeck.draw();
        currentComCard = comDeck.draw();
//        playerCard.setText(currentPlayerCard.toString());
//        comCard.setText(currentComCard.toString());
//
        int playerCardId = getResources().getIdentifier(currentPlayerCard.toResourceString(), "drawable", getPackageName());
        int comCardId = getResources().getIdentifier(currentComCard.toResourceString(), "drawable", getPackageName());
//        Log.d("CardID", "card id: " + String.valueOf(playerCardId));
        playerCardImg.setImageResource(playerCardId);
        comCardImg.setImageResource(comCardId);
        battle();
    }

    public void battle() {

        String str = "Player " + playerDeck.size();
        player.setText(str);


        str = "Computer " + comDeck.size();
        computer.setText(str);

        // Do the battle
        if (currentPlayerCard.compareRank(currentComCard) > 0) {
            // player wins
            playerDeck.add(currentPlayerCard);
            playerDeck.add(currentComCard);
            Log.d("win", "battle: Player Wins");
        } else if (currentPlayerCard.compareRank(currentComCard) == 0){
            // ties
            Log.d("win", "battle: Player ties");
        } else {
            // com wins
            comDeck.add(currentPlayerCard);
            comDeck.add(currentComCard);
            Log.d("win", "battle: Player loses");
        }
        str = "Player " + playerDeck.size();
        player.setText(str);


        str = "Computer " + comDeck.size();
        computer.setText(str);

        playerDeck.shuffle();
        comDeck.shuffle();

    }


}