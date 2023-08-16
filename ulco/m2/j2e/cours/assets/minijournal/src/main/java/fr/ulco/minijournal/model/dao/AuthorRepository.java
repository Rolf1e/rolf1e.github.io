package fr.ulco.minijournal.model.dao;

import fr.ulco.minijournal.model.entities.AuthorEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Option<AuthorEntity> findByName(final String name);
}
