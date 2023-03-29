package fr.ulco.minijournal.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ulco.minijournal.DatabaseConfig;
import fr.ulco.minijournal.model.dto.in.NewAuthorDTO;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DatabaseConfig.class})
@RunWith(SpringRunner.class)
public class InMemoryDBTest {

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Order(1)
    @Test
    public void shouldCreateNewAuthor() throws Exception {
        final var content = new NewAuthorDTO("Michel");

        final var request = MockMvcRequestBuilders.post(Routes.CREATE_AUTHOR)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(content))
                .header("Authorization", "Basic " + AuthUtils.basicPayload);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Order(2)
    @Test
    public void shouldFindNewCreatedUser() throws Exception {
        final var request = MockMvcRequestBuilders.get(Routes.GET_AUTHORS_DETAILS.replace("{id}", "3"))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic " + AuthUtils.basicPayload);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\": \"Michel\"}"));
    }

}
