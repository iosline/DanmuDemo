package com.app.aspros.danmudemo;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

import view.DensityUtils;
import view.NormalDanmu;
import view.ReverseDanmu;
import view.TopDanmu;


public class MainActivity extends FragmentActivity {

    private RelativeLayout container;
    private Button button,reverseButton,topButton,bottomButtom;
    private int sWidth,sHeight;
    private HashMap<Integer,Boolean> sendPosition=new HashMap<Integer,Boolean>();
    private HashMap<Integer,Boolean> reverseSendPosition=new HashMap<Integer,Boolean>();
    private HashMap<Integer,Boolean> topSendPosition=new HashMap<Integer,Boolean>();
    private HashMap<Integer,Boolean> bottomSendPosition=new HashMap<Integer,Boolean>();
    private int count;
    private int danmuHeight;
    private Paint paint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container= (RelativeLayout) findViewById(R.id.danmuContainer);
        button= (Button) findViewById(R.id.button);
        reverseButton= (Button) findViewById(R.id.reverse_button);
        topButton= (Button) findViewById(R.id.top_button);
        bottomButtom= (Button) findViewById(R.id.bottom_button);
        init();
    }

    private void init()
    {



        danmuHeight= (int) (DensityUtils.sp2px(this,15)*1.5);
        ViewGroup.LayoutParams lp=container.getLayoutParams();
        lp.width=DensityUtils.sp2px(this,15)*100;
        container.setLayoutParams(lp);

        sWidth= DensityUtils.getScreenW(this);
        sHeight=DensityUtils.getScreenH(this)*3/4;

        count= sHeight/danmuHeight;
        for(int i=0;i<count;i++)
        {
            sendPosition.put(i,false);
            reverseSendPosition.put(i,false);
            topSendPosition.put(i,false);
            bottomSendPosition.put(i,false);
        }





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDanmu();
            }
        });

        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setReverseDanmu();
            }
        });
        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTopDanmu();
            }
        });
        bottomButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBottomDanmu();
            }
        });


        TextView textView=new TextView(this);
        textView.setTextSize(15);
        paint=textView.getPaint();



    }

    private void setReverseDanmu()
    {

        TextView textView=new TextView(this);
        textView.setTextSize(15);
        Paint paint=textView.getPaint();

        String ss="按是按时按是android.os.BinderProx按是";
        int ll=ss.length()*DensityUtils.sp2px(this,15);
        int ran= new Random().nextInt(ss.length());
        String str=ss.substring(ran);
        final ReverseDanmu reverseDanmu=new ReverseDanmu(this, (int) -paint.measureText(str),sWidth);
        reverseDanmu.setTextSize(15);
        reverseDanmu.setText(str);
        reverseDanmu.setTextColor(Color.BLUE);
        reverseDanmu.setOnAnimationEndListener(new ReverseDanmu.OnAnimationEndListener() {
            @Override
            public void clearPosition() {

                reverseSendPosition.put(reverseDanmu.getPosition(), false);
            }

            @Override
            public void animationEnd() {

                container.removeView(reverseDanmu);
            }

        });

        for(int i=0;i<count;i++)
        {
            if(reverseSendPosition.get(i)==false)
            {
                reverseDanmu.setPosition(i);
                RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, danmuHeight);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                lp.topMargin=i*danmuHeight;
                reverseDanmu.setGravity(Gravity.CENTER);
                container.addView(reverseDanmu, lp);

                reverseDanmu.send();

                reverseSendPosition.put(i,true);
                break;
            }

        }
    }

    private void setDanmu()
    {
        String ss="按是按时按是android.os.BinderProx按是";
        int ll=ss.length()*DensityUtils.sp2px(this,15);
        int ran= new Random().nextInt(ss.length());
        String str=ss.substring(ran);
        final NormalDanmu danmu=new NormalDanmu(this,sWidth,(int) -paint.measureText(str));
        danmu.setTextSize(15);
        danmu.setText(str);
        danmu.setOnAnimationEndListener(new NormalDanmu.OnAnimationEndListener() {
            @Override
            public void clearPosition() {

                sendPosition.put(danmu.getPosition(), false);
            }

            @Override
            public void animationEnd() {

                container.removeView(danmu);
            }

        });

        for(int i=0;i<count;i++)
        {
            if(sendPosition.get(i)==false)
            {
                danmu.setPosition(i);
                RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, danmuHeight);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                lp.topMargin=i*danmuHeight;
                danmu.setGravity(Gravity.CENTER);
                container.addView(danmu, lp);

                danmu.send();

                sendPosition.put(i,true);
                break;
            }

        }
    }

    private void setTopDanmu()
    {
        String ss="按是按时按是android.os.BinderProx按是";

        int ran= new Random().nextInt(ss.length());
        String str=ss.substring(ran);
        int ll=str.length()*DensityUtils.sp2px(this, 15);
        final TopDanmu danmu=new TopDanmu(this,2000);
        danmu.setTextSize(15);
        danmu.setText(str);
        danmu.setTextColor(Color.GREEN);
        danmu.setOnDisappearListener(new TopDanmu.OnDisappearListener() {
            @Override
            public void disappear() {
                container.removeView(danmu);
                topSendPosition.put(danmu.getPosition(), false);
            }
        });

        for(int i=0;i<count;i++)
        {
            if(topSendPosition.get(i)==false)
            {
                danmu.setPosition(i);
                RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, danmuHeight);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                int margin= (int) ((sWidth-paint.measureText(danmu.getText().toString()))/2);
                lp.topMargin=i*danmuHeight;
                lp.leftMargin=margin;
                danmu.setGravity(Gravity.CENTER);
                container.addView(danmu, lp);

                danmu.send();

                topSendPosition.put(i,true);
                break;
            }

        }
    }
    private void setBottomDanmu()
    {
        String ss="按是按时按是android.os.BinderProx按是";
        int ran= new Random().nextInt(ss.length());
        String str=ss.substring(ran);
        int ll=str.length()*DensityUtils.sp2px(this,15);
        final TopDanmu danmu=new TopDanmu(this,2000);
        danmu.setTextSize(15);
        danmu.setText(str);
        danmu.setTextColor(Color.RED);
        danmu.setOnDisappearListener(new TopDanmu.OnDisappearListener() {
            @Override
            public void disappear() {
                container.removeView(danmu);
                bottomSendPosition.put(danmu.getPosition(), false);
            }
        });

        for(int i=count-1;i>=0;i--)
        {
            if(bottomSendPosition.get(i)==false)
            {
                danmu.setPosition(i);
                RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, danmuHeight);
                lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                int margin= (int) ((sWidth-paint.measureText(danmu.getText().toString()))/2);
                lp.leftMargin=margin;
                lp.topMargin=i*danmuHeight;
                danmu.setGravity(Gravity.CENTER);
                container.addView(danmu, lp);

                danmu.send();

                bottomSendPosition.put(i,true);
                break;
            }

        }
    }

}
