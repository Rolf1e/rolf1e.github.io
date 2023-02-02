package fr.ulco.minijournal;

import fr.ulco.minijournal.configurations.ServicesConfig;
import fr.ulco.minijournal.services.AuthorService;
import fr.ulco.minijournal.utils.TestUnitJpaConfig;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {TestUnitJpaConfig.class, ServicesConfig.class})
public class AuthorServiceIntegrationTest {

    @Autowired
    public AuthorService authorService;

    @Test
    public void should_fetch_authors() {
        final var names = authorService.findNames();
        Assertions.assertIterableEquals(List.of("Tigran", "Arthur"), names);
    }
}
