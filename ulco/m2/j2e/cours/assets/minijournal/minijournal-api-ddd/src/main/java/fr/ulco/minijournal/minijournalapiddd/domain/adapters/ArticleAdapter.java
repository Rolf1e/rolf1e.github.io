package fr.ulco.minijournal.minijournalapiddd.domain.adapters;

import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.ArticleAuthorJoinBO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.ArticleBO;

import java.util.Collection;
import java.util.Optional;

public interface ArticleAdapter {
    Collection<ArticleAuthorJoinBO> findArticlesFromSearchCriteria(ArticleSearchBO searchBO);

    Optional<ArticleBO> findById(Long id);
}
