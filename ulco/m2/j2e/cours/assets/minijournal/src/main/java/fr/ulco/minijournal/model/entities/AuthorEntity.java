package fr.ulco.minijournal.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Table(name = "authors")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    // Relations
    @ManyToMany(mappedBy = "authors")
    private Collection<ArticleEntity> articles;

}
