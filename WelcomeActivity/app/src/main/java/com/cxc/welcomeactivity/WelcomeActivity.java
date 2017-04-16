package com.cxc.welcomeactivity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    private TextView tv_wel;

    public static final int SUCCESS = 1;

    private int i = 0;

    Timer timer = new Timer();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == SUCCESS){
                tv_wel.setText(Integer.toString(i++));
            }
            super.handleMessage(msg);
        }
    };

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = SUCCESS;
            handler.sendMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        
        initView();

        timer.schedule(timerTask,1000,1000);
    }

    private void initView(){
        tv_wel = (TextView)findViewById(R.id.tv_wel);
    }
}
