package fr.ulco.minijournal.minijournalapiddd.controllers;

import fr.ulco.minijournal.minijournalapiddd.controllers.dto.out.ArticleDTO;
import fr.ulco.minijournal.minijournalapiddd.controllers.dto.out.ArticleSummaryDTO;
import fr.ulco.minijournal.minijournalapiddd.domain.adapters.LoggingAdapter;
import fr.ulco.minijournal.minijournalapiddd.domain.mappers.ArticleMapper;
import fr.ulco.minijournal.minijournalapiddd.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.minijournalapiddd.domain.services.failure.FailureService;
import fr.ulco.minijournal.minijournalapiddd.domain.services.articles.ArticleService;
import io.micrometer.core.annotation.Counted;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final FailureService failureService;
    private final LoggingAdapter log;

    @GetMapping("")
    public ResponseEntity<Collection<ArticleSummaryDTO>> getArticles(
            @RequestParam(required = false) Collection<String> authorNames
    ) {
        log.info("Fetching articles for authors: " + authorNames);
        return fetchArticles(authorNames);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id) {
        return articleService.findArticleById(Long.parseLong(id))
                .map(ArticleMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Counted(value = "minijournal_api_ddd_articles_with_error_fetch_count", description = "Number of times articles with error endpoint was called")
    @GetMapping("/errors")
    public ResponseEntity<Collection<ArticleSummaryDTO>> getArticlesWithError(
            @RequestParam(required = false) Collection<String> authorNames
    ) {
        log.info("Fetching articles with error for authors: " + authorNames);
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
