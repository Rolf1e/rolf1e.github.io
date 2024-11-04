package fr.ulco.minijournal.controllers;

import fr.ulco.minijournal.model.dto.in.AuthorSearchDTO;
import fr.ulco.minijournal.model.dto.in.NewAuthorDTO;
import fr.ulco.minijournal.model.dto.out.ArticleDTO;
import fr.ulco.minijournal.model.dto.out.AuthorDTO;
import fr.ulco.minijournal.services.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthorController {
    
    private final AuthorService authorService;
    
    @GetMapping()
    public ResponseEntity<Collection<ArticleDTO>> getArticles() {
        return ResponseEntity.ok(authorService.findArticles());
    }

    
    @GetMapping(Routes.GET_AUTHORS)
    public ResponseEntity<Collection<String>> getAuthors() {
        return ResponseEntity.ok(authorService.findNames());
    }

    @GetMapping("/private" + Routes.GET_AUTHORS)
    public ResponseEntity<Collection<String>> getPrivateAuthors() {
        final var securityContext = SecurityContextHolder.getContext();
        final var auth = securityContext.getAuthentication();
        log.info("Hello from {}, {}, {}", auth.getName(), auth.getDetails(), auth.getPrincipal());
        return ResponseEntity.ok(authorService.findNames());
    }


    @GetMapping(Routes.GET_AUTHORS_DETAILS)
    public ResponseEntity<AuthorDTO> getAuthorDetails(@PathVariable("id") final Long id) {
        return authorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(Routes.POST_AUTHORS)
    public ResponseEntity<AuthorDTO> postAuthorDetails(@RequestBody final AuthorSearchDTO search) {
        return authorService.findByName(search.getName())
                .map(ResponseEntity::ok)
                .getOrElse(ResponseEntity.notFound().build());
    }

    @PostMapping(Routes.CREATE_AUTHOR)
    public ResponseEntity<Object> createAuthor(@RequestBody final NewAuthorDTO newAuthor) {
        return authorService.createAuthor(newAuthor)
                .map(author -> {
                    final var uri = Routes.GET_AUTHORS_DETAILS.replace("{id}", author.id() + " ");
                    return ResponseEntity.created(URI.create(uri))
                            .build();
                })
                .orElse(ResponseEntity.badRequest().build());
    }
}
