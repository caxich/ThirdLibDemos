package com.cxc.appcontext;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBtn_get_to;
    private Button mBtn_get_sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData(){
        SharedPreferences.Editor editor = AppContext.getContext().getSharedPreferences("init_data",MODE_PRIVATE).edit();
        editor.putString("context",AppContext.getContext().toString());
        editor.commit();
    }

    private void initView(){
        mBtn_get_to = (Button)findViewById(R.id.btn_get_to);
        mBtn_get_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AppContext.getContext(),AppContext.getContext().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        mBtn_get_sh = (Button)findViewById(R.id.btn_get_sh);
        mBtn_get_sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = AppContext.getContext().getSharedPreferences("init_data",MODE_PRIVATE);
                String str = pref.getString("context","null");
                Toast.makeText(AppContext.getContext(),str,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
