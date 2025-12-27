package fr.ulco.minijournal.controllers;

import fr.ulco.minijournal.controllers.api.AuthorController;
import fr.ulco.minijournal.model.dao.postgres.AuthorRepository;
import fr.ulco.minijournal.model.entities.AuthorEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthorsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorController controller;


    @Test
    public void shouldFindAuthors() throws Exception {
        final var tigran = new AuthorEntity();
        tigran.setName("Tigran");

        final var arthur = new AuthorEntity();
        arthur.setName("Arthur");
        when(authorRepository.findAll())
                .thenReturn(Arrays.asList(tigran, arthur));


        final var request = MockMvcRequestBuilders.get("/authors")
                .header("Authorization", "Basic " + AuthUtils.basicPayload);
        mvc.perform(request)
                .andExpect(status().is2xxSuccessful());

    }
}
