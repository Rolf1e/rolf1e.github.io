package fr.ulco.minijournal.minijournalapiddd.domain.mappers;

import fr.ulco.minijournal.minijournalapiddd.controllers.dto.out.AuthorDTO;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.AuthorBO;
import fr.ulco.minijournal.minijournalapiddd.infra.sql.entities.AuthorEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorMapper {

    public static AuthorDTO toDTO(final AuthorBO bo) {
        return new AuthorDTO(bo.name());
    }

}
