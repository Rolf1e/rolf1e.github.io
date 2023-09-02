# TP Java

Le but de ce tp de Java est de se familiariser avec l'API de Java par l'exemple.

Vous allez pour cela écrire plusieurs snippets de code répondant à des cas d'utilisations. Vous pouvez utiliser JShell
ou bien une simple classe Java avec une méthode `main`.

## Cas d'utilisations:

### Avec l'api des streams java

#### 1. Construire la liste des nombres entre 0 et 15

<details> 

<summary> Réponse </summary>

```java

class PlayGround {

    static List<Integer> withListApi() {
        return Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    }

    static List<Integer> withAdvanceStreamApi() {
        return Stream.iterate(0, n -> n + 1)
                .limit(16)
                .toList();
    }
}

```

</details> 

#### 2. A partir de la liste précédente, multiplier tous ses éléments par deux puis diviser les par trois

<details> 

<summary> Réponse </summary>

```java

class PlayGround {

    static List<Integer> withTwoSteps() {
        return Stream.iterate(0, n -> n + 1)
                .limit(16)
                .map(n -> n * 2)
                .map(n -> n / 3)
                .toList();
    }

    // Little trick here, it does not change performance wise, since streams are lazy

    static List<Integer> withOneStep() {
        return Stream.iterate(0, n -> n + 1)
                .limit(16)
                .map(PlayGround::computation)
                .toList();
    }


    static Integer computation(Integer n) {
        return n * 2 / 3;
    }
}

```

</details> 

#### 3. Vous avez en entrée une liste de personnes, nous cherchons à filtrer les adultes avec un chapeau.

```java
class PlayGround {

    static record Person(
            String name,
            Integer age,
            Boolean hat
    ) {
    }

    static final int LEGAL_ADULT_AGE = 18;

    val adultsWithHat = PlayGround.filterAdultsWithHat(
            Arrays.asList(
                    new PlayGround.Person("Tigran", 24, true),
                    new PlayGround.Person("Antoine", 20, false),
                    new PlayGround.Person("Chloé", 11, true)
            ),
            18);
}
```

<details> 

<summary> Réponse </summary>

```java

class PlayGround {

    record Person(String name, Integer age, Boolean hat) {
    }

    static final int LEGAL_ADULT_AGE = 18;

    static Collection<String> filterAdultsWithHat(Collection<Person> persons, int legalAdultAge) {
        return persons
                .stream()
                .filter(person -> person.age >= legalAdultAge)
                .filter(person -> person.hat)
                .map(Person::name)
                .toList();

    }


}

```

</details> 

#### 4. Groupez ces livres par noms d'auteur

```java
class PlayGround {

    record Book(String name, String author) {
    }

    Collection<Book> books = List.of(
            new Book("Livre 1", "Author 1"),
            new Book("Livre 2", "Author 1"),
            new Book("Livre 3", "Author 2"),
            new Book("Livre 4", "Author 1"),
            new Book("Livre 5", "Author 3")
    );

    Map<String, Collection<Book>> groupedByAuthors = groupByAuthor(books);
}
```

<details> 

<summary> Réponse </summary>

```java

class PlayGround {

    static groupByAuthor(Collection<Book> books) {
        return books.stream()
                .collect(Collectors.groupingBy(Book::author));
    }

}

```

</details> 

#### 5. Decks de cartes ! Dans cette exemple, nous souhaitons initialiser un deck de cartes. Pour cela vous disposez:

```java
enum Rank {KING, QUEEN, TWO, ACE}

enum Suit {DIAMOND, CLUB, SPADE, HEART}

record Card(Rank rank, Suit suit) {
}
```

Il nous faut pour réaliser cette tâche calculer toutes les paires possibles. Utiliser une combinaison de `flatMap`
et `map` opérators.

<details> 

<summary> Réponse </summary>

```java

import java.util.Collection;
import java.util.stream.Stream;

class PlayGround {

    enum Rank {KING, QUEEN, TWO, ACE}

    enum Suit {DIAMOND, CLUB, SPADE, HEART}

    record Card(Rank rank, Suit suit) {
    }

    static Collection<Card> newDeck() {
        return Stream.of(Suit.values())
                .flatMap(suit -> Stream.of(Rank.values())
                        .map(rank -> new Card(rank, suit))
                )
                .toList();
    }
}

```

</details> 

#### 6. Unchecked exceptions et Streams. Un des inconvénients des Streams et qu'il est impossible d'utiliser les `checked`.

```java

import java.util.stream.Stream;

class PlayGround {

    static Integer methodThrowingCheckedException(int i) throws Exception {
        throw new Exception("checked");
    }

    static void run() {
        var numbers = Stream.iterate(0, n -> n + 1)
                .limit(15)
                .map(PlayGround::methodThrowingCheckedException) // ici le code ne compile pas
                .toList();
    }
}

```

Essayez de trouver des hacks pour faire compiler ce code sans trop perdre d'informations !

<details> 

<summary> Réponse </summary>

```java

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

class PlayGround {

    static Integer methodThrowingCheckedException(int i) throws Exception {
        throw new Exception("checked");
    }

    static void run() {
        // Solution 1
        var numbers = Stream.iterate(0, n -> n + 1)
                .limit(15)
                .map(i -> {
                    try {
                        return PlayGround.methodThrowingCheckedException(i);
                    } catch (Exception e) {
                        // handle here, but the issue is, what to return :/ 
                        // we could transform into a Runtime but meeh
                    }
                })
                .toList();

        // Solution 2 
        var numbers = Stream.iterate(0, n -> n + 1)
                .limit(15)
                .map(i -> {
                    try {
                        return Optional.of(PlayGround.methodThrowingCheckedException(i));
                    } catch (Exception e) {
                        return Optional.empty(); // Not bad, but we loose some information, logging could be enough tho
                    }
                })
                .filter(Optional::isPresent)
                .toList();


        // Solution 3 using FP
        var numbers = Stream.iterate(0, n -> n + 1)
                .limit(15)
                .map(PlayGround::methodWrapped) // Here it compiles ! And we do not loose information, FP is wonderful :-)
                .toList();


    }

    // For the example to be a Monad, we would need to add function like `flatMap`, `map`, `filter` 
    // and verify the three lows, but well you got what we want to do :p
    interface Either<L, R> extends Iterable<R> {

        static <L, R> Either<L, R> right(R right) {
            return new Right<>(right);
        }

        static <L, R> Either<L, R> left(L left) {
            return new Left<>(left);
        }

        boolean isLeft();

        L getLeft();

        boolean isRight();

        R getRight();

        final class Right<L, R> implements Either<L, R> {
            private final R value;

            private Right(R right) {
                this.value = right;
            }
            // complete implementation
        }

        final class Left<L, R> implements Either<L, R> {
            private final L value;

            private Left(L left) {
                this.value = left;
            }
            // complete implementation
        }

    }


}

```

Un peu plus de lecture pour une solution plus javaesque : https://stackoverflow.com/a/27644392
</details> 

### Jouons un peu avec le bytecode !

Créer un fichier `ByteCodePlayGround.java`.

```java

public class ByteCodePlayGround {

    Integer a;
    private String b;
    private final String c;

    public ByteCodePlayGround(String c) {
        this.c = c;
    }

    public static void main(String[] args) {

    }
}
```

Compiler le code à l'aide de `javac ByteCodePlayGround`. Puis nous allons maintenant examiner le bytecode à l'aide
de `javap -v ByteCodePlayGround.class`.

### Exercice de mémoire

À l'aide du schéma de le mémoire vu dans le cours, donner la sortie du programme suivant.

```java
public class JVMPlayGround {
    static class Container {
        private String initial = "A";

        public String getInitial() {
            return this.initial;
        }

        public void setInitial(String initial) {
            this.initial = initial;
        }
    }

    public static void main(String[] args) {
        JVMPlayGround main = new JVMPlayGround();
        main.start();
    }

    private void start() {
        String last = "Z";
        Container container = new Container();
        container.setInitial("C");
        another(container, last);
        System.out.print(container.getInitial());
    }

    private void another(Container initialHolder, String newInitial) {
        newInitial.toLowerCase();
        initialHolder.setInitial("B");
        Container initial2 = new Container();
        initialHolder = initial2;
        System.out.print(initialHolder.getInitial());
        System.out.print(newInitial);

    }
}

```

<details>

<summary>Réponse</summary>

<h4>Predication of the outcome</h4>

Le tableau se lit du bas vers le haut pour garder le "stack".

```
scope      | stack          | heap                   
-----------|----------------|------------------------
(another)> | initial2      -|-> Container() -> "A"   
           |                |   ^
(another)> | newInitial    -|---|------------------| 
(another)> | initialHolder -|---|                  | 
(another)> | container     -|-> Container() -> "B" | 
(start)>   | last          -|-> "Z" <------------- | 
(main)>    | main          -|-> Main()               
```

First print: "A"

2nd print: "Z"

3rd print: "B"
</details>

## documentation

- https://www.baeldung.com/java-8-streams
- https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javap.html
