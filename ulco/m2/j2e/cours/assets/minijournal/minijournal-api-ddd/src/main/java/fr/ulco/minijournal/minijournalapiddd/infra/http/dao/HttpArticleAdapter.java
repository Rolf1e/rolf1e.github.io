package fr.ulco.minijournal.minijournalapiddd.infra.http.dao;

import fr.ulco.minijournal.minijournalapiddd.domain.adapters.ArticleAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.ArticleAuthorJoinBO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.ArticleBO;
import fr.ulco.minijournal.minijournalapiddd.infra.http.dao.spring.MiniJournalApiClient;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HttpArticleAdapter implements ArticleAdapter {

    private final MiniJournalApiClient miniJournalApiClient;
   
    @Override
    public Collection<ArticleAuthorJoinBO> findArticlesFromSearchCriteria(ArticleSearchBO searchBO) {
        return List.of();
    }

    @Override
    public Optional<ArticleBO> findById(Long id) {
        return Optional.empty();
    }
}
