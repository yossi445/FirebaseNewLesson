package com.example.yossi.firebasenewlesson;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Article {

    String userId;
    String title;
    String body;
    int likes = 0;

    String key;


    public Article(String userId, String title, String body, int likes, String key) {
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.likes = likes;
        this.key = key;
    }

    public Article()
    {

    }



    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getLikes() {
        return likes;
    }

    public String getKey() {
        return key;
    }
}
