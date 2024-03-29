package com.example.tpservlet.model.dao;

import com.example.tpservlet.model.entity.PostEntity;
import com.example.tpservlet.model.entity.SubRedditEntity;

import java.util.Collection;
import java.util.List;

public class MockedRedditDAO implements RedditDAO {

    @Override
    public Collection<PostEntity> getPosts() {
        return List.of(
                new PostEntity(1, "A post", "A super post about Scala", "Scala")
        );
    }

    @Override
    public Collection<SubRedditEntity> getSubReddit() {
        return List.of(
                new SubRedditEntity(1, "Scala")
        );
    }
}
