package fr.ulco.minijournal.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestOperations;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@RequiredArgsConstructor
public class AuthenticationTest {
    private final RestOperations rest;

    /**
     * curl -v localhost:8080/authors -u admin:admin
     */
    public void testAuthentication() {
        final var response = rest.getForEntity("http://localhost:8080/authors", String[].class);
        if (HttpStatusCode.valueOf(200) != response.getStatusCode()) {
            System.out.println("Failed with " + response.getStatusCode() + " " + Arrays.toString(response.getBody()));
            return;
        }
        System.out.println(Arrays.toString(response.getBody()));

    }

    public static void main(String[] args) {
        final var basicPayload = Base64.getEncoder().encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8));
        final var rest = new RestTemplateBuilder()
                .defaultHeader("Authorization", "Basic " + basicPayload);
        new AuthenticationTest(rest.build())
                .testAuthentication();
    }
}
