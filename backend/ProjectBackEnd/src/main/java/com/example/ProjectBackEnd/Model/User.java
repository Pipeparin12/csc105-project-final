package com.example.ProjectBackEnd.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int user_id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;

    public User(int user_id, String firstname, String lastname, String username, String password) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public User(ResultSet rs)throws SQLException {
        this.user_id = rs.getInt("user_id");
        this.firstname = rs.getString("firstname");
        this.lastname = rs.getString("lastname");
        this.username = rs.getString("username");
        this.password = rs.getString("password");
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
