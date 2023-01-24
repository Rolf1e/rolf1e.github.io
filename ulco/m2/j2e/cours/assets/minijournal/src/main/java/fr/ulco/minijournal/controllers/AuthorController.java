package fr.ulco.minijournal.controllers;

import fr.ulco.minijournal.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping(Routes.GET_AUTHORS)
    public ResponseEntity<Collection<String>> getAuthors() {
        return ResponseEntity.ok(authorService.findNames());
    }
}
