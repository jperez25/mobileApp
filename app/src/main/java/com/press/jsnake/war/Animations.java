package com.press.jsnake.war;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

public class Animations {
    static ConstraintLayout layout;

    private  Animations(){};

    static void playAnimation(ImageView img, int who){
        ObjectAnimator aniX;
        ObjectAnimator aniY;
        AnimatorSet set;
        switch (who){
            case 0:
                aniX = ObjectAnimator.ofFloat(img,"x", 600f);
                aniY = ObjectAnimator.ofFloat(img,"y", 1000f);
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

    static  void generateImages(ConstraintLayout lay, Context cx){
        layout = lay;
        ImageView img1 = new ImageView(cx);

        img1.setLayoutParams(new android.view.ViewGroup.LayoutParams(80,60));
        img1.setMaxHeight(20);
        img1.setMaxWidth(20);
        img1.setImageResource(R.drawable.aces);

        // Adds the view to the layout
        layout.addView(img1);
    }

}
