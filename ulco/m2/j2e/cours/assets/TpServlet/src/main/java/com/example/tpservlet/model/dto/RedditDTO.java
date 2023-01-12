package com.example.tpservlet.model.dto;

import java.util.Collection;

public class RedditDTO {
    private String name;
    private String description;
    private Collection<PostDTO> post;

    public RedditDTO(String name, String description, Collection<PostDTO> post) {
        this.name = name;
        this.description = description;
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Collection<PostDTO> getPost() {
        return post;
    }
}
