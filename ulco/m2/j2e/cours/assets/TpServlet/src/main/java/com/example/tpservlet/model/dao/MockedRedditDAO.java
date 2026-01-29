package com.example.tpservlet.model.dao;

import com.example.tpservlet.model.entity.PostEntity;
import com.example.tpservlet.model.entity.SubRedditEntity;

import java.util.Collection;
import java.util.List;

public class MockedRedditDAO implements RedditDAO {

    @Override
    public Collection<PostEntity> getPosts(String subreddit) {
        return List.of(
                new PostEntity("A super post about Scala", "AAAA", "r/Scala"),
                new PostEntity("Another post about Scala 2", "BBBB", "r/Scala")
        );
    }

    @Override
    public Collection<SubRedditEntity> getSubReddit(List<String> subreddits) {
        return List.of(
                new SubRedditEntity("Scala", "Scala", "image")
        );
    }
}
