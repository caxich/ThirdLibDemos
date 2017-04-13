package com.cxc.swiperefreshlayout.bean;

import android.graphics.Bitmap;

/**
 * Created by cxc on 2017/4/10.
 */

public class News {

    private String newsTitle;
    private String newsTime;
    private String newsUrl;
    private Bitmap newsPic;

    public void setNewsTitle(String newsTitle){this.newsTitle = newsTitle;}
    public void setNewsTime(String newsTime){this.newsTime = newsTime;}
    public void setNewsUrl(String newsUrl){this.newsUrl = newsUrl;}
    public void setNewsPic(Bitmap newsPic){this.newsPic = newsPic;}

    public String getNewsTitle(){return newsTitle;}
    public String getNewsTime(){return newsTime;}
    public String getNewsUrl(){return newsUrl;}
    public Bitmap getNewsPic(){return newsPic;}
}
