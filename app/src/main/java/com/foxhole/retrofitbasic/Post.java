package com.foxhole.retrofitbasic;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

public class Post {

    public int userId;

    @SerializedName("id")
    public Integer UID;

    public String title;

    @SerializedName("body")
    public String text;

    public Post(int UID, String title, String text) {
        this.UID = UID;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
