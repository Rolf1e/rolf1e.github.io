package fr.ulco.minijournal.domain.model.dao.sql;

import fr.ulco.minijournal.domain.model.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    
}
