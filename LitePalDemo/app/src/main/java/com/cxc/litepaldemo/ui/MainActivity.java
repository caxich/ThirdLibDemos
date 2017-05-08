package com.cxc.litepaldemo.ui;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cxc.litepaldemo.R;
import com.cxc.litepaldemo.model.News;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData(){
        SQLiteDatabase db = Connector.getDatabase();
        //插入一条数据
        News news = new News();
        news.setTitle("新闻标题1");
        news.setContent("新闻内容");
        news.setPublishDate(new Date());
        news.saveThrows();

        //存储多条数据
        List<News> newsList = new ArrayList<>();
        News news1 = new News();
        news1.setTitle("新闻标题2");
        news1.setContent("新闻内容2");
        news1.setPublishDate(new Date());
        News news2 = new News();
        news2.setTitle("新闻标题1");
        news2.setContent("新闻内容");
        news2.setPublishDate(new Date());
        newsList.add(news1);
        newsList.add(news2);
        DataSupport.saveAll(newsList);

        Log.i("LitePal",Integer.toString(news2.getId()));
        Log.i("LitePal",news2.getPublishDate().toString());
    }
}
