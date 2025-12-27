package fr.ulco.minijournal.domain.services;

import fr.ulco.minijournal.controllers.api.dto.out.AuthorDTO;
import fr.ulco.minijournal.domain.mappers.AuthorMapper;
import fr.ulco.minijournal.infra.sql.dao.AuthorRepository;
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
}
