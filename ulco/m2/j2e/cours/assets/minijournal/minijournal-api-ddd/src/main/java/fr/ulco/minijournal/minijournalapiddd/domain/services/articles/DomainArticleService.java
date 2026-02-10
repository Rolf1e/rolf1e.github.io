package fr.ulco.minijournal.minijournalapiddd.domain.services.articles;

import fr.ulco.minijournal.minijournalapiddd.controllers.dto.out.AuthorDTO;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.ArticleAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.LoggingAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.mappers.ArticleMapper;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.ArticleBO;
import fr.ulco.minijournal.minijournalapiddd.domain.services.authors.AuthorService;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class DomainArticleService implements ArticleService {

    private final LoggingAdapter log;
    private final ArticleAdapter articleAdapter;
    private final AuthorService authorService;

    @Override
    public Collection<ArticleBO> findArticles(ArticleSearchBO rawSearchCriteria) {
        final var searchCriteria = validateSearchCriteria(rawSearchCriteria);
        return articleAdapter.findArticlesFromSearchCriteria(searchCriteria)
                .stream()
                .map(ArticleMapper::toBO)
                .toList();
    }

    @Override
    public Optional<ArticleBO> findArticleById(Long id) {
        log.info("Fetching article with id: " + id);
        return articleAdapter.findById(id);
    }

    private ArticleSearchBO validateSearchCriteria(ArticleSearchBO searchCriteria) {
        if (Objects.isNull(searchCriteria.authorNames())) {
            final var authors = authorService.findAuthors();
            return new ArticleSearchBO(
                    authors.stream()
                            .map(AuthorDTO::name)
                            .toList()
            );
        }
        return searchCriteria;
    }

}
