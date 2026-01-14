package fr.ulco.minijournal.minijournalapi.domain.models.bo.in;

import java.util.Collection;

public record ArticleSearchBO(Collection<String> authorNames) {
}
