package fr.ulco.minijournal;

import fr.ulco.minijournal.services.AuthorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MinijournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinijournalApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(final AuthorService authorService) {
        return (String[] args) -> {
            authorService.findNames()
                    .forEach(System.out::println);
        };
    }

}
