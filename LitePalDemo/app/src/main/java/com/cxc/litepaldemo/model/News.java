package com.cxc.litepaldemo.model;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by cxc on 2017/5/7.
 */

public class News extends DataSupport{
    private int id;

    private String title;

    private String content;

    private Date publishDate;

    private int commentCount;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setId(int id) {

        this.id = id;
    }
}
