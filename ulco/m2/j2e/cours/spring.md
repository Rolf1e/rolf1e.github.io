## Sommaire

- Spring
- Spring Boot
- REST
- Security
- DATA

## Spring

[Spring](https://spring.io/projects/spring-framework) est un framework Java pour construire des applications variées.
Spring est une implémentation de [Jakarta EE](https://jakarta.ee/). Des alternatives comme Jetty, Tomcat.

Il facilite des tâches en java comme le support pour le web, l'accès à la donnée via son ORM, l'envoie d'emails, de SMS,
le scheduling, le caching en
[s'intégrant](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html) facilement.
Il possède également un support pour le langage Kotlin.

Spring est composé de plusieurs petites briques appelées modules ou "containers".

- Spring core: IoC, i18n, Data Binding, ...
- Spring tests: Mocks, TestContext, ...
- Data access: Transactions, ORM, ...
- Web Servlet: MVC, WebSockets, STOMP, ...
- Integration: REST, Scheduling, Caching, ...

## Spring Boot 2.7.0

Spring boot est un projet permettant de démarrer facilement une application Spring. Il s'occupe seule de lancer le
Contexte, les Beans via l'IoC, initier les connexions aux bases de données, mettre à l'écoute les différents endpoints
si besoin.

### Dependency Injection (DI)

[L'Injection de Dépendance](https://en.wikipedia.org/wiki/Dependency_injection) est un patron de conception dans lequel
un objet ou une fonction reçoit ses dépendances en paramètre. Ce patron à pour intérêt de mettre en évidence les
dépendances externes. Les objets deviennent moins couplés. En effet, il est plus simple de les remplacer par leurs
interfaces.

```java
public interface Hat {
}

public class Casquette extends Hat {
}

public class HautDeFormes extends Hat {
}

public class Person {
    private String name;
    private Hat hat;

    public Person(Hat hat) {
        this.name = "A name";
        this.hat = hat;
    }

    public Person(String name,
                  Hat hat) {
        this.name = name;
        this.hat = hat;
    }

    public static void main(String[] args) {
        var pierre = new Person(new Casquette());
        var marie = new Person(new HautDeFormes());
    }
}
```

### Inversion of Control (IoC)

[L'Inversion de Contrôle](https://en.wikipedia.org/wiki/Inversion_of_control) est un autre patron proche de l'Injection
de Dépendance, mais qui va plus loin en automatisant la manière dont les dépendances sont transmises aux objets. En
effet, même si l'DI est efficace pour réduire les effets de bords, en java spécialement, le code devient
particulièrement lourd avec beaucoup code boilerplate à rédiger afin de passer et construire les différentes
dépendances.

### Le Context et ses @Configuration et ses @Bean

Afin de mettre en place ces deux patrons, Spring utilise des notions comme le Context. Le Context de Spring pour se
résumer comme le cycle de vie d'une application. Il est défini par les différents `@Bean`s qui y vivent.

Un `@Bean` est une instance de classe créée par Spring qui va être injectée sur demande à d'autres `@Bean`s. Ils sont
déclarés dans une classe de configuration annotée de `@Configuration`.

```java
public class HatDealer {
    public Hat dealAHat() {
        // return a random hat
    }
}

@Configuration
public class HelloWorldConfig {
    @Bean
    public HatDealer hatDealer() {
        return new HatDealer();
    }
}

@Component
public class PersonFactory {
    private HatDealer hatDealer;
    
    public PersonFactory(HatDealer hatDealer) {
        this.hatDealer = hatDealer;
    }
    
    public Person newPerson(String name) {
        return new Person(name, hatDealer.dealAHat());
    }
}
```

Par défaut Spring utilise le typage pour résoudre l'injection. Mais il est possible de rencontrer des cas ambigus où
deux `@Bean`s de même type doivent vivre ensemble. On peut alors utiliser `@Qualifier` pour nommer notre `@Bean`.

## Spring REST

[//]: # (### Servlet, la porte vers le web !)

### Structurer notre application avec @Service

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

Bien qu'il soit possible de retourner directement un type sans passer par [`ResponseEntity`](), il est bonne pratique
d'utiliser ce dernier. En effet, pour la gestion d'erreur, il possède en API gérant les [codes HTTP]().

Java étant un langage avec un typage fort à la compilation, il nous permet de construire une API avec des messages
typés ! Un projet très connu de parsing en Java s'appelle [Jackson](). Il utilise les constructeurs et accesseurs des
objets pour pouvoir serializer (ou bien deserializer). Il propose ensuite tout un panel d'adaptateurs (JSON, XML, ...).

## Spring DATA

### Hibernate

### Transactions

### Spring DATA API, l'ORM

#### @Repository - DAO

#### Setup with Drivers

## Spring Security

### CORS

### Basic Authentication

### Filters

### Tooling

[Spring Initilizr](https://start.spring.io/) est un outil permettant de générer des projets spring.

Ce qui vient après est bonus :)

### Spring tests

### Caching avec Guava

### QueryBuilder, le dessous de la mécanique
