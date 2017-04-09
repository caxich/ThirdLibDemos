package com.cxc.swiperefreshlayout;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;

    public static final int REFRESH_FAIL = 0;//获取html失败

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REFRESH_FAIL:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView(){
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout(){
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.srl);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1,R.color.swiperefresh_color2,
                R.color.swiperefresh_color3,R.color.swiperefresh_color4);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(MainActivity.this,"refresh start",Toast.LENGTH_SHORT).show();
    }

    private void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                parseHtml();
            }
        }).start();
    }

    private void parseHtml(){
        try{
            Document document;
            document = Jsoup.connect("http://www.jy510.com/a/houseinfo/kpyh/").get();
        }catch (IOException ie){
            Message msg = new Message();
            msg.what = REFRESH_FAIL;
            msg.obj = "refresh failed";
            handler.sendMessage(msg);
            ie.printStackTrace();
        }
    }
}
