package fr.ulco.minijournal.minijournalapiddd.infra.sql.dao;

import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.AuthorBO;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.AuthorAdapter;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.dao.spring.SpringDataAuthorRepository;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.entities.AuthorEntity;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.entities.articles.ArticleEntity;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class SqlAuthorAdapter implements AuthorAdapter {

    private final SpringDataAuthorRepository authorRepository;

    @Override
    public Collection<AuthorBO> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(entity -> {
                    final var articleIds = extractArticleIds(entity);
                    return new AuthorBO(entity.getId(), entity.getName(), articleIds);
                })
                .toList();
    }

    private static List<Long> extractArticleIds(AuthorEntity entity) {
        return entity.getArticles()
                .stream()
                .map(ArticleEntity::getId)
                .toList();
    }
}
