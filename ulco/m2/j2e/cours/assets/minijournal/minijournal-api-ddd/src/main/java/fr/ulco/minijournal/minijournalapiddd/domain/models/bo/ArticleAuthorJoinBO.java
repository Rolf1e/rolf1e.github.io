package fr.ulco.minijournal.minijournalapiddd.domain.models.bo;

import java.time.LocalDateTime;

public record ArticleAuthorJoinBO(
        Long articleId, String title, String content, LocalDateTime createdAt,
        LocalDateTime updatedAt, Long authorId, String authorName) {
}

