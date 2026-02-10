## Spring Inversion of Control (IoC)

## Dependency Injection (DI) et Inversion of Control (IoC)

Voir ici: [IoC](../../../architecture/cours/di_ioc.md)

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

### Les paramètres

Vous êtes peut-être habitué à avoir un fichier `.env` ou bien une config `.json`. Java, spécifiquement Maven, a pris une
autre route avec `.properties` ou plus récemment `.yml`. Dans les dernières versions de Spring, il est possible de
rajouter un fichier `application.yml` dans le dossier `resources`.
