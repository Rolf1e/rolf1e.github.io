package com.example.tpservlet.model.entity;

public class SubRedditEntity {
    private final String title;
    private final String redditName;
    private final String headerImage;

    public SubRedditEntity(String title, String redditName, String headerImage) {
        this.title = title;
        this.redditName = redditName;
        this.headerImage = headerImage;
    }
    
    public String getRedditName() {
        return redditName;
    }

    public String getTitle() {
        return title;
    }

    public String getHeaderImage() {
        return headerImage;
    }
}
