package fr.ulco.minijournal.domain.mappers;

import fr.ulco.minijournal.controllers.api.dto.out.AuthorDTO;
import fr.ulco.minijournal.infra.sql.entities.AuthorEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorMapper {

    public static AuthorDTO toDTO(final AuthorEntity entity) {
        return new AuthorDTO(entity.getName());
    }

}
