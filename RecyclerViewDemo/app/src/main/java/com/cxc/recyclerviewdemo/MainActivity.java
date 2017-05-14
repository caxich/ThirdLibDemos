package com.cxc.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recv;
    private List<String> list = new ArrayList<>();
    private RecvAdapter recvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        for(int i=0;i<20;i++){
            list.add("item"+Integer.toString(i));
        }
    }

    private void initView() {
        recv = (RecyclerView)findViewById(R.id.recv);
        LinearLayoutManager linlayout = new LinearLayoutManager(this);
        //设置布局管理器
        recv.setLayoutManager(linlayout);
        //垂直布局
        linlayout.setOrientation(OrientationHelper.VERTICAL);
        //水平布局
//        linlayout.setOrientation(OrientationHelper.HORIZONTAL);

        recvAdapter = new RecvAdapter(MainActivity.this,list);
        //设置adapter
        recv.setAdapter(recvAdapter);

        //        //设置分割线
//        recv.addItemDecoration();

        //垂直分割线
        recv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //水平分割线
//        recv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));

//        //设置增加或删除条目的动画
//        recv.setItemAnimator(new DefaultItemAnimator());

        //点击事件
        recvAdapter.setOnItemClickListener(new RecvAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this,"Item:" + Integer.toString(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                Toast.makeText(MainActivity.this,"long Item:" + Integer.toString(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
