package fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out;

import java.time.LocalDateTime;

public record ArticleBO(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long authorId,
        String authorName
) {
}
