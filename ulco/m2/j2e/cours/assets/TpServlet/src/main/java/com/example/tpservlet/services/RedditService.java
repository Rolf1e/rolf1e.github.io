package com.example.tpservlet.services;

import com.example.tpservlet.model.dto.PostDTO;
import com.example.tpservlet.model.dto.RedditDTO;
import com.example.tpservlet.model.dao.RedditRepo;
import com.example.tpservlet.model.entity.PostEntity;
import com.example.tpservlet.model.entity.SubRedditEntity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RedditService {

    private final RedditRepo redditRepo;

    public RedditService() {
        this.redditRepo = new RedditRepo();
    }

    public Collection<RedditDTO> getSubReddits() {
        var subs = redditRepo.getSubReddit();
        var posts = redditRepo.getPosts()
                .stream()
                .collect(Collectors.groupingBy(PostEntity::getSub));
        return subs.stream()
                .map(sub -> toDto(posts, sub))
                .collect(Collectors.toList());
    }

    private static RedditDTO toDto(final Map<String, List<PostEntity>> posts,
                                   final SubRedditEntity sub) {
        var subPosts = posts.get(sub.getTitle())
                .stream()
                .map(post -> new PostDTO(post.getName(), post.getContenu()))
                .collect(Collectors.toList());
        return new RedditDTO(sub.getTitle(), "", subPosts);
    }

}
