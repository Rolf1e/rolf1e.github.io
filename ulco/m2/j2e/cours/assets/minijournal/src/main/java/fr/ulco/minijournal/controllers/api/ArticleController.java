package fr.ulco.minijournal.controllers.api;

import fr.ulco.minijournal.controllers.api.dto.out.ArticleDTO;
import fr.ulco.minijournal.domain.mappers.ArticleMapper;
import fr.ulco.minijournal.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.controllers.api.dto.out.ArticleSummaryDTO;
import fr.ulco.minijournal.domain.services.articles.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<Collection<ArticleSummaryDTO>> getArticles(
            @RequestParam(required = false) Collection<String> authorNames
    ) {
        final ArticleSearchBO searchCriteria = new ArticleSearchBO(authorNames);
        final List<ArticleSummaryDTO> articles = articleService.findArticles(searchCriteria)
                .stream()
                .map(ArticleMapper::toSummaryDTO)
                .toList();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id) {
        return articleService.findArticleById(id)
                .map(ArticleMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
