package fr.ulco.minijournal.minijournalapi.domain.services.articles;

import fr.ulco.minijournal.minijournalapi.controllers.dto.out.AuthorDTO;
import fr.ulco.minijournal.minijournalapi.domain.mappers.ArticleMapper;
import fr.ulco.minijournal.minijournalapi.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.minijournalapi.domain.models.bo.out.ArticleBO;
import fr.ulco.minijournal.minijournalapi.domain.services.AuthorService;
import fr.ulco.minijournal.minijournalapi.infra.sql.dao.ArticleRepository;
import fr.ulco.minijournal.minijournalapi.infra.sql.entities.AuthorEntity;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class SqlArticleService implements ArticleService {

    private static final boolean USE_SPRING_API_JOIN = false;

    private final ArticleRepository articleRepository;
    private final AuthorService authorService;

    @Override
    public Collection<ArticleBO> findArticles(ArticleSearchBO rawSearchCriteria) {
        final var searchCriteria = validateSearchCriteria(rawSearchCriteria);
        final var authorNames = searchCriteria.authorNames();

        if (false == USE_SPRING_API_JOIN) {
            return articleRepository.findArticlesFromSearchCriteria(authorNames)
                    .stream()
                    .map(ArticleMapper::toBO)
                    .toList();
        }

        final var authors = authorService.findAuthorsByNames(authorNames);
        return SqlArticleService.findArticlesForEachAuthors(authors);
    }

    @Override
    public Optional<ArticleBO> findArticleById(String rawId) {
        final var id = Long.valueOf(rawId);

        return articleRepository.findById(id)
                .map(article -> {
                    final var author = article.getAuthors();
                    return ArticleMapper.toBO(author.getFirst(), article);
                });
    }


    private ArticleSearchBO validateSearchCriteria(ArticleSearchBO searchCriteria) {
        if (searchCriteria.authorNames() == null) {
            final var authors = authorService.findAuthors();
            return new ArticleSearchBO(
                    authors.stream()
                            .map(AuthorDTO::name)
                            .toList()
            );
        }
        return searchCriteria;
    }

    private static List<ArticleBO> findArticlesForEachAuthors(Collection<AuthorEntity> authors) {
        return authors.stream()
                .flatMap(SqlArticleService::findArticlesForAuthor)
                .toList();
    }

    private static Stream<ArticleBO> findArticlesForAuthor(AuthorEntity author) {
        // Here we call getArticles which is a JPA lazy loaded collection
        return author.getArticles()
                .stream()
                .map(article -> ArticleMapper.toBO(author, article));
    }


}
