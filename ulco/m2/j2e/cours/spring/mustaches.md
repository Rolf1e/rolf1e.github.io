# Spring Mustache

Spring Boot supporte le moteur de template [Mustache](https://mustache.github.io/) nativement via le starter
`spring-boot-starter-mustache`. C'est un moteur très simple est suffisant pour notre cas d'utilisation.

## Example

### Templates Mustache

```groovy
    implementation 'org.springframework.boot:spring-boot-starter-mustache'
```

Nous souhaitons afficher nos articles dans une page HTML. Voici d'abord les templates Mustache.

D'abord l'`index.mustache` qui est le template principal.

```mustache
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0 ">
    <title>Mini Journal</title>
</head>
<body>
<p>Welcome to the Mini Journal application!</p>
<p>This is a simple web application where you can write and save your daily journal entries.</p>

<p>Here is the list of articles by authors</p>
<div class="articles-list">
    {{>articlesList}}
</div>
</body>
</html>
```

Notre liste d'articles est dans un template partiel `articlesList.mustache`.

```mustache
<ul>
    {{#articles}}
        <li>
        <a href="/article/{{id}}">{{title}}</a> by {{authorName}} on {{publishedAt}}
        </li>
    {{/articles}}
</ul>
```

Et enfin notre article en lui même `article.mustache`.

```mustache
<!DOCTYPE html>
<html lang="en">
{{#article}}
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0 ">
        <title>Mini Journal - {{title}}</title>
    </head>
    <body>
    <a href="/">Home</a>
    <h1>{{title}}</h1>
    <p>by {{authorName}} on {{publishedAt}} update at {{updatedAt}}</p>
    <div class="article-content">
        {{content}}
    </div>
    </body>
{{/article}}

</body>
</html>
```

### Controller

Maintenant nous pouvons exposer dans un `@Controller` Spring nos pages HTML. On oublie pas d'injecter nos paramètres au
moteur via l'API de Mustache.

```java

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
```

## Resources

[Mustache Documentation](https://mustache.github.io/)
[Baeldung Mustache with Spring Boot](https://www.baeldung.com/spring-boot-mustache)
[Spring Boot Mustache](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

