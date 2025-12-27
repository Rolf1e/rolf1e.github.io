package fr.ulco.minijournal.domain.services;

import fr.ulco.minijournal.domain.exceptions.AuthorNotFoundException;
import fr.ulco.minijournal.domain.model.dto.in.NewAuthorDTO;
import fr.ulco.minijournal.domain.model.dto.out.AuthorDTO;
import io.vavr.control.Either;

import java.util.Collection;
import java.util.Optional;

public interface AuthorService {
    Optional<AuthorDTO> findById(final Long id);

    Either<AuthorNotFoundException, AuthorDTO> findByName(final String name);

    Optional<AuthorDTO> createAuthor(final NewAuthorDTO newAuthor);

    Collection<String> findNames();
}
