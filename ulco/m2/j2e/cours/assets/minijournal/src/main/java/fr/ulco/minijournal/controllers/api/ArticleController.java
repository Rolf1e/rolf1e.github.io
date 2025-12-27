package fr.ulco.minijournal.controllers.api;

import fr.ulco.minijournal.domain.mappers.ArticleMapper;
import fr.ulco.minijournal.domain.models.bo.in.ArticleSearchBO;
import fr.ulco.minijournal.controllers.api.dto.out.ArticleDTO;
import fr.ulco.minijournal.domain.services.articles.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public ResponseEntity<Collection<ArticleDTO>> getArticles(
            @RequestParam(required = false) String authorName
    ) {
        final ArticleSearchBO searchCriteria = new ArticleSearchBO(authorName);
        final List<ArticleDTO> articles = articleService.findArticles(searchCriteria)
                .stream()
                .map(ArticleMapper::toDTO)
                .toList();
        return ResponseEntity.ok(articles);
    }

}
