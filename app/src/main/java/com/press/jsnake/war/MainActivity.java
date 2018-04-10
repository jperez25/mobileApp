package com.press.jsnake.war;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.Switch;
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
        //set imageviews to their respective places
        playerCardImg.setX(1200);
        playerCardImg.setY(1600);

        comCardImg.setX(100);
        comCardImg.setY(100);
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

        playerCardImg.setVisibility(View.VISIBLE);
        comCardImg.setVisibility(View.VISIBLE);


       playAnimation(playerCardImg, 0);
       playAnimation(comCardImg, 1);

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
            //move cards to player deck
            playAniAfterBattle(playerCardImg, 0);
            playAniAfterBattle(comCardImg, 0);

            //setting cards to invisible
            //playerCardImg.setVisibility(View.INVISIBLE);
            //comCardImg.setVisibility(View.INVISIBLE);

            playerDeck.add(currentPlayerCard);
            playerDeck.add(currentComCard);
            Log.d("win", "battle: Player Wins");
        } else if (currentPlayerCard.compareRank(currentComCard) == 0){
            iftied();
            Log.d("win", "battle: Player ties");
        } else {
            // com wins
            //move cards to player deck
            playAniAfterBattle(playerCardImg, 1);
            playAniAfterBattle(comCardImg, 1);

            //setting cards to invisible
            //playerCardImg.setVisibility(View.INVISIBLE);
            //comCardImg.setVisibility(View.INVISIBLE);

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

    void playAnimation(ImageView img, int who){
        ObjectAnimator aniX;
        ObjectAnimator aniY;
        AnimatorSet set;
        switch (who){
            case 0:
                aniX = ObjectAnimator.ofFloat(img,"x", 600f);
                aniY = ObjectAnimator.ofFloat(img,"y", 1100f);
                aniX.setDuration(1000); //milli
                aniY.setDuration(1000); //milli
                set = new AnimatorSet();
                set.playTogether(aniX,aniY);
                set.start();
                break;
            case 1:
                aniX = ObjectAnimator.ofFloat(img,"x", 600f);
                aniY = ObjectAnimator.ofFloat(img,"y", 600f);
                aniX.setDuration(1000); //milli
                aniY.setDuration(1000); //milli
                set = new AnimatorSet();
                set.playTogether(aniX,aniY);
                set.start();
                break;
        }
    }

    void playAniAfterBattle(ImageView img, int where){
        ObjectAnimator aniX;
        ObjectAnimator aniY;
        AnimatorSet set;
        switch (where){
            case 0:
                aniX = ObjectAnimator.ofFloat(img,"x", 1100f);
                aniY = ObjectAnimator.ofFloat(img,"y", 1600f);
                aniX.setDuration(1000); //milli
                aniY.setDuration(1000); //milli
                set = new AnimatorSet();
                set.playTogether(aniX,aniY);
                set.start();
                break;
            case 1:
                aniX = ObjectAnimator.ofFloat(img,"x", 100f);
                aniY = ObjectAnimator.ofFloat(img,"y", 100f);
                aniX.setDuration(1000); //milli
                aniY.setDuration(1000); //milli
                set = new AnimatorSet();
                set.playTogether(aniX,aniY);
                set.start();
                break;
        }
    }

    void iftied(){
        for(int i = 0; i < 3; i++){
            currentPlayerCard = playerDeck.draw();
            currentComCard = comDeck.draw();
        }

    }


}