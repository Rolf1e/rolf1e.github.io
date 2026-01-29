package com.example.tpservlet.model.entity;

public class PostEntity {

    private final String name;
    private final String author;
    private final String subreddit;

    public PostEntity(String name, String author, String subreddit) {
        this.name = name;
        this.author = author;
        this.subreddit = subreddit;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubreddit() {
        return subreddit;
    }

}
