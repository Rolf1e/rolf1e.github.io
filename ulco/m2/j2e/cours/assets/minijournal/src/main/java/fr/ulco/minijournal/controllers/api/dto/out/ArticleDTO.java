package fr.ulco.minijournal.controllers.api.dto.out;

public record ArticleDTO(
        String title,
        String authorName,
        String content
) {
}
