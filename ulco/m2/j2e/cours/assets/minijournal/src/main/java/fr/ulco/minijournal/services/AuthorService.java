package fr.ulco.minijournal.services;

import fr.ulco.minijournal.model.dao.AuthorRepository;
import fr.ulco.minijournal.model.dto.in.NewAuthorDTO;
import fr.ulco.minijournal.model.dto.out.AuthorDTO;
import fr.ulco.minijournal.model.entities.ArticleEntity;
import fr.ulco.minijournal.model.entities.AuthorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Collection<String> findNames() {
        return authorRepository.findAll()
                .stream()
                .map(AuthorEntity::getName)
                .collect(Collectors.toList());
    }

    public Optional<AuthorDTO> findById(final Long id) {
        return authorRepository.findById(id)
                .map(entity -> new AuthorDTO(entity.getName()));
    }

    public Optional<AuthorDTO> findByName(final String name) {
        return authorRepository.findByName(name)
                .map(entity -> new AuthorDTO(entity.getName()));
    }

    public Collection<ArticleEntity> findArticlesByName(final String name) {
        return authorRepository.findByName(name)
                .map(AuthorEntity::getArticles)
                .orElse(Collections.emptyList());
    }

    @Transactional
    public Optional<AuthorEntity> createAuthor(final NewAuthorDTO author) {
        final var newAuthor = new AuthorEntity();
        newAuthor.setName(author.name());
        return Optional.of(authorRepository.save(newAuthor));
    }

}
