package fr.ulco.minijournal.minijournalapiddd.domain.adapters;

import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.AuthorBO;

import java.util.Collection;

public interface AuthorAdapter {
    Collection<AuthorBO> findAll();
}
