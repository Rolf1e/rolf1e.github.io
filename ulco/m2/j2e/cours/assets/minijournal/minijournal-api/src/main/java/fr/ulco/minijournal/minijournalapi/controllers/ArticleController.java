package fr.ulco.minijournal.minijournalapi.controllers;

import fr.ulco.minijournal.minijournalapi.controllers.dto.out.ArticleDTO;
import fr.ulco.minijournal.minijournalapi.domain.mappers.ArticleMapper;
import fr.ulco.minijournal.minijournalapi.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.minijournalapi.controllers.dto.out.ArticleSummaryDTO;
import fr.ulco.minijournal.minijournalapi.domain.services.FailureService;
import fr.ulco.minijournal.minijournalapi.domain.services.articles.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final FailureService failureService;

    @GetMapping("")
    public ResponseEntity<Collection<ArticleSummaryDTO>> getArticles(
            @RequestParam(required = false) Collection<String> authorNames
    ) {
        log.info("Fetching articles for authors: {}", authorNames);
        return fetchArticles(authorNames);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id) {
        log.info("Fetching article with id: {}", id);
        return articleService.findArticleById(id)
                .map(ArticleMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/errors")
    public ResponseEntity<Collection<ArticleSummaryDTO>> getArticlesWithError(
            @RequestParam(required = false) Collection<String> authorNames
    ) {
        log.info("Fetching articles with error for authors: {}", authorNames);
        if (failureService.isFailure()) {
            log.error("Simulated failure occurred while fetching articles");
            return ResponseEntity.status(500).build();
        }
        return fetchArticles(authorNames);
    }

    private ResponseEntity<Collection<ArticleSummaryDTO>> fetchArticles(Collection<String> authorNames) {
        final var searchCriteria = new ArticleSearchBO(authorNames);
        final var articles = articleService.findArticles(searchCriteria)
                .stream()
                .map(ArticleMapper::toSummaryDTO)
                .toList();
        return ResponseEntity.ok(articles);
    }


}
