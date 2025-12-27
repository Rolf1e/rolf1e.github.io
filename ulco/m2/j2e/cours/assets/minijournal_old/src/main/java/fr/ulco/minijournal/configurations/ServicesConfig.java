package fr.ulco.minijournal.configurations;

import fr.ulco.minijournal.domain.model.dao.sql.AuthorRepository;
import fr.ulco.minijournal.domain.services.AuthorService;
import fr.ulco.minijournal.domain.services.SQLAuthorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public AuthorService authorService(final AuthorRepository authorRepository) {
        return new SQLAuthorService(authorRepository);
    }
}
