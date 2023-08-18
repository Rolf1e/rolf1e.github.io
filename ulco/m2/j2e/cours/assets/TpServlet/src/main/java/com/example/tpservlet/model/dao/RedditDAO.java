package com.example.tpservlet.model.dao;

import com.example.tpservlet.model.entity.PostEntity;
import com.example.tpservlet.model.entity.SubRedditEntity;

import java.util.Collection;

public interface RedditDAO {
    Collection<PostEntity> getPosts();

    Collection<SubRedditEntity> getSubReddit();
}
