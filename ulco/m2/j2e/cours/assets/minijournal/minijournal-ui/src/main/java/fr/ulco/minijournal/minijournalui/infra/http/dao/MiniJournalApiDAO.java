package fr.ulco.minijournal.minijournalui.infra.http.dao;

import fr.ulco.minijournal.minijournalui.infra.http.model.ArticleDO;
import fr.ulco.minijournal.minijournalui.infra.http.model.ArticleSummaryDO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collection;

@Component
@RequiredArgsConstructor
// https://docs.spring.io/spring-boot/reference/io/rest-client.html
public class MiniJournalApiDAO {

    private final RestClient api;
    
    private static class Routes {
        private static final String BASE_PATH = "/api";
        public static final String GET_ARTICLES_ENDPOINT = BASE_PATH + "/articles/errors";
        public static final String GET_ARTICLE_ENDPOINT = BASE_PATH + "/articles/{id}";
    }
    
    public ResponseEntity<Collection<ArticleSummaryDO>> fetchArticles() {
        return api.get()
                .uri(Routes.GET_ARTICLES_ENDPOINT)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });
    }
    
    public ResponseEntity<ArticleDO> fetchArticleById(String id) {
        return api.get()
                .uri(Routes.GET_ARTICLE_ENDPOINT.replace("{id}", id))
                .retrieve()
                .toEntity(ArticleDO.class);
    }
}
