package fr.ulco.minijournal.minijournalapiddd.infra.http.model;

public record ArticleSummaryDO(
        Long id,
        String title,
        String authorName,
        String publishedAt
) {
}
