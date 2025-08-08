package com.ll.domain.article.entity;

public class Article {
    private int id;
    private String title;
    private String content;
    private String regDate;
    private int viewCount;

    public Article(int id, String title, String content, String regDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regDate = regDate;
        this.viewCount = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    
    public void increaseViewCount() {
        this.viewCount++;
    }
}