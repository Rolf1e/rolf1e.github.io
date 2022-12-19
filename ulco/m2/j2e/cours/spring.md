## Sommaire

- Spring
- Spring Boot
- REST
- Security
- DATA

## Spring

[Spring](https://spring.io/projects/spring-framework) est un framework Java pour construire des applications variées.
Spring est une implémentation de [Jakarta EE](https://jakarta.ee/). Des alternatives comme Jetty, Tomcat.

Il facilite des tâches en java comme le support pour le web, l'accès à la donnée via son ORM, l'envoie d'email, de SMS,
le scheduling, le caching
en [s'intégrant](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html) facilement.
Il possède également un support pour le langage Kotlin.

Spring est composé de plusieurs petites briques appelées modules ou containers.

- Spring core: IoC, i18n, Data Binding, ...
- Spring tests: Mocks, TestContext, ...
- Data access: Transactions,
- Web Servlet: MVC, WebSockets, STOMP
- Integration: REST, Scheduling, Caching

## Spring Boot 2.7.0

Spring boot est un projet permettant de démarrer facilement une application Spring. Elle s'occupe seule de lancer le
Contexte, les Beans via l'IoC, initier les connexions aux bases de données, mettre à l'écoute les différents endpoints
si besoin.

### Dependency Injection (DI)

[L'Injection de Dépendance](https://en.wikipedia.org/wiki/Dependency_injection) est un patron de conception dans lequel
un objet ou une fonction reçoit ses dépendances en paramètre. Ce patron à pour intérêt de mettre en évidence les
dépendances externes. Les objets deviennent moins couplés. En effet, il est plus simple de les remplacer par leurs
interfaces.

### Inversion of Control (IoC)

[L'Inversion de Contrôle](https://en.wikipedia.org/wiki/Inversion_of_control) est un autre patron proche de l'Injection
de Dépendance, mais qui va plus loin en automatisant la manière dont les dépendances sont transmises aux objets. En
effet, même si l'DI est efficace pour réduire les effets de bords, en java spécialement, le code devient
particulièrement lourd avec beaucoup code boilerplate à rédiger afin de passer et construire les différentes
dépendances.

### Le Contexte et ses @Configuration et ses @Bean

Afin de mettre en place ces deux patrons, Spring utilise des notions comme le Contexte.

## Spring REST

### Servlet, la porte vers le web !

### @Controller

### @Service

## Spring Security

### CORS

### Basic Authentication

### Filters

## Spring DATA

### Hibernate

### Spring DATA API

### Transactions

### ORM

#### @Repository - DAO

#### Setup with Drivers

### Tooling

[Spring Initilizr](https://start.spring.io/) est un outil permettant de générer des projets spring.

Ce qui vient après est bonus :)

### Caching avec Guava

### QueryBuilder, le dessous de la mécanique

### Spring tests
