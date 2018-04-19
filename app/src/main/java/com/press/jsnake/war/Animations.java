package com.press.jsnake.war;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

public class Animations {
    static private ConstraintLayout layout;
    static private ImageView playerFaceDownCards[];
    static private ImageView comFaceDownCards[];

    private  Animations(){};

    static void playAnimation(ImageView img, int who){
        ObjectAnimator aniX;
        ObjectAnimator aniY;
        AnimatorSet set;
        switch (who){
            //player
            case 0:
                aniX = ObjectAnimator.ofFloat(img,"x", 600f);
                aniY = ObjectAnimator.ofFloat(img,"y", 1000f);
                aniX.setDuration(1000); //milli
                aniY.setDuration(1000); //milli
                set = new AnimatorSet();
                set.playTogether(aniX,aniY);
                set.start();
                break;
            //com
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

    static void playAniAfterBattle(ImageView img, int where){
        ObjectAnimator aniX;
        ObjectAnimator aniY;
        AnimatorSet set;
        switch (where){
            //player
            case 0:
                aniX = ObjectAnimator.ofFloat(img,"x", 1100f);
                aniY = ObjectAnimator.ofFloat(img,"y", 1600f);
                aniX.setDuration(1000); //milli
                aniY.setDuration(1000); //milli
                set = new AnimatorSet();
                set.playTogether(aniX,aniY);
                set.start();
                break;
            //computer
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

    static void tieAnimation(ImageView img, int who){
        ObjectAnimator aniX;
        ObjectAnimator aniY;
        AnimatorSet set;
        switch (who){
            //player
            case 0:
                aniX = ObjectAnimator.ofFloat(img,"x", 100f);
                aniY = ObjectAnimator.ofFloat(img,"y", 1000f);
                aniX.setDuration(1000); //milli
                aniY.setDuration(1000); //milli
                set = new AnimatorSet();
                set.playTogether(aniX,aniY);
                set.start();

                break;
            //computer
            case 1:
                aniX = ObjectAnimator.ofFloat(img,"x", 100f);
                aniY = ObjectAnimator.ofFloat(img,"y", 600f);
                aniX.setDuration(1000); //milli
                aniY.setDuration(1000); //milli
                set = new AnimatorSet();
                set.playTogether(aniX,aniY);
                set.start();


                break;
        }

    }

    static  void generateImages(ConstraintLayout lay, Context cx, int who){
        layout = lay;
        playerFaceDownCards = new ImageView[3];
        comFaceDownCards = new ImageView[3];
        ObjectAnimator aniX;
        ObjectAnimator aniY;
        AnimatorSet set;

        switch (who){
            //Player
            case 0:
                //Create image Views
                int i = 0;
                for (ImageView img: playerFaceDownCards) {
                    playerFaceDownCards[i] = new ImageView(cx);
                    playerFaceDownCards[i].setLayoutParams(new android.view.ViewGroup.LayoutParams(100,100));
                    playerFaceDownCards[i].setMaxHeight(80);
                    playerFaceDownCards[i].setMaxWidth(53);
                    playerFaceDownCards[i].setImageResource(R.drawable.back_card);
                    playerFaceDownCards[i].setX(1200);
                    playerFaceDownCards[i].setY(1600);

                    // Adds the view to the layout
                    layout.addView(playerFaceDownCards[i]);

                    i++;
                }
                i = 0;
                for (ImageView img: playerFaceDownCards) {
                    //initialize animation
                    aniX = ObjectAnimator.ofFloat(playerFaceDownCards[i],"x", 100f + i*50 );
                    aniY = ObjectAnimator.ofFloat(playerFaceDownCards[i],"y", 1600f);
                    aniX.setDuration(1000+i*50); //milli
                    aniY.setDuration(1000+i*50); //milli
                    set = new AnimatorSet();
                    set.playTogether(aniX,aniY);
                    set.start();
                    i++;
                }
                break;
            case 1:
                //Create image Views
                i = 0;
                for (ImageView img: playerFaceDownCards) {
                    comFaceDownCards[i] = new ImageView(cx);
                    comFaceDownCards[i].setLayoutParams(new android.view.ViewGroup.LayoutParams(100,100));
                    comFaceDownCards[i].setMaxHeight(80);
                    comFaceDownCards[i].setMaxWidth(53);
                    comFaceDownCards[i].setImageResource(R.drawable.back_card);
                    comFaceDownCards[i].setX(100);
                    comFaceDownCards[i].setY(100);

                    // Adds the view to the layout
                    layout.addView(comFaceDownCards[i]);
                    i++;
                }
                i = 0;
                for (ImageView img: playerFaceDownCards) {
                    //initialize animation
                    aniX = ObjectAnimator.ofFloat(comFaceDownCards[i],"x", 1200f - i*50);
                    aniY = ObjectAnimator.ofFloat(comFaceDownCards[i],"y", 100);
                    aniX.setDuration(1000+i*50); //milli
                    aniY.setDuration(1000+i*50); //milli
                    set = new AnimatorSet();
                    set.playTogether(aniX,aniY);
                    set.start();
                    i++;
                }
                break;
        }
    }



}
