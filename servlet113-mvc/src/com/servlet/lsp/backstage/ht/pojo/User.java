package com.servlet.lsp.backstage.ht.pojo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class User {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsd() {
        return password;
    }

    public void setPsd(String psd) {
        this.password = password;
    }

    public User(String username, String psd) {
        this.username = username;
        this.password= password;
    }
    public User() {
    }
}
