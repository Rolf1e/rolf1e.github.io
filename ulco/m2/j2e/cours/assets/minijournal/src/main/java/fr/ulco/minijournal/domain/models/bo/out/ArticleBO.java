package fr.ulco.minijournal.domain.models.bo.out;

import fr.ulco.minijournal.infra.sql.entities.articles.ArticleEntity;
import fr.ulco.minijournal.infra.sql.entities.AuthorEntity;

import java.time.LocalDateTime;

public record ArticleBO(ArticleEntity article, AuthorEntity author) {

    public ArticleBO(
            Long articleId,
            String title,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Long authorId,
            String authorName
    ) {
        final ArticleEntity article = new ArticleEntity() {{
            setId(articleId);
            setTitle(title);
            setContent(content);
            setCreatedAt(createdAt);
            setUpdatedAt(updatedAt);
        }};
        final AuthorEntity author = new AuthorEntity() {{
            setId(authorId);
            setName(authorName);
        }};
        this(article, author);
    }
}
