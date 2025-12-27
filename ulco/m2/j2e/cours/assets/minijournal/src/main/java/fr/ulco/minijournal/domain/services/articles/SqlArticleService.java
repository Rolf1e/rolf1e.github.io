package fr.ulco.minijournal.domain.services.articles;

import fr.ulco.minijournal.domain.exceptions.NotFoundException;
import fr.ulco.minijournal.domain.mappers.ArticleMapper;
import fr.ulco.minijournal.domain.mappers.AuthorMapper;
import fr.ulco.minijournal.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.domain.models.bo.out.ArticleBO;
import fr.ulco.minijournal.infra.sql.dao.ArticleRepository;
import fr.ulco.minijournal.infra.sql.dao.AuthorRepository;
import fr.ulco.minijournal.controllers.api.dto.out.AuthorDTO;
import fr.ulco.minijournal.infra.sql.entities.AuthorEntity;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class SqlArticleService implements ArticleService {

    private static final boolean USE_SPRING_API_JOIN = false;

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Collection<ArticleBO> findArticles(ArticleSearchBO searchCriteria) {
        final AuthorEntity author = authorRepository.findByName(searchCriteria.authorName())
                .orElseThrow(() -> new NotFoundException("Cannot found Author with search criteria: " + searchCriteria.authorName()));

        if (false == USE_SPRING_API_JOIN) {
            return articleRepository.findArticlesFromSearchCriteria(searchCriteria.authorName())
                    .stream()
                    .map(ArticleMapper::toBO)
                    .toList();
        }

        return SqlArticleService.findAuthorArticles(author);
    }

    private static List<ArticleBO> findAuthorArticles(AuthorEntity author) {
        // Here we call getArticles which is a JPA lazy loaded collection
        return author.getArticles()
                .stream()
                .map(article -> ArticleMapper.toBO(author, article))
                .toList();
    }


}
