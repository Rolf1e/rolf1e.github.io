package fr.ulco.minijournal.domain.services.articles;

import fr.ulco.minijournal.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.domain.models.bo.out.ArticleBO;

import java.util.Collection;

public interface ArticleService {

    Collection<ArticleBO> findArticles(ArticleSearchBO searchCriteria);

}
