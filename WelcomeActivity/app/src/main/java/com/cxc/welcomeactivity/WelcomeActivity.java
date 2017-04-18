package com.cxc.welcomeactivity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    public static final int SUCCESS = 1;

    Timer timer = new Timer();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == SUCCESS){
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
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

        //AppCompatActivity下去除标题栏
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                ,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);
        
        initView();

        timer.schedule(timerTask,2000);
//        timer.schedule(timerTask,1000,1000);
    }

    private void initView(){
    }
}
