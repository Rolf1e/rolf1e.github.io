package com.example.tpservlet.model.dao;

import com.example.tpservlet.model.entity.PostEntity;
import com.example.tpservlet.model.entity.SubRedditEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class HttpRedditDAO implements RedditDAO {

    private final HttpClient client = HttpClient.newBuilder()
            .build();

    @Override
    public Collection<PostEntity> getPosts(String subreddit) {
        try {
            final var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.reddit.com/r/" + subreddit + "/new.json"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            final var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return PostMapper.toEntity(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class PostMapper {
        private static final ObjectMapper mapper = new ObjectMapper();

        public static Collection<PostEntity> toEntity(final String rawBody) throws JsonProcessingException {
            final JsonNode posts = mapper.readTree(rawBody);
            final JsonNode children = posts.path("data").path("children");
            return StreamSupport.stream(children.spliterator(), false)
                    .map(node -> node.path("data"))
                    .map(data -> new PostEntity(
                            data.path("title").asText(),
                            data.path("author_fullname").asText(),
                            data.path("subreddit").asText()
                    ))
                    .collect(Collectors.toList());
        }
    }


    @Override
    public Collection<SubRedditEntity> getSubReddit(List<String> subreddits) {
        final Collection<SubRedditEntity> subs = new ArrayList<>();
        for (String subreddit : subreddits) {
            try {
                subs.add(getSubRedditEntity(subreddit));
            } catch (Exception e) {
                System.out.println("Failed to fetch subreddit: " + subreddit);
            }
        }
        return subs;
    }

    @Nonnull
    private SubRedditEntity getSubRedditEntity(String subreddit) {
        try {
            final var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.reddit.com/r/" + subreddit + "/about.json"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            final var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return SubMapper.toEntity(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class SubMapper {
        private static final ObjectMapper mapper = new ObjectMapper();

        public static SubRedditEntity toEntity(final String rawBody) throws JsonProcessingException {
            final JsonNode posts = mapper.readTree(rawBody);
            final JsonNode data = posts.path("data");
            return new SubRedditEntity(
                    data.path("title").asText(),
                    data.path("display_name").asText(),
                    data.path("header_img").asText()
            );
        }
    }


}
