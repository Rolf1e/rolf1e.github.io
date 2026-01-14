package fr.ulco.minijournal.minijournalui.infra.http.model;

public record ArticleSummaryDO(
        Long id,
        String title,
        String authorName,
        String publishedAt
) {
}
