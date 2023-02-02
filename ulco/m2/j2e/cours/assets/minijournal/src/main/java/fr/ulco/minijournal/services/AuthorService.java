package fr.ulco.minijournal.services;

import fr.ulco.minijournal.model.dao.AuthorRepository;
import fr.ulco.minijournal.model.dto.out.AuthorDTO;
import fr.ulco.minijournal.model.entities.AuthorEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorService {

    private final AuthorRepository authorRepository;

    public static AuthorService create(final AuthorRepository authorRepository) {
        return new AuthorService(authorRepository);
    }

    public Collection<String> findNames() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorEntity::getName)
                .collect(Collectors.toList());
    }

    public Optional<AuthorDTO> findByName(final String name) {
        authorRepository.findByName(name)
                .ifPresent(ent -> {
                    var articles = ent.getArticles();
                    System.out.println(articles);
                });


        return authorRepository.findByName(name)
                .map(entity -> new AuthorDTO(entity.getName()));
    }

}
