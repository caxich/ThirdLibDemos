package com.cxc.swiperefreshlayout.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cxc.swiperefreshlayout.bean.News;
import com.cxc.swiperefreshlayout.R;

import java.util.List;

/**
 * Created by cxc on 2017/4/13.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects){
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        News newsInfo = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_news_title = (TextView)view.findViewById(R.id.tv_news_title);
            viewHolder.tv_news_date = (TextView)view.findViewById(R.id.tv_news_date);
            viewHolder.iv_img = (ImageView)view.findViewById(R.id.iv_img);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.tv_news_title.setText(newsInfo.getNewsTitle());
        viewHolder.tv_news_date.setText(newsInfo.getNewsTime());
        viewHolder.iv_img.setImageBitmap(newsInfo.getNewsPic());

        return view;
    }

    class ViewHolder{
        TextView tv_news_title;
        TextView tv_news_date;
        ImageView iv_img;
    }

}
