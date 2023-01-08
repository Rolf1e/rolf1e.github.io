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

[Hibernate](https://hibernate.org/) est un ORM mais aussi une suite d'outils interagissant avec la donnée.

Un Object Relational Mapping est une technique de programmation convertissant le système de type d'un langage en
utilisant la programmation orientée objet. Cela évite de devoir maintenir la transformation SQL -> Java, de maintenir
des requêtes avec des `String` magiques. Certain vont plus loin avec des fonctionnalités pour la gestion du modèle de
données (schéma, migration).

Hibernate est aussi implémentation de l'API Java de persistance (JEE).

### Spring DATA API, l'ORM

#### Setup with Drivers

#### @Entity - POJO / DO

D'abord, il nous faut une classe (POJO / DO) représentant notre schéma SQL.

```java

@Entity
@Table(name = "Person")
@Setter
@Getter
@NoArgConstructor
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    // Modification fields
    @Basic
    @Column(name = "created_at", updatable = false, columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;

    @Basic
    @Column(name = "updated_at", columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private LocalDateTime updatedAt;
}
```

#### @Repository - DAO

Il faut maintenant un moyen d'accéder à notre donnée, cela s'appel un DAO.

Avec Spring, il faut faire créer une interface implémentant
soit [`JpaRepository`](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html)
ou
bien  [`CrudRepository`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html)
ces interfaces acceptent deux paramètres
`T` et `ID`. Le premier est notre entité, le deuxième est le type de la clé primaire.

```java

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
}
```

Notre DAO peut maintenant accéder des méthodes comme `count()`, `save(S entity)`, `find*()`.

Il est possible de définir ces propres méthodes pour accéder notre donnée. Ces méthodes doivent cela dit respecter
certains critères dans leur signature.

- Le nommage
- Le type de retour
- Les paramètres

Cela permet de construire des queries en ne codant que du Java !

```java

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    Optional<PersonEntity> findByName(String name);
}
```

[La spécification des repositories](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories)

[Liste de tous les mots clés possible](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject)

Il est également possible d'utiliser l'annotation `@Query` afin de gérer les queries avec des `String`.

```java

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
    @Query("SELECT p FROM Person p WHERE p.age = :age")
    Optional<Collection<PersonEntity>> findAllByAge(Integer age);
}
```

[Tutoriel pour @Query](https://www.baeldung.com/spring-data-jpa-query)

### SQL Relations

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
