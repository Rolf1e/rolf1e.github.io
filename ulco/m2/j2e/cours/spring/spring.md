## Spring

[Spring](https://spring.io/projects/spring-framework) est un framework Java pour construire des applications variées.
Spring est une implémentation de [Jakarta EE](https://jakarta.ee/). Des alternatives comme Jetty, Tomcat.

Il facilite des tâches en Java comme le support pour le web, l'accès à la donnée via son ORM, l'envoie d'emails, de SMS,
le scheduling, le caching en
[s'intégrant](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html) facilement.
Il possède également un support pour le langage Kotlin.

Spring est composé de plusieurs petites briques appelées modules ou "containers".

- Spring core: [IoC, i18n, Data Binding, ...](./ioc.md)
- Spring tests: Mocks, TestContext, ...
- Data access: [Transactions, ORM, ...](./data.md)
- Web Servlet: MVC, WebSockets, STOMP, ...
- Integration: [REST](./rest.md), Scheduling, Caching, [Security](./security.md)...
- [Open telemetry](opentelemetry.md)

## Spring Boot 2.7.0

Spring boot est un projet permettant de démarrer facilement une application Spring. Il s'occupe seule de lancer le
Contexte, les Beans via l'IoC, initier les connexions aux bases de données, mettre à l'écoute les différents endpoints
si besoin.

Ce morceau de code est a placé à la racine de votre projet, c'est notre super application utilisant Spring Boot !

Vous pouvez utiliser [minijournal](../../cours/assets/minijournal) comme base de projet.

```groovy
plugins {
	id 'org.springframework.boot' version '4.0.1'
	id 'io.spring.dependency-management' version '1.1.7'
}
```

```java

@SpringBootApplication
class MySpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringApplication.class, args);
    }
}
```

Cette annotation `@SpringBootApplication` fait les choses suivantes:

- `@Configuration`: Tag la classe comme source de définition pour les `@Bean`s pour le Context.
- `@EnableAutoConfiguration`: Dis à Spring Boot de commencer à ajouter les `Bean`s en fonctions des paramètres du
  classpath, d'autres `@Bean`s et des `properties`.
- `@ComponentScan`: Dis à Spring de chercher les `@Component`s, `@Configuration`s, `@Service`s, `@Controller`s
  et `@Repository`s dans le package courant.


#### Tooling

[Spring Initilizr](https://start.spring.io/) est un outil permettant de générer des projets spring.

Cet outil est inclus dans intellij à la création de votre projet :) .

### Mettre un peu d'ordre dans tout ça

Il est bon vivre que de ranger correctement ses affaires pour les retrouver. Spring n'échappe pas à cette règle.

Je vous propose l'organisation suivante pour vos futures applications Spring !

```
fr.ulco.demo
    - .controllers // Entry point
        - .dto 
    - .domain // Business logic
      - .services 
      - .models
        - .bo
        - .mappers
        - .exceptions
    - .configurations // For our @Configuration, @Bean
    - .infra
      - .sql // related to sql
        - .entities // SQL Entities / DO
        - .dao // Our @Repository
      - .http // related to rest clients
        - .dao // Our REST clients
        - .model // DO for REST clients
        
        
```


