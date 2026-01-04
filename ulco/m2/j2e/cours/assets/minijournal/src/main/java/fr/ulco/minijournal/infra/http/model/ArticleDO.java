package fr.ulco.minijournal.infra.http.model;

import java.time.LocalDateTime;

public record ArticleDO(
        String title,
        String content,
        String author,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt
) {
}
