package com.example.ProjectBackEnd.Model;

import com.example.ProjectBackEnd.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQueryModel {
    Connection con;

    public User insetNewUser(String firstname, String lastname, String username, String password) throws SQLException {
        try {
            con = DBConnection.getMySQLConnection();
            System.out.println(username);
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO db63130500246.User (firstname, lastname, username, password) VALUES (?, ?, ?, ?);");
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.execute();
            return this.getUser(username);
        } finally {
            con.close();
        }
    }

    public User getUser(String username) throws SQLException {
        try {
            con = DBConnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM db63130500246.User WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()){
                user = new User(rs);
            }
            return user;
        }finally {
            con.close();
        }
    }

    public User getUser(int user_id) throws SQLException {
        try{
            con = DBConnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM db63130500246.User WHERE user_id = ?");
            preparedStatement.setInt(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()){
                user = new User(rs);
            }
            return user;
        }finally {
            con.close();
        }
    }

    public User signInUser(String username, String password)throws SQLException{
        try{
            con = DBConnection.getMySQLConnection();
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM db63130500246.User WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            User user = null;
            if (rs.next()){
                user = new User(rs);
            }return user;
        }finally {
            con.close();
        }
    }
}
