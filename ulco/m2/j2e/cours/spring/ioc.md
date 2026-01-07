## Spring Inversion of Control (IoC)

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

### Les paramètres

Vous êtes peut-être habitué à avoir un fichier `.env` ou bien une config `.json`. Java, spécifiquement Maven, a pris une
autre route avec `.properties` ou plus récemment `.yml`. Dans les dernières versions de Spring, il est possible de
rajouter un fichier `application.yml` dans le dossier `resources`.
