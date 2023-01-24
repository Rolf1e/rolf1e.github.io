package fr.ulco.minijournal.services;

import fr.ulco.minijournal.model.dao.AuthorRepository;
import fr.ulco.minijournal.model.entities.AuthorEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
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

}
