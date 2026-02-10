package fr.ulco.minijournal.minijournalapiddd.configurations;

import fr.ulco.minijournal.minijournalapiddd.domain.adapters.ArticleAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.AuthorAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.LoggingAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.services.failure.DomainFailureService;
import fr.ulco.minijournal.minijournalapiddd.domain.services.failure.FailureService;
import fr.ulco.minijournal.minijournalapiddd.domain.services.articles.ArticleService;
import fr.ulco.minijournal.minijournalapiddd.domain.services.articles.DomainArticleService;
import fr.ulco.minijournal.minijournalapiddd.domain.services.authors.AuthorService;
import fr.ulco.minijournal.minijournalapiddd.domain.services.authors.DomaineAuthorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public ArticleService articleService(ArticleAdapter articleAdapter, AuthorService authorService, LoggingAdapter log) {
        return new DomainArticleService(log, articleAdapter, authorService);
    }

    @Bean
    public AuthorService authorService(AuthorAdapter authorAdapter, LoggingAdapter log) {
        return new DomaineAuthorService(log, authorAdapter);
    }

    @Bean
    public FailureService failureService(LoggingAdapter log) {
        return new DomainFailureService(log);
    }

}
