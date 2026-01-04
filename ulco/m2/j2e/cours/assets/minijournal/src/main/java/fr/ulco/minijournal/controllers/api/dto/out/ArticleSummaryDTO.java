package fr.ulco.minijournal.controllers.api.dto.out;

import java.time.LocalDateTime;

public record ArticleSummaryDTO(
        Long id,
        String title,
        String authorName,
        LocalDateTime publishedAt
) {
}
