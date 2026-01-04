package fr.ulco.minijournal.infra.sql.dao;

import fr.ulco.minijournal.infra.sql.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findByName(String name);
    
     Collection<AuthorEntity> findByNameIn(Collection<String> name);
}
