package com.example.tpservlet.services;

import com.example.tpservlet.model.dao.HttpRedditDAO;
import com.example.tpservlet.model.dao.RedditDAO;
import com.example.tpservlet.model.dto.PostDTO;
import com.example.tpservlet.model.dto.RedditDTO;
import com.example.tpservlet.model.entity.SubRedditEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RedditService {

    private final RedditDAO redditRepo;

    public RedditService() {
//        this.redditRepo = new MockedRedditDAO();
        this.redditRepo = new HttpRedditDAO();
    }

    public Collection<RedditDTO> getSubReddits(List<String> subreddits) {
        return redditRepo.getSubReddit(subreddits)
                .stream()
                .map(this::fetch)
                .collect(Collectors.toList());
    }

    private RedditDTO fetch(final SubRedditEntity sub) {
        final var posts = redditRepo.getPosts(sub.getRedditName())
                .stream()
                .map(post -> new PostDTO(post.getAuthor(), post.getName()))
                .collect(Collectors.toList());
        return new RedditDTO(sub.getTitle(), "", posts);
    }

}
