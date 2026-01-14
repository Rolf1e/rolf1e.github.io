package fr.ulco.minijournal.minijournalapi.controllers.dto.out;

import java.time.LocalDateTime;

public record ArticleDTO(
        String title,
        String content,
        String authorName,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt
) {
}
