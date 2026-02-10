package fr.ulco.minijournal.minijournalapiddd.controllers;

import fr.ulco.minijournal.minijournalapiddd.controllers.dto.out.AuthorDTO;
import fr.ulco.minijournal.minijournalapiddd.domain.services.authors.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("")
    public ResponseEntity<Collection<AuthorDTO>> getAuthors() {
        return ResponseEntity.ok(authorService.findAuthors());
    }
}
