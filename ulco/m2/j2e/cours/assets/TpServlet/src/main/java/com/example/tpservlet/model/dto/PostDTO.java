package com.example.tpservlet.model.dto;

public class PostDTO {
    private String name;
    private String contenu;

    public PostDTO(String name, String contenu) {
        this.name = name;
        this.contenu = contenu;
    }

    public String getName() {
        return name;
    }

    public String getContenu() {
        return contenu;
    }
}
