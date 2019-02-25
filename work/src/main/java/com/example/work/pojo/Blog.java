package com.example.work.pojo;

import java.util.Date;

public class Blog {
    private Integer id;

    private String title;

    private String type;

    private String author;

    private String content;

    private Date time;

    public Blog(Integer id, String title, String type, String author, String content, Date time) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.author = author;
        this.content = content;
        this.time = time;
    }

    public Blog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}