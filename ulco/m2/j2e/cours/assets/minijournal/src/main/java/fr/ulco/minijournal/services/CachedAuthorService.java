package fr.ulco.minijournal.services;

import fr.ulco.minijournal.exceptions.AuthorNotFoundException;
import fr.ulco.minijournal.model.dto.in.NewAuthorDTO;
import fr.ulco.minijournal.model.dto.out.AuthorDTO;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class CachedAuthorService implements AuthorService {
    
    private final AuthorService authorService;
    
    @Override
    public Optional<AuthorDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Either<AuthorNotFoundException, AuthorDTO> findByName(String name) {
        return null;
    }

    @Override
    public Optional<AuthorDTO> createAuthor(NewAuthorDTO newAuthor) {
        return Optional.empty();
    }

    @Override
    public Collection<String> findNames() {
        return null;
    }
}
