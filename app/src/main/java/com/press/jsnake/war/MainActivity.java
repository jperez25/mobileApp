package com.press.jsnake.war;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    Card cPlayerCard;
    Card cComCard;
    LinkedList<Card> cards;

    public ImageView playerCardImg;
    public ImageView comCardImg;
    ImageView pnewCard;
    ImageView cnewCard;


    ConstraintLayout layout;
    String str;

    AsyncAnimations anime;
    ConstraintLayout mainLayout;

    boolean isReadyforBattle;

    int phases = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        anime = new AsyncAnimations();

        layout = findViewById(R.id.fragment);

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

        str = " Player " + playerDeck.size();
        player.setText(str);

        str = "Player " + comDeck.size();
        computer.setText(str);

        playerCardImg = findViewById(R.id.playerCardImg);
        comCardImg = findViewById(R.id.comCardImg);

        isReadyforBattle = false;
    }

    public void play(View view) {
        switch (phases){
            //set cards
            case 0:
                setCardsForBattle();
                phases = 1;
                break;
            //battle
            case 1:
                if(battle()){
                    //goes to tie;
                    phases = 3;
                }
                else{
                    //goes to after battle
                    phases = 2;
                }
                break;
            //after battle
            case 2:
                //kind of redundant and not useful
                afterBattle();
                phases = 0;
                break;
            //set cards for tie
            case 3:
                setCardsForTieBattle();
                phases = 4;
                break;
            //tie Battle
            case 4:
                //If true there is a double tie
                if(tieBattle()){
                    //destroy cards before battle
                    destroyExtraCards();
                    //goes to tie
                    phases = 3;
                }
                else{
                    //goes to after battle
                    phases = 2;
                }
                break;
        }

    }

    private void setCardsForBattle(){
        //set imageviews to their respective places
        playerCardImg.setX(1200);
        playerCardImg.setY(1600);


        comCardImg.setX(100);
        comCardImg.setY(100);

        if (playerDeck.isEmpty()){
            //player loses
            setContentView(R.layout.lose);
            return;
        }
        if (comDeck.isEmpty()){
            //com loses
            setContentView(R.layout.winner);
            return;
        }

        currentPlayerCard = playerDeck.draw();
        currentComCard = comDeck.draw();


        int playerCardId = getResources().getIdentifier(currentPlayerCard.toResourceString(), "drawable", getPackageName());
        int comCardId = getResources().getIdentifier(currentComCard.toResourceString(), "drawable", getPackageName());

        Bitmap originalImage = BitmapFactory.decodeResource(getResources(), playerCardId);


        playerCardImg.setImageResource(playerCardId);
        comCardImg.setImageResource(comCardId);

        playerCardImg.setVisibility(View.VISIBLE);
        comCardImg.setVisibility(View.VISIBLE);

        //Reset text after each draw
        str = "Player " + playerDeck.size();
        player.setText(str);
        str = "Computer " + comDeck.size();
        computer.setText(str);

        //Animations.playAnimation(playerCardImg, 0);
        //Animations.playAnimation(comCardImg, 1);

        anime.playAnimation(playerCardImg,0);
        anime.playAnimation(comCardImg,1);
    }

    public boolean battle() {

        // Do the battle
        if (currentPlayerCard.compareRank(currentComCard) > 0) {
            // player wins
            //move cards to player deck
            //Animations.playAniAfterBattle(playerCardImg, 0);
            //Animations.playAniAfterBattle(comCardImg, 0);

            anime.playAniAfterBattle(playerCardImg, 0);
            anime.playAniAfterBattle(comCardImg, 0);

            playerDeck.add(currentPlayerCard);
            playerDeck.add(currentComCard);

            //set text after animation ends
            str = "Player " + playerDeck.size();
            player.setText(str);


            str = "Computer " + comDeck.size();
            computer.setText(str);


            Log.d("win", "battle: Player Wins");
        } else if (currentPlayerCard.compareRank(currentComCard) == 0){

            Log.d("win", "battle: Player ties");
            return true;

        } else {
            // com wins
            //move cards to player deck
            Animations.playAniAfterBattle(playerCardImg, 1);
            Animations.playAniAfterBattle(comCardImg, 1);

            anime.playAniAfterBattle(playerCardImg, 1);
            anime.playAniAfterBattle(comCardImg, 1);

            comDeck.add(currentPlayerCard);
            comDeck.add(currentComCard);

            //set text after animation ends
            str = "Player " + playerDeck.size();
            player.setText(str);


            str = "Computer " + comDeck.size();
            computer.setText(str);


            Log.d("win", "battle: Player loses");
        }
        if (playerDeck.isEmpty()){
            //player loses
            Intent myIntent = new Intent(MainActivity.this, lose.class);
            startActivity(myIntent);
        }
        if (comDeck.isEmpty()){
            //com loses

            Intent myIntent = new Intent(MainActivity.this, Win.class);
            startActivity(myIntent);
        }
        playerDeck.shuffle();
        comDeck.shuffle();

        return false;
    }

    private void afterBattle(){
        Log.d("after battle", "afterBattle: ");
        playerCardImg.setVisibility(View.INVISIBLE);
        playerCardImg.setImageDrawable(null);
        comCardImg.setVisibility(View.INVISIBLE);
        comCardImg.setImageDrawable(null);
        try{
            if (cnewCard.getVisibility() == View.VISIBLE){

                cnewCard.setVisibility(View.INVISIBLE);
                cnewCard.setImageDrawable(null);
            }
            if (pnewCard.getVisibility() == View.VISIBLE){
                pnewCard.setVisibility(View.INVISIBLE);
                pnewCard.setImageDrawable(null);
            }
//            if (anime.playerFaceDownCards[0].getVisibility() == View.VISIBLE ){
                for (ImageView img: anime.playerFaceDownCards) {
                    img.setVisibility(View.INVISIBLE);
                    img.setImageDrawable(null);
                }
//            }
//            if (anime.comFaceDownCards[0].getVisibility() == View.VISIBLE ){
                for (ImageView img: anime.comFaceDownCards) {
                    img.setVisibility(View.INVISIBLE);
                    img.setImageDrawable(null);
                }
//            }
        }
        catch (NullPointerException e){
            Log.d("NULL", "newCard wasnt created");
        }

    }

    private void setCardsForTieBattle(){
        //player 0
        //com 1
        anime.tieAnimation(comCardImg,1);
        anime.tieAnimation(playerCardImg,0);
        Log.d("Images", "Generating Images: ");
        anime.generateImages(layout, getApplicationContext(),0);
        anime.generateImages(layout, getApplicationContext(),1);
        Log.d("Images", "Images Generated: ");

        //draw three cards and put them on hold
        cards = new LinkedList<>();
        for(int i = 0; i < 3; i++){
            if (playerDeck.isEmpty()){
                //player loses
                Intent myIntent = new Intent(MainActivity.this, lose.class);
                startActivity(myIntent);
                break;
            }
            if (comDeck.isEmpty()){
                //com loses
                Intent myIntent = new Intent(MainActivity.this, Win.class);
                startActivity(myIntent);
                break;
            }
            Log.d("Card drawn", "Card Drawn");
            cards.add(playerDeck.draw());
            Log.d("tie", cards.getLast().toString());
            cards.add(comDeck.draw());
            Log.d("tie", cards.getLast().toString());
        }


        //draw two more cards
        if (playerDeck.isEmpty()){
            //player loses
            //take to win/lose screen
            setContentView(R.layout.lose);
            Intent myIntent = new Intent(MainActivity.this, lose.class);
            startActivity(myIntent);
            return;
        }
        cPlayerCard = playerDeck.draw();
        if (comDeck.isEmpty()){
            //com loses
            //take to win/lose screen
            Log.d("Empty Deck", "Coms deck is empty!! Switching layouts... ");
            setContentView(R.layout.winner);
            Intent myIntent = new Intent(MainActivity.this, Win.class);
            startActivity(myIntent);
            return;
        }
        cComCard = comDeck.draw();

        setNewCardsForBattle(cPlayerCard, cComCard);

    }

    private void setNewCardsForBattle(Card cPlayerCard, Card cComCard){
        //player 0
        //com 1

        //Generate Images Dynamically
        pnewCard = new ImageView(getApplicationContext());
        cnewCard = new ImageView(getApplicationContext());

        int playerCardId = getResources().getIdentifier(cPlayerCard.toResourceString(), "drawable", getPackageName());
        int comCardId = getResources().getIdentifier(cComCard.toResourceString(), "drawable", getPackageName());

        pnewCard.setImageResource(playerCardId);
        cnewCard.setImageResource(comCardId);

        pnewCard.setLayoutParams(new android.view.ViewGroup.LayoutParams(300,250));
        pnewCard.setMinimumHeight(120);
        pnewCard.setMinimumWidth(80);
//        pnewCard.setMaxHeight(80);
//        pnewCard.setMaxWidth(53);
        pnewCard.setImageResource(playerCardId);
        pnewCard.setX(1200);
        pnewCard.setY(1600);

        cnewCard.setLayoutParams(new android.view.ViewGroup.LayoutParams(300,250));
//        cnewCard.setMaxHeight(80);
//        cnewCard.setMaxWidth(53);
        pnewCard.setMinimumHeight(120);
        pnewCard.setMinimumWidth(80);
        cnewCard.setX(100);
        cnewCard.setY(100);

        layout.addView(pnewCard);
        layout.addView(cnewCard);

        //execute animation
        anime.playAnimation(pnewCard,0);
        anime.playAnimation(cnewCard,1);

        //set text
        str = "Player " + playerDeck.size();
        player.setText(str);

        str = "Computer " + comDeck.size();
        computer.setText(str);
    }

    private boolean tieBattle(){
        //player 0
        //com 1

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

            anime.moveCards(playerCardImg, pnewCard, 0);
            anime.moveCards(comCardImg, cnewCard, 0);

            Log.d("win", "battle: Player Wins");
        } else if (cPlayerCard.compareRank(cComCard) == 0){
            //if double tied, we are fucked
            //cards on hold are kept on hold
            Log.d("win", "battle: Player ties");
            return true;
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

            anime.moveCards(playerCardImg, pnewCard, 1);
            anime.moveCards(comCardImg, cnewCard, 1);

            Log.d("win", "battle: Player loses");
        }
        str = "Player " + playerDeck.size();
        player.setText(str);

        str = "Computer " + comDeck.size();
        computer.setText(str);

        return false;
    }

    private void destroyExtraCards(){
        try{
            if (cnewCard.getVisibility() == View.VISIBLE){
                cnewCard.setVisibility(View.INVISIBLE);
                cnewCard.setImageDrawable(null);
            }
            if (pnewCard.getVisibility() == View.VISIBLE){
                pnewCard.setVisibility(View.INVISIBLE);
                pnewCard.setImageDrawable(null);
            }
            if (anime.playerFaceDownCards[0].getVisibility() == View.VISIBLE ){
                for (ImageView img: anime.playerFaceDownCards) {
                    img.setVisibility(View.INVISIBLE);
                    img.setImageDrawable(null);
                }
            }
            if (anime.comFaceDownCards[0].getVisibility() == View.VISIBLE ){
                for (ImageView img: anime.comFaceDownCards) {
                    img.setVisibility(View.INVISIBLE);
                    img.setImageDrawable(null);                }
            }
        }
        catch (NullPointerException e){
            Log.d("NULL", "destroy didnt work");
        }
    }

}
