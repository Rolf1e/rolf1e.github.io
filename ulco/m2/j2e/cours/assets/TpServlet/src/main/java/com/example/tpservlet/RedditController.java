package com.example.tpservlet;

import com.example.tpservlet.model.dto.RedditDTO;
import com.example.tpservlet.services.RedditService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.Collection;

@Path("/reddit")
public class RedditController {
    private final RedditService redditService;

    public RedditController() {
        this.redditService = new RedditService();
    }

    @GET
    @Produces("application/json")
    public Collection<RedditDTO> getPosts() {
        return redditService.getSubReddits();
    }
}
