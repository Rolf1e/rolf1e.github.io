# Projet: Architecture multi tier avec JEE

Le but du projet va être de partir d'un 3-Tier classique et de le faire grandir
pour en devenir un projet important !

Essayer de prendre un sujet qui vous intéresse, et qui permet des analyses intéressantes. Trouver une source de données
avec 1M de lignes au minimum. Le gouvernement français met à disposition des datasets
sur [data.gouv.fr](https://www.data.gouv.fr/fr/). L'INSEE expose aussi des
données dans leur [catalogue](https://catalogue-donnees.insee.fr/fr/catalogue/recherche). Le nettoyage de la donnée sera
surement nécessaire.

Vous avez à votre disposition un [docker-compose](../j2e/cours/assets/minijournal/docker-compose.yml).
Celui-ci initialize une base PostgreSQL. Il y a également un Grafana. Un Clickhouse est aussi disponible pour faire de
l'analytics.

Il va falloir penser à la volumétrie de données dès le début. Le chargement de la donnée dans les bases de données doit
être automatique au moment où le `docker-compose` est lancé. Vous pouvez utiliser des scripts bash, python, etc.

L'objectif est de réaliser une application qui va venir exposer notre donnée via une API. Ensuite, vous y connecterez un
front. Le front doit afficher votre application. Par exemple si vous avez choisi un dataset sur les films, vous
effectuez un catalogue de films. Et ensuite il faut un onglet où l'on peut visualiser des analyses sur les films (genre
le plus populaire par année, etc).

Il faudra réaliser un [schéma N-Tier](../j2e/cours/images/business_layer.png) de votre projet. Détaillez la partie
métier de votre Spring avec un diagramme de classes UML.

Le motoring de votre application doit être visible dans Grafana. Il sera réaliser avec opentelemetry. Il faut être
capable de voir les logs par composants, les traces des appels HTTP et les métriques de votre application (une custom
est nécessaire).
On réalisera un endpoint qui fail afin de voir les retries dans les traces.

## Rendu

- [ ] schéma n tier
- DATA LAYER
    - [ ] dataset volumineux (1M+ lignes)
    - [ ] script d'import automatique dans la base de données (avec nettoyage si nécessaire)
- BUSINESS LAYER
    - [ ] une API en Java Spring Boot qui requête le tier de donnée.
    - [ ] une gestion d'utilisateur (basic) avec Spring Sécurité.
    - [ ] au moins un endpoint pour votre besoin utilisateur (ex: liste des films, recherche par auteur, etc).
    - [ ] un endpoint qui fail aléatoirement pour voir les retries dans les traces (peut être l'endpoint de votre API
      principale).
    - [ ] un endpoint d'analyses qui requête la base de données pour faire des analyses (trend, histogram, etc).
- FRONT LAYER
    - [ ] un front minimaliste pour visualiser la donnée via l'API
    - [ ] un chart d'analyses (trend, histogram, ...)
- MONITORING
  - [ ] opentelemetry intégré dans l'application
    - [ ] logs par composants
    - [ ] traces des appels HTTP
    - [ ] métriques custom (ex: nombre d'utilisateurs, nombre de requêtes par endpoint, etc).
- docker compose
    - [ ] base de données Postgres SQL
    - [ ] base de données Clickhouse pour l'analytics
    - [ ] Grafana + OTEL collector 
