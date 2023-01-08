# Java, Un peu d'histoire !

Java est un langage orienté objet "class" inventé par [James Gosling](https://en.wikipedia.org/wiki/James_Gosling)
chez [Sun Microsystems](https://en.wikipedia.org/wiki/Sun_Microsystems) en 1996. La licence est rachetée par Oracle qui
continue aujourd'hui à maintenir et faire évoluer le langage.

Une version majeure de Java est la 1.8, sorti en mars 2014 (encore aujourd'hui en LTS et jusqu'en 2030 par Oracle !).
Elle contient de grandes mises à jour comme l'API des `Streams`, [les fonctions lambdas](https://openjdk.org/jeps/126).
Une refonte de l'API du temps avec `LocalDate`.

Il possède un [garbage collector](https://en.wikipedia.org/wiki/Garbage_collection_(computer_science)) et tourne sur
la [Java Virtual Machine](https://en.wikipedia.org/wiki/Java_virtual_machine) via du bytecode.

Java existe de deux manières différentes: JDK ou JRE. Le premier contient le compilateur et la JVM, le deuxième
seulement la JVM.

# Sommaire

- [Les objets](java.md#les-objets--class-)
- [Enums](java.md#enum)
- [Static et final](java.md#static-et-final)
- [Enhanced for-loop](java.md#for-loop)
- [Gestion d'erreur](java.md#gestion-derreur)
- [Classe abstraite](java.md#classes-abstraites)
- [Interface](java.md#les-interfaces)
- [Composition](java.md#composition)
- [Le JAR](java.md#le-jar)
- [La JVM](java.md#la-jvm)
- [`Collection<E>`](java.md#javautilcollection-e)
- [`Optional<T>`](java.md#javautiloptional-t)
- [`Stream<T>`](java.md#javautilstream-e)
- [`Objects`](java.md#javautilobjects)
- [`Arrays`](java.md#javautilarrays)
- [`Collections`](java.md#javautilcollections)
- [Date and Time](java.md#javatimelocaldate-et-javatimelocaldatetime)
- [Java IO](java.md#java-inputoutput)
- [Concurrence](java.md#concurrence--multi-threading-)
- [JDBC](java.md#jdbc)
- [JEE](java.md#java-entreprise-edition--jakarta-ee-)
- [Lombok](java.md#lombok)
- [Maven](java.md#maven)

# Et beaucoup de code !

#### Les objets (class)

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
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

    public Person(String name, int age, Nationality nationality) {
        this.name = name;
        this.age = age;
        this.nationality = nationality;
    }
}
```

#### static et final

##### Static, les variables de "classe".

```java
public class Persons { // Parfois PersonUtils
    private static int A_INTEGER_STATIC_FIELD = 1;

    public static Person newFrenchPerson(String name, int age) {
        return new Person(name, age, Nationality.FR);
    }

    public static void main(String[] args) {
        var staticField = Persons.A_INTEGER_STATIC_FIELD;

        var alice = Persons.newFrenchPerson("Alice", 23);
    }
}
```

##### Vers l'immutabilité avec `final`.

Le mot clé `final` n'est pas suffisant pour faire de l'immutabilité, mais il est nécessaire. Dans les faits, `final`
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
pour les tableaux Java qui peuvent également l'utiliser).

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

## Héritage

### Classes abstraites

Java est capable de partager du code via l'abstraction. C'est une fonction puissante des langages objets.

```java
public class AbstractPerson {
    private int age;

    public final void grow() {
        age += 1;
    }

    public abstract boolean isAdult();
}

public class AdultPerson extends AbstractClass {
    @Override
    public boolean isAdult() {
        return true;
    }
}

public class ChildPerson extends AbstractClass {
    @Override
    public boolean isAdult() {
        return false;
    }
}
```

### Les interfaces

Les interfaces permettent dans les langages objets de définir des contrats. Cela permet de construire un logiciel
scalable et maintenable.

```java
public interface IPerson {
    String sayHello();
}

public class Person implements IPerson {
    private String name;

    @Override
    public String sayHello() {
        return "Hello, I am " + name;
    }
}
```

### Composition

L'héritage et la composition sont souvent comparés, mais ils servent à résoudre la même problématique: partager un code
identique entre classes. La différence notable est l'auxiliaire que l'on peut lui appliquer. Soit `est`(être) soit `a`(
avoir).
Dans l'exemple suivant, nous allons composer notre classe `Person` d'un chapeau car, une personne _possède_ (a) un
chapeau.

```java
public class Hat {
    private String color;
}

public class Person {
    private String name;
    private Hat hat;

    public Person(String name, Hat hat) {
        this.name = name;
        this.hat = hat;
    }
}
```

### Le JAR

Le [JAR](https://docs.oracle.com/javase/tutorial/deployment/jar/basicsindex.html) est une archive Java (un zip)
contenant le bytecode Java (Java compilé) prêt à être lancé par une JVM.
Pour lancer un JAR en ligne de commande `java -jar <archive>.jar`.

### La JVM

La JVM, le superpouvoir du Java qui nous permet de "Code Once - Run Everywhere !". La JVM execute
du [Java bytecode](https://en.wikipedia.org/wiki/Java_bytecode). Au runtime depuis Java 1.2, Java utilise
un [JIT](https://en.wikipedia.org/wiki/Just-in-time_compilation). La JVM remplace le développeur pour pouvoir faire du
cross plateforme. En effet, ceci est délégué à la JVM d'effectuer les instructions avec l'OS. Elle gère également la
gestion de la mémoire pour le développeur via un garbage collecteur. Ce dernier va régulièrement examiner la mémoire
pour libérer de la mémoire.

Le [classpath](https://en.wikipedia.org/wiki/Classpath), c'est une variable donnée à la JVM qui lui dit où regarder pour
charger les classes utilisées par le programme écrit. Java charge les classes selon l'ordre suivant:

- Boostrap classes, l'API du Java.
- Extension classes, les fichiers dans `(jre|jdk)/lib/ext`
- User-defined packages et libraries.

Il est verbeux à utiliser manuellement, ce pourquoi des outils comme Maven, Ant ou bien Gradle ont été créé.

### L'API [(OpenJDK)](https://openjdk.org/)

#### Quelques interfaces / objects

##### java.util.Collection<E>

- `Iterable<E>` pour les enchanced for-loops

- `Iterator<E>` définit un élément qui possède un suivant avec la méthode `hasNext()`.
- `Collection<E>` définit une collection d'éléments. Elle possède une taille `int size()` et permet la modification
  avec `add(E e)` et `remove(E e)`.
- `List<E>` définit une collection d'éléments **ordonnés**. Les implémentations les plus utilisées: `ArrayList<E>`
  et `LinkedList<E>`. La première automatise les tableaux Java. La deuxième est une liste chaînée.
- `Set<E>` définit un set mathématique → une collection d'éléments sans doublons. (Utilisant `equals(Object o)` pour
  effectuer la comparaison).

- `Map<K, V>` définit un objet clé/valeur. Avantageux pour trouver une clé rapidement ou avoir une clé différente d'un
  entier (un tableau). Implémentations courantes: `HashMap<K, V>`, `TreeMap<K, V>` et `ConcurrentMap<K, V>`. TreeMap
  garantie l'ordre de sortie. ConcurrentHashMap garantie les accès concurrents.

##### java.util.Optional<T>

Java en 1.8, a rajouté quelques monades comme `Option<T>` et `Function<T, R>` qui permettent d'émuler un peu de
programmation fonctionnelle en Java. (Fonctionne particulièrement bien avec l'API des Streams et Collections).

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

Le type `Function<T, R>` permet à Java (comme à [Scala](https://www.scala-lang.org/api/3.2.1/scala/Function0.html#))
d'approcher le langage fonctionnel en pouvant écrire des
instructions simples ([lambdas functions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html))
qui seront transformées en objet par la JVM !

##### java.util.Stream<E>

L'API des Streams est une manière efficace de pouvoir itérer dans des Collections de manière lazy, en parallèle et en
utilisant le fonctionnel.

```java
public class AnonymusPerson {
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
                .map(person -> new AnonymusPerson(person.getAge(), person.getNationality()))
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
        Objects.requireNonNull(args[0], "message optionnel"); // will throw NPE if null, quelques variantes avec default et autre erreur throwing existent.
        Objects.isNull(args[0]);
        Objects.nonNull(args[0]);
        Objects.equals(args[0], args[1]);
        Objects.deepEquals(args[0], args[1]);
    }
}
```

##### java.util.Arrays

`Arrays` nous donne des fonctions pour travailler avec les tableaux Java de manière plus aisée.

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

Depuis Java 1.8, les dates en Java sont immuables.

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

### Java Input/Output

Java possède un système de classes très robustes afin d'accéder à des resources. Nous pouvons
citer `InputStream`, `OutputStream` (à ne pas confondre avec `Stream`) ou bien `java.sql.Connection`.

Afin de ne pas avoir des fuites de performances il faut gérer ces resources manuellement.

Par exemple, ici pour lire la première ligne d'un fichier.

```java
public class IOPlayGround {

    static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }
}
```

Il faut appeler la méthode `close()` pour éviter une fuite mémoire. Le soucis est qu'il faut le faire correctement et
ne pas l'oublier. Pour cela, depuis java 7 il est possible d'utiliser un `try-with-resources`.
La classe qui doit être managée par Java doit implémenter `AutoCloseable`.

Ici `BufferedReader` rempli le contrat, on peut donc écrire:

```java
public class IOPlayGround {

    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }
}
```

Rien ne nous empêche de gérer l'erreur dans ce `try`

```java
public class IOPlayGround {

    static String firstLineOfFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            // handle 
        }
    }
}
```

### Concurrence (Multi-threading)

Java comme tout autre langage, est utilisé pour construire des applications hautes performances. Sa gestion de
threads et de processus concurrentiels a beaucoup évolué au cours du temps.

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

#### Le package java.util.concurrent depuis Java 1.5

Dans
ce [package](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/package-summary.html),
on peut trouver des choses comme `Future<V>`, `BlockingQueue<E>`, `ConcurrentMap<K, V>` et `Executor`.

Cette dernière interface avec son implémentation `ExecutorService` agit comme un thread-like subsystem. Elle contient
une threadpool et une async IO.

### Databases

Java possède une
manière [d'interagir avec SQL](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/module-summary.html).

#### JDBC

Via JDBC, Java peut exécuter toutes sortes de tâches SQL. Le driver est l'implémentation dans un langage SQL précis
utilisé pour communiquer avec la base de données par exemple [pgsql driver](https://jdbc.postgresql.org/).

- `Statement` envoie des actions SQL à la base de données (une requête par exemple).
- `PreparedStatement` permet d'effectuer des requêtes préparées (éviter les injections par exemple).
- `Connection` est le string de connexion à la base de données.
- La partie metadata est gérée via `DatabaseMetadata`.

Plus de détails [ici](https://www.baeldung.com/java-jdbc).

## Java Entreprise Edition (Jakarta EE)

[Jakarta EE](https://en.wikipedia.org/wiki/Jakarta_EE) est un [set spécifications](https://jakarta.ee/specifications/)
de ce à quoi doit répondre l'expérience de Java pour les entreprises. Ces specs touchent à des features comme le calcul
distribué ou bien les services web. Cela forme une forme de certifications pour les produits, projets implémentant ces
specs. Une [liste des produits](https://jakarta.ee/compatibility/) compatible est disponible. Cela se traduit par
l'utilisation de contrats (Les interfaces :)).

Nous verrons dans le [premier tp](../tp/tp_servlet.md) comment utiliser la spec native de JEE pour effectuer un endpoint
REST.

Par la suite nous utiliserons [Spring](./spring.md) comme implémentation principale de JEE.

# Quelques outils

## Lombok

Le projet [Lombok](https://projectlombok.org/) est une librairie Java qui aide à rendre le code moins lourd et verbeux
de Java comme les constructeurs, getters, builders.

- `@Getter` crée les getters de tous les attributs de la classe. (Le même existe pour les `@Setter`).
- `@AllAgrsConstructor` un constructeur pour tous les attributs.
- `@NoAgrConstructor` un constructeur sans paramètre.
- `@RequiredArgsConstructor` un constructeur pour tous les attributs **requis** (aka `final` attributs).
- `@Builder` crée une inner class `Example.ExampleBuilder` permettant d'avoir un "pattern builder".
- `@Slf4j` permet d'instancier un logger pour la classe facilement. (Un logger fait des choses très complexes en Java).

### Que fait lombok ?

Lombok génère du code via les annotations de Java. Il va au moment de la compilation les remplacer par du code Java
répondant à ce qui a été décrit. Vous pouvez faire l'expérience vous-même en créant une classe Java avec une
annotation `@Getter` sur un attribut. Lancez la compilation et remarquez que dans le fichier `.class` (l'ouvrir à l'aide
de `javap` ou bien d'Intellij IDEA) le getter s'y trouve bien.

## Maven

[Apache Maven](https://maven.apache.org/) est un outil de gestion de projet, en particulier pour la gestion des
dépendances (via le classpath), du build, du lancement des tests et des versions (JVM, Classes, packages). Un exemple de
projet Maven se trouve dans la partie [spring](./spring.md). Il utilise XML pour décrire les actions à effectuer.

### How does it work ?

```shell
mvn clean         # détruit le dossier /target avec toutes les .class et autres fichiers utiles à la JVM.
mvn clean install # Install les dépendances listée dans le pom.xml, lance la compilation et les tests.
mvn test          # lance les tests
mvn package       # Construit le projet pour le release

java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App # lance le jar de notre application.
```

[Maven en 5 minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

Pour ajouter une dépendance à votre projet, dans votre `pom.xml` rajoutez ceci dans la balise `<dependencies>` pour
ajouter le driver JDBC pour postgresql.

```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.5.1</version>
</dependency>
```

### Pour aller plus loin

- [Intellij IDEA à la rescousse](https://www.jetbrains.com/help/idea/maven-support.html)
- [Maven et NodeJS](https://github.com/eirslett/frontend-maven-plugin)
- [Angular application avec Maven](https://medium.com/sparkles-blog/angular-in-the-enterprise-building-angular-apps-through-maven-3ca535152f85)

## Source

- La Java doc
- Effective Java 3rd Edition de J. Bloch