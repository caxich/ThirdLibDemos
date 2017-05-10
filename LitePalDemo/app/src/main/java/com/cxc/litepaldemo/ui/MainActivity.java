package com.cxc.litepaldemo.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
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

        //生成数据库及插入一条或多条数据
//        dataInsert();
        //数据修改
//        dataUpdate();
        //数据删除
//        dataDelete();
        //数据查询
//        dataQuery();
        //聚合函数
        dataFuncQuery();
    }

    private void dataInsert(){
        //生成数据库
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

    private void dataUpdate(){
        ContentValues values = new ContentValues();
        values.put("title","更新的新闻标题");
        //如不写判断条件则会修改所有记录
        DataSupport.updateAll(News.class,values,"title = ? and id = ?","新闻标题1","1");
    }

    private void dataDelete(){
        DataSupport.deleteAll(News.class,"title = ? and id = ?","新闻标题1","3");
    }

    //select * from users where id > 0 order by publishDate desc limit 1,10;
    private void dataQuery(){
        List<News> newsList =  DataSupport.where("id > ?","1")
                .order("publishdate desc")
                .limit(10)
                .offset(1)
                .find(News.class);

        Log.i("LitePal",Integer.toString(newsList.size()));

        for(News news:newsList){
            Log.i("LitePal",news.getTitle());
        }
    }

    private void dataFuncQuery(){
        int rlt = DataSupport.count(News.class);
        Log.i("LitePal",Integer.toString(rlt));
    }
}
