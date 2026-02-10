package fr.ulco.minijournal.minijournalapiddd.infra.http.dao;

import fr.ulco.minijournal.minijournalapiddd.domain.adapters.AuthorAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out.AuthorBO;
import fr.ulco.minijournal.minijournalapiddd.infra.http.dao.spring.MiniJournalApiClient;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class HttpAuthorAdapter implements AuthorAdapter {
    private final MiniJournalApiClient miniJournalApiClient;

    @Override
    public Collection<AuthorBO> findAll() {
        return List.of();
    }
}
