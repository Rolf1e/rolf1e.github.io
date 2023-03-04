package fr.ulco.minijournal.controllers;

import fr.ulco.minijournal.model.dto.out.AuthorDTO;
import fr.ulco.minijournal.model.dto.in.AuthorSearchDTO;
import fr.ulco.minijournal.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping(Routes.GET_AUTHORS)
    public ResponseEntity<Collection<String>> getAuthors() {
        final var securityContext = SecurityContextHolder.getContext();
        final var auth = securityContext.getAuthentication();
        log.info("Hello from {}, {}, {}", auth.getName(), auth.getDetails(), auth.getPrincipal());
        return ResponseEntity.ok(authorService.findNames());
    }

    @GetMapping(Routes.GET_AUTHORS_DETAILS)
    public ResponseEntity<AuthorDTO> getAuthorDetails(@PathVariable("name") final String name) {
        return authorService.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(Routes.POST_AUTHORS)
    public ResponseEntity<AuthorDTO> postAuthorDetails(@RequestBody final AuthorSearchDTO search) {
        return authorService.findByName(search.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
