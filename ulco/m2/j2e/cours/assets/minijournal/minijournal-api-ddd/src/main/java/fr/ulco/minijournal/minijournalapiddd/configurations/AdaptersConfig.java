package fr.ulco.minijournal.minijournalapiddd.configurations;

import fr.ulco.minijournal.minijournalapiddd.domain.adapters.ArticleAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.AuthorAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.LoggingAdapter;
import fr.ulco.minijournal.minijournalapiddd.infra.Slf4jLoggingAdapter;
import fr.ulco.minijournal.minijournalapiddd.infra.http.dao.HttpArticleAdapter;
import fr.ulco.minijournal.minijournalapiddd.infra.http.dao.HttpAuthorAdapter;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.dao.SqlArticleAdapter;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.dao.SqlAuthorAdapter;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.dao.spring.SpringDataArticleRepository;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.dao.spring.SpringDataAuthorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdaptersConfig {

    @Bean
    public ArticleAdapter articleRepository(SpringDataArticleRepository springDataArticleRepository) {
//        return new HttpArticleAdapter();
        return new SqlArticleAdapter(springDataArticleRepository);
    }

    @Bean
    public AuthorAdapter authorRepository(SpringDataAuthorRepository authorRepository) {
//        return new HttpAuthorAdapter();
        return new SqlAuthorAdapter(authorRepository);
    }

    @Bean
    public LoggingAdapter loggingAdapter() {
        return new Slf4jLoggingAdapter();
    }

}
