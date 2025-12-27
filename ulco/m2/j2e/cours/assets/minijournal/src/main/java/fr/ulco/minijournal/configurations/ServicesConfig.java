package fr.ulco.minijournal.configurations;

import fr.ulco.minijournal.domain.services.articles.ArticleService;
import fr.ulco.minijournal.domain.services.articles.SqlArticleService;
import fr.ulco.minijournal.infra.sql.dao.ArticleRepository;
import fr.ulco.minijournal.infra.sql.dao.AuthorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public ArticleService articleService(ArticleRepository articleRepository, AuthorRepository authorRepository) {
        return new SqlArticleService(articleRepository, authorRepository);
    }
}
