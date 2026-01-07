## Spring REST

### @Controller, notre endpoint

L'annotation `Controller` permet d'enregistrer en controller auprès de Spring. Il existe une spécification
`@RestController` qui déclarant un controller REST. L'annotation hérite de `@Component`. Elle accepte en paramètre une
chaîne de caractères qui définie une racine commune pour les routes que le controller contiendra.

Afin de définir un endpoint dans notre application, il faut utiliser les annotations suivantes.
`@Mapping`, `@GetMapping`, `@PostMapping`.

```java

@RestController("hello")
public class HelloWorldController {

    @GetMapping(value = "/world")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World !");
    }
}
```

Bien qu'il soit possible de retourner directement un type sans passer
par [`ResponseEntity`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html),
il est bonne pratique d'utiliser ce dernier. En effet, pour la gestion d'erreur, il possède en API gérant
les [codes HTTP](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status).

Java étant un langage avec un typage fort à la compilation, il nous permet de construire une API avec des messages
typés ! Un projet très connu de parsing en Java
s'appelle [Jackson](https://www.baeldung.com/jackson-object-mapper-tutorial). Il utilise les constructeurs et accesseurs
des objets pour pouvoir serializer (ou bien deserializer). Il propose ensuite tout un panel d'adaptateurs (JSON,
XML, ...). Il possède également une manière plus manuelle en utilisant un `ObjetMapper`.

### RestOperations et RestTemplate

Nativement, Java permet de faire des opérations sur le réseau à travers `HttpUrlConnection`. Spring ajoute au-dessus
cette API sa propre interprétation `RestOperations` et plus précisément `RestTemplate`. Son API est simple et permet la
deserialization autonome dans des `Class<T>` Java.

```java
public class PlayGround {
    public void http() {
        final var rest = new RestTemplateBuilder()
                .defaultHeader("Authorization", "Basic " + basicPayload);

        final var response = rest.getForEntity("http://localhost:8080/authors", String[].class);

    }
}
```

### Open API (Swagger)

Il est possible de générer un swagger avec Open API. Cela permet de facilement partager les specs de l'API avec d'autres
équipes.

```xml

<dependencies>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.0.2</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
</dependencies>
```

Vous pouvez configurer l'endroit où sera exposé le swagger avec ce paramètre.

```yml
springdoc:
  swagger-ui.path: /docs
```

En occurrence, ici : http://localhost:8080/docs

- [La documentation officielle](https://springdoc.org/v2/) il faut utiliser la version `/v2` pour une version `3.x.x` de
  Spring Boot.
- [Tutoriel Baeldung](https://www.baeldung.com/spring-rest-openapi-documentation)
