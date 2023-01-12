package com.example.tpservlet.model.entity;

public class PostEntity {

    private Integer id;
    private String name;
    private String contenu;
    private String sub;

    public PostEntity(Integer id, String name, String contenu, String sub) {
        this.id = id;
        this.name = name;
        this.contenu = contenu;
        this.sub = sub;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContenu() {
        return contenu;
    }

    public String getSub() {
        return sub;
    }
}
