package com.example.georeview;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

public class UserLogModal {
    protected String username;
    protected boolean isLogged = false;

    public UserLogModal (@NonNull String username) {
        this.username = username;
    }

    public void setLogged(AsyncTask<String, Void, String> logged) {
        if (logged.equals("true")) isLogged = true;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
