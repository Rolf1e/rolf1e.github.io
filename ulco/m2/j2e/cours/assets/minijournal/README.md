### Postgresql

Une image docker est fournis avec cette base de projet. Executer `docker-compose up` pour lancer celui-ci. Attention par
défaut, il n'y a pas de volume, donc pas de persitance de données. (Si le docker est détruit, la donnée également).
J'ai utilisé ce [projet](https://github.com/felipewom/docker-compose-postgres) pour mettre en place facilement ce genre
d'outil.

### Dialect postgresql

`org.hibernate.dialect.PostgreSQLDialect`