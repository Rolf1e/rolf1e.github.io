package fr.ulco.minijournal.model.mappers;

import fr.ulco.minijournal.model.dto.out.AuthorDTO;
import fr.ulco.minijournal.model.entities.AuthorEntity;

public class AuthorMapper {
    
    public static AuthorDTO toDto(final AuthorEntity entity) {
        return new AuthorDTO(entity.getId(), entity.getName());
    }
    
}
