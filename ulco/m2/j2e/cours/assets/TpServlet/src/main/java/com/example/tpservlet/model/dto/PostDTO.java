package com.example.tpservlet.model.dto;

public class PostDTO {

    private final String author;
    private final String name;

    public PostDTO(String author, String name) {
        this.author = author;
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

}
