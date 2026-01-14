package fr.ulco.minijournal.minijournalapi.domain.mappers;

import fr.ulco.minijournal.minijournalapi.controllers.dto.out.AuthorDTO;
import fr.ulco.minijournal.minijournalapi.infra.sql.entities.AuthorEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorMapper {

    public static AuthorDTO toDTO(final AuthorEntity entity) {
        return new AuthorDTO(entity.getName());
    }

}
