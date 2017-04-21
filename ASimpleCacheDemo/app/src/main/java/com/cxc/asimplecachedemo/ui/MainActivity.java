package com.cxc.asimplecachedemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cxc.asimplecachedemo.R;
import com.cxc.asimplecachedemo.cache.ACache;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    private Button btn_save;
    private Button btn_read;
    private EditText et_h;
    private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        aCache = ACache.get(this);
    }

    private void initView(){
        btn_read = (Button)findViewById(R.id.btn_read);
        btn_read.setOnClickListener(this);

        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        et_h = (EditText)findViewById(R.id.et_h);
    }

    private void readCache(){
        String str = aCache.getAsString("et_str");
        if(str == null){
            et_h.setText("cache is empty");
            return;
        }
        et_h.setText(str);
    }

    /**
    * 保存缓存
    * @param saveTime：过期时间，单位：秒
    * @return
    */
    private void saveCache(int saveTime){
        aCache.put("et_str",et_h.getText().toString(),saveTime);
        et_h.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_read:
                Toast.makeText(this,"read",Toast.LENGTH_SHORT).show();
                readCache();
                break;
            case R.id.btn_save:
                Toast.makeText(this,"save",Toast.LENGTH_SHORT).show();
                saveCache(30);
                break;
            default:
                break;
        }
    }
}
