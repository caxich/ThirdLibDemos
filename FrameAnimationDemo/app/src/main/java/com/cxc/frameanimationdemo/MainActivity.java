package com.cxc.frameanimationdemo;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        TextView tv_anim = (TextView)findViewById(R.id.tv_anim);

        //帧动画XML文件必须放置在drawable文件夹下
        tv_anim.setBackgroundResource(R.drawable.signal_anim);
        AnimationDrawable drawable = (AnimationDrawable)tv_anim.getBackground();
        drawable.start();
    }
}
