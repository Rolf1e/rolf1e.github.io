package fr.ulco.minijournal.minijournalapiddd.domain.models.bo.out;

import java.util.List;

public record AuthorBO(Long id, String name, List<Long> articleIds) {
}
