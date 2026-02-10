package fr.ulco.minijournal.minijournalapiddd.domain.services.authors;

import fr.ulco.minijournal.minijournalapiddd.controllers.dto.out.AuthorDTO;

import java.util.Collection;

public interface AuthorService {

    Collection<AuthorDTO> findAuthors();
   
}
