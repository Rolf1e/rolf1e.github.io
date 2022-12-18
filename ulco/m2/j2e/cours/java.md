# Un peu d'histoire !

Java est un langage orienté objet "class" inventé par [James Gosling](https://en.wikipedia.org/wiki/James_Gosling)
chez [Sun Microsystems](https://en.wikipedia.org/wiki/Sun_Microsystems) en 1996. Aujourd'hui c'est Oracle qui détient
la licence.

Il possède un [garbage collector](https://en.wikipedia.org/wiki/Garbage_collection_(computer_science)) et tourne sur
la [Java Virtual Machine](https://en.wikipedia.org/wiki/Java_virtual_machine) via du bytecode.

Java existe de deux manières différentes: JDK ou JRE. Le premier contient le compilateur et la JVM, le deuxième
seulement
la JVM.

# Et beaucoup de code !

#### Les objets (class)

```java
public class Person {
    private String name;
    private int age;

    public MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        var alice = new Person("Alice", 23);
        var bob = new Person("Bob", 18);
    }
}
```

#### enum

```java
public enum Nationality {
    FR("Français"), BE("Belge"), EN("English");
    private String full;

    Nationality(String full) {
        this.full = full;
    }
}

public class Person {
    private String name;
    private int age;
    private Nationality nationality;

    public MyClass(String name, int age, Nationality nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }
}
```

#### static et final

Static, les variables de "classe".

```java
public class Persons { // Parfois PersonUtils
    private static int A_INTEGER_STATIC_FIELD = 1;

    public static Person newFrenchPerson(String name, int age) {
        new Person(name, age, Nationality.FR);
    }

    public static void main(String[] args) {
        var staticField = Persons.A_INTEGER_STATIC_FIELD;

        var alice = Persons.newFrenchPerson("Alice", 23);
    }
}
```

Vers l'immutabilité avec `final`.
Le mot clé `final` n'est pas suffisant pour faire de l'immutabilité mais il est nécessaire. Dans les faits, `final`
empêche un changement de référence.

```java
public class Persons {

    private static int A_INTEGER_STATIC_FIELD = 1;
    private static final int A_INTEGER_CONSTANT = 1;

    public static void main(String[] args) {
        Persons.A_INTEGER_STATIC_FIELD;
        Persons.A_INTEGER_STATIC_FIELD += 1; // mutating class variable

        Persons.A_INTEGER_CONSTANT; // access to class variable (aka static)
        Persons.A_INTEGER_CONSTANT += 1; // illegal mutation

        final var alice = Persons.newFrenchPerson("Alice", 23);
        alice = Persons.newFrenchPerson("Alice", 23); // illegal reference change
    }
}
```

#### for-loop

La boucle for "améliorée" permet d'itérer de manière efficace au travers d'objet implémentant `Iterable<T>`. (Exception
pour les tableaux java qui peuvent également l'utiliser).

```java
public class Persons {
    public static void main(String[] args) {
        final var alice = Persons.newFrenchPerson("Alice", 23);
        final var bob = Persons.newFrenchPerson("Bob", 23);
        final var persons = new Person[]{alice, bob};
        for (var person : persons) {
            System.out.println(person.getName() + " says hello !");
        }
    }
}
```

#### Gestion d'erreur

La gestion d'erreur avec Java se fait via les mots clés `throws`, `throw`, `try {} catch(...) {}`.
Exemple:

```java
public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(final String name) {
        super(name + " was not found :(");
    }
}

public class PersonHolder {

    private final Person[] persons;

    // Constructeur

    public Person findPerson(final String name) throws PersonNotFoundException {
        for (var person : persons) {
            if (name.equals(person.getName())) {
                return person;
            }
        }
        throw new PersonNotFoundException(name);
    }

    public static void main(String[] args) {
        final var persons = new Person[]{
                Persons.newFrenchPerson("Alice", 23),
                Persons.newFrenchPerson("Bob", 23)
        };
        final var personHolder = new PersonHolder(persons);
        try {
            personHolder.findPerson("Claire");
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
```

### Le JAR

Le [JAR](https://docs.oracle.com/javase/tutorial/deployment/jar/basicsindex.html) est une archive java (un zip)
contenant le bytecode java (java compilé) prêt à être lancé par une JVM.
Pour lancer un JAR en ligne de commande `java -jar <archive>.jar`.

### La JVM

La JVM, le super pouvoir du Java qui nous permet de "Code Once - Run Everywhere !". La JVM execute
du [Java bytecode](https://en.wikipedia.org/wiki/Java_bytecode). Au runtime depuis java 1.2, Java utilise
un [JIT](https://en.wikipedia.org/wiki/Just-in-time_compilation). La JVM remplace le développeur pour pouvoir faire du
cross plateforme. En effet, ceci est délégué à la JVM d'effectuer les instructions avec l'OS. Elle gère également la
gestion de la mémoire pour le développeur via un garbage collecteur. Ce dernier va régulièrement examiner la mémoire
pour libérer de la mémoire.

Le classpath // TODO

### L'API [(OpenJDK)](https://openjdk.org/)

#### Quelques interfaces / objects

##### java.util.Collection<E>

- `Iterable<E>` pour les enchanced for-loops

- `Iterator<E>` définit un élément qui possède un suivant avec la méthode `hasNext()`.
- `Collection<E>` définit une collection d'éléments. Elle possède une taille `int size()` et permet la modification
  avec `add(E e)` et `remove(E e)`.
- `List<E>` définit une collection d'éléments **ordonnés**. Les implémentations les plus utilisées: `ArrayList<E>`
  et `LinkedList<E>`. La première automatise les tableaux java. La deuxième est une liste chaînée.
- `Set<E>` définit un set mathématique => une collection d'éléments sans doublons. (Utilisant `equals(Object o)` pour
  effectuer la comparaison).

- `Map<K, V>` définit un objet clé/valeur. Avantageux pour trouver une clé rapidement ou avoir une clé différente d'un
  entier (un tableau). Implémentations courantes: `HashMap<K, V>`, `TreeMap<K, V>` et `ConcurrentMap<K, V>`. TreeMap
  garantie l'ordre de sortie. ConcurrentHashMap garantie les accès concurrents.

##### java.util.Optional<T>

Java en 1.8, a rajouté quelques monades comme `Option<T>` et `Function<T, R>` qui permettent d'émuler un peu de
programmation fonctionnelle en java. (Fonctionne particulièrement bien avec l'API des Streams et Collections).

On peut considérer réécrire notre fonction de recherche de cette façon:

```java
public class PersonHolder {

    private final Person[] persons;

    // Constructeur

    public Option<Person> findPerson(final String name) {
        for (var person : persons) {
            if (name.equals(person.getName())) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        final var persons = new Person[]{
                Persons.newFrenchPerson("Alice", 23),
                Persons.newFrenchPerson("Bob", 23)
        };
        final var personHolder = new PersonHolder(persons);

        var maybePerson = personHolder.findPerson("Claire");
        if (maybePerson.isEmpty()) {
            System.out.println("Person not found");
        } else {
            var person = maybePerson.get();
        }
    }

}
```

L'objet contient quelques méthodes de compatibilité pour le gérer de manière fonctionnelle comme `getOrElse(<default>)`
ou `getOrElseThrows(() -> new PersonNotFound("")`, `map(<predicate>)`, `filter(<predicate>)`, `flatMap(<predicate>)`,
`count()`, `sum()`

##### java.util.Stream<E>

L'API des Streams est une manière efficace de pouvoir itérer dans des Collections de manière lazy, en parallèle et en
utilisant le fonctionnel.

```java
public class AnonymPerson {
    private int age;
    private Nationality nationality;
}

public class PersonHolder {

    private final Person[] persons;

    // Constructeur

    public Option<Person> findPerson(final String name) {
        return Arrays.stream(persons)
                .filter(person -> name.equals(person.getName()))
                .findFirst();
    }

    public Long countFrenchPerson() {
        return Arrays.stream(person)
                .filter(person -> Nationality.FR.equals(person.getNationality()))
                .count();
    }

    public Collection<Person> anonymizedPerson() {
        return Arrays.stream(persons)
                .map(person -> new AnonymPerson(person.getAge(), person.getNationality()))
                .collect(Collectors.toList());
    }
}
```

#### Some utilities classes

##### java.util.Objects

`Objects` a quelques fonctions sympathiques pour tester des choses comme l'égalité et la nullité d'un objet. (Permettant
d'éviter des `if/else`).

```java
public class Examples {
    public static void main(String[] args) {
        Objects.requireNonNull(args[0], "message optionnel"); // will throw NPE if null, quelques variantes avec default et autre erreur throwing existe.
        Objects.isNull(args[0]);
        Objects.nonNull(args[0]);
        Objects.equals(args[0], args[1]);
        Objects.deepEquals(args[0], args[1]);
    }
}
```

##### java.util.Arrays

`Arrays` nous donne des fonctions pour travailler avec les tableaux java de manière plus aisée.

```java
public class Examples {
    public static void main(String[] args) {
        Stream<String> streamArgs = Arrays.stream(args);
        List<String> listArgs = Arrays.asList(args);
        Arrays.fill(args, "all elements are now this");
        Arrays.copyOf(args);
        Arrays.sort(args);
        Arrays.parallelSort(args);
        Arrays.binarySearch(args, "args1");
    }
}
```

##### java.util.Collections

Quelques fonctions pour les créer.

```java
public class Examples {
    public static void main(String[] args) {
        Collections.emptyList();
        Collections.emptySet();
        Collections.emptyMap();
        Collections.singletonList("A");
        Collections.singleton("A");
        Collections.frequency(args, "arg1");
        Collections.disjoint(Collections.singleton("a"), Collections.singleton("a"));
    }
}
```

##### java.time.LocalDate et java.time.LocalDateTime

Depuis java 1.8, les dates en Java sont immuables.

```java
public class Examples {
    public static void main(String[] args) {
        var now = LocalDate.now();
        var bestDayOfTheYear = LocalDate.of(2022, 5, 4);

        var nowTime = LocalDateTime.now();
        var bestTimeOfTheYear = LocalDateTime.of(2022, 5, 4, 12, 0);

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var bestDayOfTheYear = LocalDate.parse("2022-5-4", formatter);
    }
}
```

### Concurrence (Multi-threading)

Java comme tous les autres langages, est utilisé pour construire des applications hautes performances. Sa gestion de
threads et de processus concurrentiels à beaucoup évoluer au cours du temps.

#### Threads

Java possède une implémentation
des [Threads](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Thread.html) relativement bas
niveau.

Pour créer, il faut effectuer le code suivant:

```java
public class PrimeThread extends Thread {
    long minPrime;

    PrimeThread(long minPrime) {
        this.minPrime = minPrime;
    }

    public void run() {
        // compute primes larger than minPrime
    }

    public static void main(String[] args) {
        PrimeThread p = new PrimeThread(143);
        p.start();
    }
}
```

Ce code fait ce qu'on lui demande, mais devient peu maintenable dans de grosses applications.

#### Le package java.util.concurrent depuis java 1.5

Dans
ce [package](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/package-summary.html),
on peut trouver des choses comme `Future<V>`, `BlockingQueue<E>`, `ConcurrentMap<K, V>` et `Executor`.

Cette dernière interface avec son implémentation `ExecutorService` agit comme un thread-like subsystem. Elle contient
une threadpool et une async IO.

### Databases

Java possède une
manière [d'interagir avec SQL](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/module-summary.html).

#### JDBC

Via JDBC, Java peut exécuter toute sorte de tâches SQL.
Le driver est l'implémentation dans un langage SQL précis utilisé pour communiquer avec la base de données par
exemple [pgsql driver](https://jdbc.postgresql.org/).

- `Statement` envoie des actions SQL à la base de données (une requête par exemple).
- `PreparedStatement` permet d'effectuer des requêtes préparées (éviter les injections par exemple).
- `Connection` est la string de connexion à la base de données.
- La partie metadata est gérée via `DatabaseMetadata`.

Plus de détails [ici](https://www.baeldung.com/java-jdbc).

# Quelques outils

## Lombok

Le projet [Lombok](https://projectlombok.org/) est une librairie java qui permet de réduire un peu de code boilerplate
de java comme constructeurs, getters, builders.

- `@Getter` créer les getters de tous les attributs de la classe. (Le même existe pour les `@Setter`).
- `@AllAgrsConstructor` un constructeur pour tous les attributs.
- `@RequiredArgsConstructor` un constructeur pour tous les attributs **requis** (aka `final` attributs).
- `@Builder` créer une inner class `Example.ExampleBuilder` permettant d'avoir un pattern builder.
- `@Slf4j` permet d'instancier un logger pour la classe facilement. (Un logger fait des choses très complexes en java).

## Maven

[Apache Maven](https://maven.apache.org/) est un outil de gestion de projet, en particulier pour la gestion des
dépendances, du build et du classpath. Un exemple de projet maven se trouve dans la partie [spring](./spring.md).
