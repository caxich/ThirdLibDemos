package com.cxc.toolbaractivity;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        tb = (Toolbar) findViewById(R.id.tb);

        //自定义toolbar属性的代码必须在setSupportActionBar调用之前执行，否则属性设定无效
        tb.setTitle("ToolBar Demo");
        tb.setTitleTextColor(ContextCompat.getColor(MainActivity.this,R.color.white));

        setSupportActionBar(tb);
    }
}
