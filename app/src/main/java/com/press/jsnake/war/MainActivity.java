package com.press.jsnake.war;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.LinkedList;

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
    boolean isReadyforBattle;
    LinkedList<Card> cards;
    ConstraintLayout layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.linearLayout);

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

        isReadyforBattle = false;
        playerCardImg = findViewById(R.id.playerCardImg);
        comCardImg = findViewById(R.id.comCardImg);
    }

    public void play(View view) {

        layout = xx;
        if (isReadyforBattle){
            battle();
            isReadyforBattle = false;
            Animations.generateImages(layout, getApplicationContext());
        }
        else{
            //set imageviews to their respective places
            playerCardImg.setX(1200);
            playerCardImg.setY(1600);


            comCardImg.setX(100);
            comCardImg.setY(100);


            currentPlayerCard = playerDeck.draw();
            currentComCard = comDeck.draw();


            int playerCardId = getResources().getIdentifier(currentPlayerCard.toResourceString(), "drawable", getPackageName());
            int comCardId = getResources().getIdentifier(currentComCard.toResourceString(), "drawable", getPackageName());

            playerCardImg.setImageResource(playerCardId);
            comCardImg.setImageResource(comCardId);

            playerCardImg.setVisibility(View.VISIBLE);
            comCardImg.setVisibility(View.VISIBLE);

            //Reset text after each draw
            String str = "Player " + playerDeck.size();
            player.setText(str);
            str = "Computer " + comDeck.size();
            computer.setText(str);

            Animations.playAnimation(playerCardImg, 0);
            Animations.playAnimation(comCardImg, 1);
            isReadyforBattle = true;
        }
    }

    public void battle() {

        String str = "";

        // Do the battle
        if (currentPlayerCard.compareRank(currentComCard) > 0) {
            // player wins
            //move cards to player deck
            Animations.playAniAfterBattle(playerCardImg, 0);
            Animations.playAniAfterBattle(comCardImg, 0);

            playerDeck.add(currentPlayerCard);
            playerDeck.add(currentComCard);

            //set text after animation ends
            str = "Player " + playerDeck.size();
            player.setText(str);


            str = "Computer " + comDeck.size();
            computer.setText(str);

            //setting cards to invisible
            //playerCardImg.setVisibility(View.INVISIBLE);
            //comCardImg.setVisibility(View.INVISIBLE);
            Log.d("win", "battle: Player Wins");
        } else if (currentPlayerCard.compareRank(currentComCard) == 0){
            iftied();
            Log.d("win", "battle: Player ties");
        } else {
            // com wins
            //move cards to player deck
            Animations.playAniAfterBattle(playerCardImg, 1);
            Animations.playAniAfterBattle(comCardImg, 1);

            comDeck.add(currentPlayerCard);
            comDeck.add(currentComCard);

            //set text after animation ends
            str = "Player " + playerDeck.size();
            player.setText(str);


            str = "Computer " + comDeck.size();
            computer.setText(str);

            //setting cards to invisible
            //playerCardImg.setVisibility(View.INVISIBLE);
            //comCardImg.setVisibility(View.INVISIBLE);
            Log.d("win", "battle: Player loses");
        }
        if (playerDeck.size() == 0){
            //player loses
            setContentView(R.layout.lose);
        }
        if (comDeck.size() == 0){
            //com loses
            setContentView(R.layout.winner);
        }
       playerDeck.shuffle();
       comDeck.shuffle();

    }

    void iftied(){
        Animations.tieAnimation(comCardImg, 1);
        Animations.tieAnimation(playerCardImg, 0);
        Animations.generateImages(layout, getApplicationContext());
        cards = new LinkedList<>();
        //draw three cards and put them on hold
        for(int i = 0; i < 3; i++){
            if (playerDeck.size() == 0){
                //player loses
                setContentView(R.layout.lose);
                break;
            }
            if (comDeck.size() == 0){
                //com loses
                setContentView(R.layout.winner);
                break;
            }
            Log.d("Card drawn", "Card Drawn");
            cards.add(playerDeck.draw());
            cards.add(comDeck.draw());
        }
        //execute animation

        //draw two more cards
        if (playerDeck.size() == 0){
            //player loses
            //take to win/lose screen
            setContentView(R.layout.lose);
        }
        Card cComCard = playerDeck.draw();
        if (comDeck.size() == 0){
            //com loses
            //take to win/lose screen
            setContentView(R.layout.winner);
        }
        Card cPlayerCard = comDeck.draw();
        //execute animation

        //set text
        String str = "Player " + playerDeck.size();
        player.setText(str);

        str = "Computer " + comDeck.size();
        computer.setText(str);

        //battle
        // Do the battle
        if (cPlayerCard.compareRank(cComCard) > 0) {
            // player wins
            //put cards in cards on hold
            //move cards to player deck
            cards.add(currentPlayerCard);
            cards.add(currentComCard);
            cards.add(cPlayerCard);
            cards.add(cComCard);

            for (Card x: cards) {
                playerDeck.add(x);
            }
            //clear cards in hold
            cards.clear();
            Log.d("win", "battle: Player Wins");
        } else if (cPlayerCard.compareRank(cComCard) == 0){
            //if double tied, we are fucked
            //cards on hold are kept on hold
            iftied();
            Log.d("win", "battle: Player ties");
        } else {
            // com wins

            cards.add(currentPlayerCard);
            cards.add(currentComCard);
            cards.add(cPlayerCard);
            cards.add(cComCard);

            for (Card x: cards) {
                playerDeck.add(x);
            }
            cards.clear();
            Log.d("win", "battle: Player loses");
        }
        str = "Player " + playerDeck.size();
        player.setText(str);

        str = "Computer " + comDeck.size();
        computer.setText(str);
    }


}