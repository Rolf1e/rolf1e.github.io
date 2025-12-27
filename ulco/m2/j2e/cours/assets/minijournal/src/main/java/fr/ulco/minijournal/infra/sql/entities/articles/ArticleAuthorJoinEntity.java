package fr.ulco.minijournal.infra.sql.entities.articles;

import java.time.LocalDateTime;

public interface ArticleAuthorJoinEntity {

    Long getArticleId();

    String getTitle();

    String getContent();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    Long getAuthorId();

    String getAuthorName();
}
