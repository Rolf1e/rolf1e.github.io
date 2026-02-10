## Dependency Injection (DI)

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

Un autre exemple dans un monde plus fonctionel, même si c'est du Java :sweat_smile:.

On déclare d'abord une fonction qui applique des statistiques à une liste de nombres donnés en chaîne de caractères.

```java
private static final List<String> source = List.of("1", "2", "3", "4", "5");

public class StatsPublisher {
    static void publish(int n) {
    }
}

static void publishStats() {
    source.stream()
            .map(s -> Integer.parseInt(s))
            .forEach(n -> StatsPublisher.publish(n));
}
```

On souhaite maintenant les publier dans un fichier CSV.

```java
public class CsvExporter {
    static void dump(int n) {
    }
}

static void exportToCsv() {
    source.stream()
            .map(s -> Integer.parseInt(s))
            .forEach(n -> CsvExporter.dump(n));
}
```

Problème ici, si une mise à jour vient à se faire sur la méthode de lecture de l'input. Il faut modifier les deux
fonctions. C'est ennuyant. Pour ça, un peu de refactor est possible pour faire en sorte que les deux fonctions soient
plus génériques.

```java
static void onEachNumber(Consumer<Integer> consumer) {
    source.stream()
            .map(s -> Integer.parseInt(s))
            .forEach(n -> consumer.accept(n));
}
```

Maintenant, il est possible d'écrire ceci ! C'est l'Injection de Dépendance !

```java
public static void main(String[] args) {
    onEachNumber(StatsPublisher::publish);
    onEachNumber(CsvExporter::dump);
}
```

## Inversion of Control (IoC)

[L'Inversion de Contrôle](https://en.wikipedia.org/wiki/Inversion_of_control) est un autre patron proche de l'Injection
de Dépendance, mais qui va plus loin en automatisant la manière dont les dépendances sont transmises aux objets. En
effet, même si l'DI est efficace pour réduire les effets de bords, en java spécialement, le code devient
particulièrement lourd avec beaucoup code boilerplate à rédiger afin de passer et construire les différentes
dépendances.

## Resources

- https://en.wikipedia.org/wiki/Dependency_injection
- https://en.wikipedia.org/wiki/Inversion_of_control
- Un plus à lire https://ivan.canet.dev/blog/2024/07/15/di-without-frameworks.html
