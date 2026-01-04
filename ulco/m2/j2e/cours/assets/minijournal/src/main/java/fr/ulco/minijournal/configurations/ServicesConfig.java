package fr.ulco.minijournal.configurations;

import fr.ulco.minijournal.domain.services.AuthorService;
import fr.ulco.minijournal.domain.services.articles.ArticleService;
import fr.ulco.minijournal.domain.services.articles.SqlArticleService;
import fr.ulco.minijournal.infra.sql.dao.ArticleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public ArticleService articleService(ArticleRepository articleRepository, AuthorService authorService) {
        return new SqlArticleService(articleRepository, authorService);
    }
}
