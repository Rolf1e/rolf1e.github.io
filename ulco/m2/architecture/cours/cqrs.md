# Le pattern CQRS

## L'architeture Command Query Responsibility Segregation

Le **patron CQRS** (Command Query Responsibility Segregation) est un patron d'architecture qui sépare les
responsabilités
d'écriture et de lecture d'un service. Il est régulièrement utilisé dans les systèmes distribués pour améliorer
les performances et la scalabilité.

## Le patron Command Query Separation (CQS)

Il se base de le patron de conception qui dicte que les méthodes d'un objet doivent être:

- **Des commandes**: modification de l'état d'un objet, sans retourner de valeur.
- **Des requêtes**: retournent une valeur, sans modifier l'état de l'objet.

En gros, une méthode retourne une valeur que si elle **n'a de pas d'effets de bord** (side effect).

Il repose sur des patrons comme
la [chaîne de responsabilité](https://refactoring.guru/design-patterns/chain-of-responsibility) ou le
patron [stratégie](https://refactoring.guru/design-patterns/strategy).

## Avantages

Les chemins de lecture et d'écriture:

- Sont séparés et mis en évidance.
- Ils peuvent être optimisés indépendamment, avec des modèles de
  données différents, des bases de données différentes, ou même des langages de programmation différents.
- Ils peuvent être mis à l'échelle (scalability) de manière independante.
- Peuvenet être maintenus par deux équipes différentes.

## Inconvénients

- Les logiciels multithreadés sont plus difficiles à implémenter, dû à la gestion de la synchronisation entre les
  chemins
  de lecture et d'écriture.
- Un logiciel qui effectue des pauses dans son execution devient complexe à implémenter.
- Pour certain algorithme, des méthodes comme `pop()` ou `peek()` sont bien pratiques ...

## Plus loin

CQRS est souvent utilisé avec un patron appelé [**Event Sourcing**](./event_sourcing.md).

## Sources

- https://en.wikipedia.org/wiki/Command_Query_Responsibility_Segregation
- https://en.wikipedia.org/wiki/Command%E2%80%93query_separation
- https://microservices.io/patterns/data/cqrs.html
- https://medium.com/codeshake/le-pattern-cqrs-de-la-performance-sans-micro-optimisations-495d7a19fe8d

