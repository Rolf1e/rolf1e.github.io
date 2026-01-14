package fr.ulco.minijournal.minijournalapi.infra.sql.entities;

import fr.ulco.minijournal.minijournalapi.infra.sql.entities.articles.ArticleEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "authors")
@Data
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    // Relations
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "authors_articles",
            joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id")
    )
    private List<ArticleEntity> articles;

}
