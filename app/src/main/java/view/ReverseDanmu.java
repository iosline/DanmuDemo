package view;

import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * Created by aspros on 15/7/27.
 */
public class ReverseDanmu extends Danmu {
    private Animation animation0,animation1;
    private int fx0,tx0,fx1,tx1;
    private int duration0,duration1;
    private OnAnimationEndListener onAnimationEndListener;

    public interface OnAnimationEndListener
    {
        public void clearPosition();
        public void animationEnd();
    }

    public ReverseDanmu(Context context,int fx,int tx)
    {
        super(context);
        this.fx0=fx;
        this.tx0=100;
        this.fx1=tx0;
        this.tx1=tx;

        duration0=2000*(Math.abs(tx0-fx0))/DensityUtils.getScreenW(context);
        duration1=2000*(Math.abs(tx1-fx1))/DensityUtils.getScreenW(context);

        initAnimation();
    }

    private void initAnimation()
    {
        animation0=new TranslateAnimation(fx0,tx0,0,0);
        animation1=new TranslateAnimation(fx1,tx1,0,0);
        animation0.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                clearAnimation();
                startAnimation(animation1);
                if (onAnimationEndListener!=null)
                {
                    onAnimationEndListener.clearPosition();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation0.setFillAfter(true);
        animation0.setDuration(duration0);
        animation0.setInterpolator(new AccelerateInterpolator());

        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(onAnimationEndListener!=null)
                {
                    onAnimationEndListener.animationEnd();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation1.setFillAfter(true);
        animation1.setDuration(duration1);
        animation1.setInterpolator(new DecelerateInterpolator());
    }

    public void setOnAnimationEndListener(OnAnimationEndListener onAnimationEndListener)
    {
        this.onAnimationEndListener=onAnimationEndListener;
    }

    @Override
    public void send() {
        startAnimation(animation0);
    }
}
