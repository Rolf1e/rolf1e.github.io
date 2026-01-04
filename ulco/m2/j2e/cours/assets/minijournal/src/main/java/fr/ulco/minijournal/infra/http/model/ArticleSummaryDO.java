package fr.ulco.minijournal.infra.http.model;

public record ArticleSummaryDO(
        Long id,
        String title,
        String authorName,
        String publishedAt
) {
}
