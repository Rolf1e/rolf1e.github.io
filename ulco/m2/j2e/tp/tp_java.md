# TP Java  

Le but de ce tp de Java est de se familiariser avec l'API de Java par l'exemple.

Vous allez pour cela écrire plusieurs snippets de code répondant à des cas d'utilisations. Vous pouvez utiliser JShell
ou bien une simple classe Java avec une méthode `main`.


## Cas d'utilisations:

### Avec l'api des streams java

<details> 

<summary> 1. Construire la liste des nombres entre 0 et 15 </summary>

### Réponse

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

<details> 

<summary> 2. A partir de la liste précédente, multiplier tous ses éléments par deux puis diviser les par trois </summary>

### Réponse

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

<details> 

<summary> 3. Vous avez en entrée une liste de personnes, nous cherchons à filtrer les adultes avec un chapeau. </summary>

### Réponse

```java

class PlayGround {

    static record Person(
        String name,
        Integer age,
        Boolean hat
    ) {}

    static final int LEGAL_ADULT_AGE = 18;

    static Collection<String> filterAdultsWithHat(Collection<Person> persons, int legalAdultAge) {
        return persons
        .stream()
        .filter(person -> person.age >= legalAdultAge)
        .filter(person -> person.hat)
        .map(Person::name)
        .toList();

    }

    // PlayGround.filterAdultsWithHat(
    //     Arrays.asList(
    //     new PlayGround.Person("Tigran", 24, true),
    //     new PlayGround.Person("Antoine", 20, false),
    //     new PlayGround.Person("Chloé", 11, true)
    //     ),
    // 18)

}

```

</details> 

<details> 

<summary> 4. Groupez ces livres par noms d'auteur.</summary>

### Réponse

```java

class PlayGround {
    static record Book(String name, String author) {}

    static groupByAuthor(Collection<Book> books) {
        return books.stream()
        .collect(Collectors.groupingBy(Book::author));
    }

    Collection<Book> books = List.of(
        new Book("Livre 1", "Author 1"),
        new Book("Livre 2", "Author 1"),
        new Book("Livre 3", "Author 2"),
        new Book("Livre 4", "Author 1"),
        new Book("Livre 5", "Author 3")
    );

    // Map<String, Collection<Book>> groupedByAuthors = groupByAuthor(books);

}

```

</details> 


## documentation

 - https://www.baeldung.com/java-8-streams
