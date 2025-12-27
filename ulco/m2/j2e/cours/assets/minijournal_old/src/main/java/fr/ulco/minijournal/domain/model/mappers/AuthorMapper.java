package fr.ulco.minijournal.domain.model.mappers;

import fr.ulco.minijournal.domain.model.dto.out.AuthorDTO;
import fr.ulco.minijournal.domain.model.entities.AuthorEntity;

public class AuthorMapper {
    
    public static AuthorDTO toDto(final AuthorEntity entity) {
        return new AuthorDTO(entity.getId(), entity.getName());
    }
    
}
