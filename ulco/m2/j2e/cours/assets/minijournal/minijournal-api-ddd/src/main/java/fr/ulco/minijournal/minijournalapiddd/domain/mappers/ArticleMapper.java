package fr.ulco.minijournal.minijournalapiddd.domain.mappers;

import fr.ulco.minijournal.minijournalapiddd.controllers.dto.out.ArticleDTO;
import fr.ulco.minijournal.minijournalapiddd.controllers.dto.out.ArticleSummaryDTO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.ArticleAuthorJoinBO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.ArticleBO;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.entities.AuthorEntity;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.entities.articles.ArticleAuthorJoinEntity;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.entities.articles.ArticleEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArticleMapper {

    public static ArticleBO toBO(AuthorEntity author, ArticleEntity article) {
        return new ArticleBO(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                author.getId(),
                author.getName()
        );
    }

    public static ArticleAuthorJoinBO toBO(ArticleAuthorJoinEntity entity) {
        return new ArticleAuthorJoinBO(
                entity.getArticleId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getAuthorId(),
                entity.getAuthorName()
        );
    }

    public static ArticleBO toBO(ArticleAuthorJoinBO join) {
        return new ArticleBO(
                join.articleId(),
                join.title(),
                join.content(),
                join.createdAt(),
                join.updatedAt(),
                join.authorId(),
                join.authorName()
        );
    }


    public static ArticleSummaryDTO toSummaryDTO(ArticleBO article) {
        return new ArticleSummaryDTO(
                article.id(),
                article.title(),
                article.authorName(),
                article.createdAt()
        );
    }

    public static ArticleDTO toDTO(ArticleBO article) {
        return new ArticleDTO(
                article.title(),
                article.content(),
                article.authorName(),
                article.createdAt(),
                article.updatedAt()
        );
    }
}
