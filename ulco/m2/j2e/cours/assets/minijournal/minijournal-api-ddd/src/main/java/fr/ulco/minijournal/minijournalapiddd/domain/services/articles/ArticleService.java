package fr.ulco.minijournal.minijournalapiddd.domain.services.articles;

import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.ArticleBO;

import java.util.Collection;
import java.util.Optional;

public interface ArticleService {

    Collection<ArticleBO> findArticles(ArticleSearchBO searchCriteria);

    Optional<ArticleBO> findArticleById(Long id);

}
