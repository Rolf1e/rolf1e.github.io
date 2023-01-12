package com.example.tpservlet.model.entity;

public class SubRedditEntity {
    private Integer id;
    private String title;

    public SubRedditEntity(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
