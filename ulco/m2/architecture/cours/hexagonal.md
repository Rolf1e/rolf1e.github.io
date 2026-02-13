# Architecture hexagonale

## Architecture

L'architecture hexagonale, aussi connue sous le nom de Ports and Adapters, est un style d'architecture logicielle qui
vise à créer des applications découplées et facilement testables. Elle a été introduite par Alistair Cockburn dans les
années 2000.

Elle vise à limiter les fuites de dépendances entres les différentes couches d'une application en isolant le domaine
métier des détails techniques comme les bases de données, les interfaces utilisateur, ou les services externes.

### Principes clés

Le code métier ne doit dépendre d'aucune technologie, framework spécifique dans son implémentation. S'il a besoin de
communiquer avec l'extérieur, il doit le faire derrière un contrat d'interface qu'il définit. Dans notre exemple de
Minijournal, il faut enlever Spring de TOUS le package `domain`. En faisant cela, on découple le code métier des détails
techniques, il devient plus facile de pouvoir enlever Spring si jamais ! Le code métier devient également mieux
testable. En effet, plus besoin d'avoir Spring pour tester le code métier.

Les `Ports` représentent les points d'entrée pour que le monde communique avec notre application. Par exemple les
endpoints HTTP, ou bien GRPC, protobuf.

Les `Adapters` sont les points dédiés pour la partie métier pour accéder au monde extérieur. Par exemple les requêtes
SQL, ou bien HTTP.

`Ports` et `Adapters` n'ont pas le droit de se connaître ou de dépendre entre eux. C'est à l'application de faire le
lien si besoin.

### Implementation dans Minijournal

Dans le module [minijournal-api-ddd](../../j2e/cours/assets/minijournal),
on peut étudier la différence entre une architecture [ddd](../../j2e/cours/assets/minijournal/minijournal-api-ddd)
et [non-ddd](../../j2e/cours/assets/minijournal/minijournal-api).

L'isolation du code métier du code d'infrastructure a été réalisé à l'aide d'`Adapter`. En utilisant ce pattern, il est
possible de ne pas contaminer le code métier avec Spring ici, ou bien n'importe quelle autre librairie ou framework.

![Minijournal-ddd](./images/minijournal-hexagonal.png)

### Avantages

- La logique métier est complètement isolée. Il est facile de le comprendre. Tous appels à une librairie extérieure ou
  framework est cachée derrière une
  abstraction.
- Les différentes couches (domain, infrastructure, application) sont clairement définies. Les contrats d'interfaces
  définissent le "data flow" (Qui ? Quoi? Pourquoi?) et les implémentations (Comment?) la partie infrastructure.
- Les tests de la partie métier sont beaucoup plus simples. En effet, il n'est pas nécessaire de configurer (spring
  ici), l'utilisation d'implémentation est naturelle (limitant les mocks complexes). Cela permet de pouvoir plus
  facilement les lancer, les maintenir et les réutiliser en cas de migration. Démo client, développement local
  facilité !
- Échanger les parties infra devient très facile, changer de SQL vers HTTP revient à implémenter un nouvel `Adapter`
  pour le besoin. Utile pour des changements externes (e.g: de technologies, de version d'API).
- Les migrations incrémentales sont possibles en ajoutant ou enlevant des `Ports` / `Adapters`.
- Dans la théorie (pas de changements de langage), l'architecture hexagonale permet d'éviter des récritures complètes
  des applications.

### Inconvénients, critique

- Verbeux, ajout de complexité pour les néophytes.
- Le principe d'ajouter des patterns `Mapper`s (conversion DTO -> BO, BO -> DO, etc) introduit beaucoup de
  boilerplate entraînant du temps de développement voire de complexité.
- Si l'architecture est appliquée à moitié, il y a peu de bénéfice !
- Certaines librarie très flexibles OpenTelemetry, Spring, Lombok (souvent basées autour des annontations) deviennent
  plus difficile à utiliser ! Nous restons des ingénieurs et il faut choisir quand sur qui comment implémenter les
  outils à notre disposition !
- La scalabilité horizontale est quasiment impossible
- L'utilisation des bases de données se retrouve réduite, ce qui peut amener des problèmes de performance

### Arborescence possible en Java

```
└── fr
  └── ulco
    └── minijournal
      └── minijournalapiddd
        ├── configurations
        ├── controllers
        │ └── dto
        │   ├── in
        │   └── out
        ├── domain
        │ ├── adapters
        │ ├── exceptions
        │ ├── mappers
        │ ├── models
        │ │ └── bo
        │ │  ├── in
        │ │  └── out
        │ └── services
        │   ├── articles
        │   ├── authors
        │   └── failure
        └── infra
          ├── http
          │ ├── dao
          │ │ └── spring
          │ └── model
          └── sql
            ├── dao
            │ └── spring
            └── entities
              └── articles
```

### Dans le monde réel

[Shopify](https://shopify.engineering/shopify-monolith#),
[PayPal](https://developer.paypal.com/community/blog/the-next-generation-of-data-platforms-is-the-data-mesh/),
[Netflix](https://netflixtechblog.com/ready-for-changes-with-hexagonal-architecture-b315ec967749),
utilisent des arhitectures hexagonales.

La question est maintenant, quand utiliser cette architecture. Eh bien, il faut y penser quand votre application vient à
grandir, et qu'il vient le besoin de supporter par exemple beaucoup de format d'entrée différents. Que votre application
puisse être robuste aux changements externes. Si comme Netflix, vous venez à devoir supporter des formats de sortie
différents de manière uniforme.

- https://www.geeksforgeeks.org/system-design/hexagonal-architecture-system-design/
- https://medium.com/@himanshusingour7/how-shopify-handles-30tb-of-data-every-minute-with-a-monolithic-architecture-cad54df86955

## Sources

- https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)
- https://dataintensive.net/
- https://alistair.cockburn.us/hexagonal-architecture
- https://www.amazon.com/Clean-Architecture-Craftsmans-Software-Structure/dp/0134494164
- https://www.baeldung.com/hexagonal-architecture-ddd-spring
- https://ivan.canet.dev/blog/2024/08/01/hexagonal-architecture.html#hexagonal-architecture
- https://wiki.c2.com/?HexagonalArchitecture
- https://www.youtube.com/watch?v=YPmKHm7G19Q&list=PLuMbC-fWSyk3zf7o0idX1pXD4HSuTqCoY
- https://atalupadhyay.wordpress.com/2025/05/29/hexagonal-architecture-what-you-need-to-know-a-simple-explanation/

### Pour aller plus loin ! Vers DDD

Domain-Driven Design (DDD) est une approche de conception logicielle qui met l'accent sur la modélisation du domaine.
On met des hexagones partout quoi !

- https://binout.github.io/#_%EF%B8%8F_jugsummercamp_september_2017
- https://binout.github.io/ten-tips-gs-ddd/#_pourquoi_ddd
- Conf JUG Julien Topçu https://www.youtube.com/watch?v=K2FIhIhKufs 


