### Postgresql

Une image docker est fournis avec cette base de projet.

Executer `docker compose up` pour lancer celui-ci. Attention par défaut, il n'y a pas de volume, donc pas de persitance
de données. (Si le docker est détruit, la donnée également). J'ai utilisé
ce [projet](https://github.com/felipewom/docker-compose-postgres) pour mettre en place facilement ce genre d'outil.

Lancer `psql postgresql://username:password@localhost:5432/default_database` pour se connecter.

### Dialect postgresql

`org.hibernate.dialect.PostgreSQLDialect`

### Swagger UI
Il est disponible `http://localhost:8080/docs`.

### Grafana
Il est disponible `http://localhost:3000`.

Inspired by:
- Les cours de Max Devulder à l'ULCO
- https://github.com/bclozel/matchmaking
