package fr.ulco.minijournal.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class AuthenticationIntegrationTest {

    /**
     * You need to launch your server before launching this test.
     * curl -v localhost:8080/authors -u admin:admin
     */
    @Test
    public void testAuthentication() {
        final var basicPayload = Base64.getEncoder().encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8));
        final var rest = new RestTemplateBuilder()
                .defaultHeader("Authorization", "Basic " + basicPayload)
                .build();

        final var response = rest.getForEntity("http://localhost:8080/authors", String[].class);
        if (HttpStatusCode.valueOf(200) != response.getStatusCode()) {
            Assertions.fail("Failed with " + response.getStatusCode() + " " + Arrays.toString(response.getBody()));
        }
        Assertions.assertArrayEquals(response.getBody(), new String[]{"Tigran", "Arthur"});
    }

}
