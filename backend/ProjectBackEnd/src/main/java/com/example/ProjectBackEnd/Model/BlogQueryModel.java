package com.example.ProjectBackEnd.Model;

import com.example.ProjectBackEnd.DB.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogQueryModel {
    Connection con;

    public void createBlog(String title, String image_url, String content, int user_id)throws SQLException{
        try{
            con = DBConnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Blog(title, image_url, content, user_id) VALUES (?,?,?,?)");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, image_url);
            preparedStatement.setString(3, content);
            preparedStatement.setInt(4, user_id);
            preparedStatement.execute();
        } finally {
            con.close();
        }
    }

    public List<Blog> showAllBlog()throws SQLException {
        try{
            con = DBConnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Blog");
            ResultSet rs = preparedStatement.executeQuery();
            List<Blog> list = new ArrayList<>();
            while (rs.next()){
                list.add(new Blog(rs));
            }
            return list;
        }finally {
            con.close();
        }
    }

    public Blog getBlogInformation(int blog_id) throws SQLException{
        try{
            con = DBConnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Blog WHERE blog_id = ?");
            preparedStatement.setInt(1, blog_id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return new Blog(rs);
        }finally {
            con.close();
        }
    }

    public void getAuthorInformation(int user_id)throws SQLException{
        try{
            con = DBConnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT firstname, lastname FROM User WHERE user_id = ?");
            preparedStatement.setInt(1, user_id);
        }finally {
            con.close();
        }
    }

    public void updateBlogInformation(int blog_id, String title, String image_url, String content)throws SQLException{
        try {
            con = DBConnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE Blog SET title = ?, image_url = ?, content = ? WHERE blog_id = ?");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, image_url);
            preparedStatement.setString(3, content);
            preparedStatement.setInt(4, blog_id);
            preparedStatement.executeUpdate();
        }finally {
            con.close();
        }
    }

    public Blog deleteBlog(int blog_id)throws SQLException{
        try{
            con = DBConnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM Blog WHERE blog_id = ?");
            preparedStatement.setInt(1, blog_id);
            preparedStatement.execute();
        }finally {
            con.close();
        }
        return null;
    }
}
