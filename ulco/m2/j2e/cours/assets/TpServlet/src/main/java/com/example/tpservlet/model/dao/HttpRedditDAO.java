package com.example.tpservlet.model.dao;

import com.example.tpservlet.model.entity.PostEntity;
import com.example.tpservlet.model.entity.SubRedditEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;

public class HttpRedditDAO implements RedditDAO {
    private static class PostMapper {
        private static final ObjectMapper mapper = new ObjectMapper();

        public static Collection<PostEntity> toEntity(final String rawBody) throws JsonProcessingException {
            return mapper.readValue(rawBody, new TypeReference<>() {
            });
        }
    }

    @Override
    public Collection<PostEntity> getPosts() {
        try {
            final var client = HttpClient.newBuilder()
                    .build();
            final var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.reddit.com/r/Scala/new.json"))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            final var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return PostMapper.toEntity(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<SubRedditEntity> getSubReddit() {
        return null;
    }
}
