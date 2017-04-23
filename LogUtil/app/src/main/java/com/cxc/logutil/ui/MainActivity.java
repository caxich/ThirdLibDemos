package com.cxc.logutil.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cxc.logutil.R;
import com.cxc.logutil.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtil.log(getApplicationContext().toString());
    }
}
