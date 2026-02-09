# Architecture hexagonale

## DDD

Domain-Driven Design (DDD) est une approche de conception logicielle qui met l'accent sur la modélisation du domaine.

## Architecture

L'architecture hexagonale, aussi connue sous le nom de Ports and Adapters, est un style d'architecture logicielle qui
vise à créer des applications découplées et facilement testables. Elle a été introduite par Alistair Cockburn dans les
années 2000.

Elle vise à limiter les fuites de dépendances entres les différentes couches d'une application en isolant le domaine
métier des détails techniques comme les bases de données, les interfaces utilisateur, ou les services externes.

### Principes clés

Le code métier ne doit dépendre d'aucune technologie ou framework spécifique. Dans notre exemple de Minijournal, il faut
enlever Spring de TOUS le package `domain`. En faisant cela, on découple le code métier des détails techniques, il
devient plus facile de pouvoir enlever Spring si jamais ! Le code métier devient également mieux testable. En effet,
plus besoin d'avoir Spring pour tester le code métier.


### Critique et dans le monde réel


## Sources

- https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)
- https://dataintensive.net/
- https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164
- https://www.baeldung.com/hexagonal-architecture-ddd-spring

Pour aller plus loin !

- https://binout.github.io/#_%EF%B8%8F_jugsummercamp_september_2017
- https://binout.github.io/ten-tips-gs-ddd/#_pourquoi_ddd
