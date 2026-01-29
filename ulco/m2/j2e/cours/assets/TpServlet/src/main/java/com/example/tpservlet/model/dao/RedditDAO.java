package com.example.tpservlet.model.dao;

import com.example.tpservlet.model.entity.PostEntity;
import com.example.tpservlet.model.entity.SubRedditEntity;

import java.util.Collection;
import java.util.List;

public interface RedditDAO {
    Collection<PostEntity> getPosts(String subreddit);

    Collection<SubRedditEntity> getSubReddit(List<String> subreddits);
}
