package fr.ulco.minijournal.domain.model.dto.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthorSearchDTO {

    private final String name;
   
    @JsonCreator
    public static AuthorSearchDTO create(@JsonProperty("name") final String name) {
        return new AuthorSearchDTO(name);
    }
}
