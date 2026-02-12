### Micro-service

### Architecture monolithique

L'architecture monolithique est un style d'architecture logicielle dans lequel une application est développée et
déployée en tant qu'unité. Tous les composants de l'application sont regroupés dans un seul code source et sont
exécutés dans un même processus. Cette approche est souvent utilisée pour les applications simples ou de petite taille.

#### Avantages

- Les développeurs peuvent facilement comprendre et travailler sur l'ensemble de l'application.
- Facilité de test.
- Facilité de déploiement (un seul artefact à déployer).
- Moins de complexité dans la communication entre les composants (pas de réseau, pas de sérialisation/désérialisation,
  sécurité).
- Moins de complexité dans la gestion des bases de données (pas de base de données, transactions distribuées).

#### Inconvénients

- Difficulté à faire évoluer l'application (ajout de nouvelles fonctionnalités, changement de technologie).
- Difficulté à faire évoluer l'infrastructure (mise à l'échelle).
- La résilience est limitée (une panne peut affecter l'ensemble de l'application).

### Exemples

- Stack Overflow
-

### Architecture micro-service

L'architecture micro-service est un style d'architecture logicielle qui consiste à diviser une application en plusieurs
services indépendants, chacun étant responsable d'une fonctionnalité spécifique. Chaque service peut être développé,
déployé et mis à l'échelle de manière autonome, on parle de modularité.

#### Avantages

- flexibilité dans le développement et le déploiement des services.
- résilience (une panne n'affecte qu'un service).
- évolutivité (mise à l'échelle de chaque service indépendamment).
- Technologie hétérogène, chaque service peut avoir sa stack la plus adaptée à sa fonctionnalité.
- Compréhension, chaque service est scopé et plus facile à comprendre.

#### Inconvénients

- Comprendre le schéma d'architecture globale d'un système d'information devient plus complexe, et de ce fait tester la
  chaîne entière prend du temps et de la resource (souvent la QA apparaît !).
- Chaque nouveau micro-service rajoute une couche, donc de la latence.
- Dans le cas de transactions distribuées, la gestion de la cohérence se conplexifie.
- La délimitation d'un "Service" n'a aucune définition universelle

## Sources

Monolithique:

- https://en.wikipedia.org/wiki/Monolithic_application
- https://en.wikipedia.org/wiki/Microservices

Microservices:

- https://aws.amazon.com/microservices/

Modular monoliths:

- https://medium.com/@husain.ammar/modular-monoliths-explained-structure-strategy-and-scalability-ba94b103b345
- Allons plus loin avec le [Hive pattern](./hive_pattern.md)
