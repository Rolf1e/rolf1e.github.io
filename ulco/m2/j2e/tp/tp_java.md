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

    List<Integer> withListApi() {
        return Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
    }

    List<Integer> withAdvanceStreamApi() {
        return Stream.iterate(0, n -> n + 1)
            .limit(16)
            .toList();
    }
}

```

</details> 

## documentation

 - https://www.baeldung.com/java-8-streams
