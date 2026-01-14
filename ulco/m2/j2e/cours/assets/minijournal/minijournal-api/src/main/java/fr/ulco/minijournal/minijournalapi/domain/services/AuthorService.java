package fr.ulco.minijournal.minijournalapi.domain.services;

import fr.ulco.minijournal.minijournalapi.controllers.dto.out.AuthorDTO;
import fr.ulco.minijournal.minijournalapi.domain.mappers.AuthorMapper;
import fr.ulco.minijournal.minijournalapi.infra.sql.dao.AuthorRepository;
import fr.ulco.minijournal.minijournalapi.infra.sql.entities.AuthorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthorService {
    
    private final AuthorRepository authorRepository;

    public Collection<AuthorDTO> findAuthors() {
        // We apply a simple mapping as a business logic
        return authorRepository.findAll()
                .stream()
                .map(AuthorMapper::toDTO)
                .toList();
    }
    
    public Collection<AuthorEntity> findAuthorsByNames(Collection<String> names) {
        return authorRepository.findByNameIn(names);
    }
}
