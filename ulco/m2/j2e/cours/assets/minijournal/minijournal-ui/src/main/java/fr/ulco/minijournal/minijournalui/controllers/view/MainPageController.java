package fr.ulco.minijournal.minijournalui.controllers.view;

import fr.ulco.minijournal.minijournalui.infra.http.dao.MiniJournalApiDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class MainPageController {
    
    private final MiniJournalApiDAO miniJournalApi;
    
    @GetMapping("/")
    public ModelAndView mainPage(Map<String, Object> model) {
        final var response = miniJournalApi.fetchArticles();
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to fetch articles from API");
        }
        
        final var articles = Objects.requireNonNull(response.getBody());
        
        model.put("articles", articles);
        
        return new ModelAndView("index", model);
    }
    
    @GetMapping("/article/{id}")
    public ModelAndView articlePage(Map<String, Object> model,
                                    @PathVariable String id) {
        final var response = miniJournalApi.fetchArticleById(id);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to fetch article from API");
        }
        final var article = Objects.requireNonNull(response.getBody());
        
        model.put("article", article);
        
        return new ModelAndView("article", model);
    }
}
