package fr.ulco.minijournal.configurations;

import fr.ulco.minijournal.model.dao.AuthorRepository;
import fr.ulco.minijournal.services.AuthorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Bean
    public AuthorService authorService(final AuthorRepository authorRepository) {
        return new AuthorService(authorRepository);
    }
}
