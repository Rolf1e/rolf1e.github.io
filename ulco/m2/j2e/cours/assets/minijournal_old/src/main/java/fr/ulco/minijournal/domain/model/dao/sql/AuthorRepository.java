package fr.ulco.minijournal.domain.model.dao.sql;

import fr.ulco.minijournal.domain.model.entities.AuthorEntity;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Option<AuthorEntity> findByName(final String name);
}
