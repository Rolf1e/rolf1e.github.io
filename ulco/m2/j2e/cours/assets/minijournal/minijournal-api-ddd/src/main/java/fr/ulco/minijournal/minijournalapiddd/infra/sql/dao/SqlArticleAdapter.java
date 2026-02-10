package fr.ulco.minijournal.minijournalapiddd.infra.sql.dao;

import fr.ulco.minijournal.minijournalapiddd.domain.mappers.ArticleMapper;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.ArticleAuthorJoinBO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.ArticleBO;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.ArticleAdapter;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.dao.spring.SpringDataArticleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SqlArticleAdapter implements ArticleAdapter {

    private final SpringDataArticleRepository articleRepository;

    @Override
    public Collection<ArticleAuthorJoinBO> findArticlesFromSearchCriteria(ArticleSearchBO search) {
        final Collection<String> authorNames = search.authorNames();
        return articleRepository.findArticlesFromSearchCriteria(authorNames)
                .stream()
                .map(ArticleMapper::toBO)
                .toList();
    }

    @Override
    public Optional<ArticleBO> findById(Long id) {
        return articleRepository.findById(id)
                .map(article -> {
                    final var author = article.getAuthors();
                    return ArticleMapper.toBO(author.getFirst(), article);
                });
    }


}
