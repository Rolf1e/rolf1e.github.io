package fr.ulco.minijournal.minijournalapiddd.domain.services.authors;

import fr.ulco.minijournal.minijournalapiddd.controllers.dto.out.AuthorDTO;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.LoggingAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.mappers.AuthorMapper;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.AuthorAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class DomaineAuthorService implements AuthorService {

    private final LoggingAdapter log;
    private final AuthorAdapter authorAdapter;

    public Collection<AuthorDTO> findAuthors() {
        log.info("Fetching authors");
        // We apply a simple mapping as a business logic
        return authorAdapter.findAll()
                .stream()
                .map(AuthorMapper::toDTO)
                .toList();
    }

}
