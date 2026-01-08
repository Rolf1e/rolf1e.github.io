package fr.ulco.minijournal.controllers.api;

import fr.ulco.minijournal.controllers.api.dto.out.AuthorDTO;
import fr.ulco.minijournal.domain.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("")
    public ResponseEntity<Collection<AuthorDTO>> getAuthors() {
        log.info("Fetching authors");
        return ResponseEntity.ok(authorService.findAuthors());
    }
}
