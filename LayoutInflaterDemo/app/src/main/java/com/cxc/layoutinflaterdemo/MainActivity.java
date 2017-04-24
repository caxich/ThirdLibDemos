package com.cxc.layoutinflaterdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (LinearLayout) findViewById(R.id.activity_main);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View tv_content = layoutInflater.inflate(R.layout.button_layout,null);
        relativeLayout.addView(tv_content);
    }
}
