package com.example.ProjectBackEnd.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Blog {
    private int blog_id;
    private int user_id;
    private String title;
    private String image_url;
    private String content;

    public Blog(int blog_id, int user_id, String title, String image_url, String content) {
        this.blog_id = blog_id;
        this.user_id = user_id;
        this.title = title;
        this.image_url = image_url;
        this.content = content;
    }

    public Blog (ResultSet rs)throws SQLException{
        this.blog_id = rs.getInt("blog_id");
        this.user_id = rs.getInt("user_id");
        this.title = rs.getString("title");
        this.image_url = rs.getString("image_url");
        this.content = rs.getString("content");
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
