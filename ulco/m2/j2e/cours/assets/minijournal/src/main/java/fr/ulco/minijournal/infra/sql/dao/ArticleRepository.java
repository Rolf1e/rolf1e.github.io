package fr.ulco.minijournal.infra.sql.dao;

import fr.ulco.minijournal.infra.sql.entities.articles.ArticleAuthorJoinEntity;
import fr.ulco.minijournal.infra.sql.entities.articles.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    
    @Query(value = """
            SELECT ar.id as articleId,
                ar.title as title,
                ar.content as content,
                ar.created_at as createdAt,
                ar.updated_at as updatedAt,
                ah.id as authorId,
                ah.name as authorName
            FROM articles ar
            JOIN authors_articles aa on ar.id = aa.article_id
            JOIN authors ah on aa.author_id = ah.id
            WHERE ah.name IN :authorNames""", nativeQuery = true)
    Collection<ArticleAuthorJoinEntity> findArticlesFromSearchCriteria(Collection<String> authorNames);
    
}
