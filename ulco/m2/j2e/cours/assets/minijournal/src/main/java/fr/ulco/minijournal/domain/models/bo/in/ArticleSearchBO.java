package fr.ulco.minijournal.domain.models.bo.in;

import java.util.Collection;

public record ArticleSearchBO(Collection<String> authorNames) {
}
