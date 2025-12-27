package fr.ulco.minijournal.domain.services;

import fr.ulco.minijournal.domain.exceptions.AuthorNotFoundException;
import fr.ulco.minijournal.domain.model.dao.sql.AuthorRepository;
import fr.ulco.minijournal.domain.model.dto.in.NewAuthorDTO;
import fr.ulco.minijournal.domain.model.dto.out.AuthorDTO;
import fr.ulco.minijournal.domain.model.entities.AuthorEntity;
import fr.ulco.minijournal.domain.model.mappers.AuthorMapper;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
public class SQLAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Optional<AuthorDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Either<AuthorNotFoundException, AuthorDTO> findByName(String name) {
        return authorRepository.findByName(name)
                .toEither(new AuthorNotFoundException())
                .map(AuthorMapper::toDto);
    }

    @Override
    public Optional<AuthorDTO> createAuthor(NewAuthorDTO newAuthor) {
        return Optional.empty();
    }

    @Override
    public Collection<String> findNames() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorEntity::getName)
                .toList();
    }
}
