package fr.ulco.minijournal.minijournalapiddd.infra.http.model;

import java.time.LocalDateTime;

public record ArticleDO(
        String title,
        String content,
        String authorName,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt
) {
}
