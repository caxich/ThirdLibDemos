package com.cxc.toolbaractivity;

import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
//        tb.setTitle("ToolBar Demo");
//        tb.setTitleTextColor(ContextCompat.getColor(MainActivity.this,R.color.white));

        setSupportActionBar(tb);
    }

    //toolbar自定义菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //toolbar自定义菜单点击响应事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tb_add:
                Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tb_search:
                Toast.makeText(this,"search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tb_menu:
                Toast.makeText(this,"menu",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
