package fr.ulco.minijournal.minijournalapi.domain.mappers;

import fr.ulco.minijournal.minijournalapi.controllers.dto.out.ArticleDTO;
import fr.ulco.minijournal.minijournalapi.controllers.dto.out.ArticleSummaryDTO;
import fr.ulco.minijournal.minijournalapi.domain.models.bo.out.ArticleBO;
import fr.ulco.minijournal.minijournalapi.infra.sql.entities.articles.ArticleAuthorJoinEntity;
import fr.ulco.minijournal.minijournalapi.infra.sql.entities.articles.ArticleEntity;
import fr.ulco.minijournal.minijournalapi.infra.sql.entities.AuthorEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleMapper {

    public static ArticleBO toBO(AuthorEntity author, ArticleEntity article) {
        return new ArticleBO(article, author);
    }

    public static ArticleBO toBO(ArticleAuthorJoinEntity join) {
        return new ArticleBO(
                join.getArticleId(),
                join.getTitle(),
                join.getContent(),
                join.getCreatedAt(),
                join.getUpdatedAt(),
                join.getAuthorId(),
                join.getAuthorName()
        );
    }


    public static ArticleSummaryDTO toSummaryDTO(ArticleBO article) {
        return new ArticleSummaryDTO(
                article.article().getId(),
                article.article().getTitle(),
                article.author().getName(),
                article.article().getCreatedAt()
        );
    }

    public static ArticleDTO toDTO(ArticleBO article) {
        return new ArticleDTO(
                article.article().getTitle(),
                article.article().getContent(),
                article.author().getName(),
                article.article().getCreatedAt(),
                article.article().getUpdatedAt()
        );
    }
}
