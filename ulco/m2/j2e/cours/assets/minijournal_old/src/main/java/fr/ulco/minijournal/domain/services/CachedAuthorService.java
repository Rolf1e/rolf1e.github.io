package fr.ulco.minijournal.domain.services;

import fr.ulco.minijournal.domain.exceptions.AuthorNotFoundException;
import fr.ulco.minijournal.domain.model.dto.in.NewAuthorDTO;
import fr.ulco.minijournal.domain.model.dto.out.AuthorDTO;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class CachedAuthorService implements AuthorService {

    private final AuthorService authorService;

    private final Map<Long, AuthorDTO> cache = new HashMap<>();

    @Override
    public Optional<AuthorDTO> findById(Long id) {
        final var cachedAuthor = cache.get(id);
        if (Objects.isNull(cachedAuthor)) {
            final var author = authorService.findById(id);
            author.ifPresent(dto -> cache.put(id, dto));
        }
        return Optional.ofNullable(cachedAuthor);

//        final var author = cache.computeIfAbsent(
//                id,
//                key -> authorService.findById(key).orElseThrow()
//        );
//        return Optional.of(author);
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
