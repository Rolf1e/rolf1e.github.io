# Base de données orientée en ligne VS en colonne

Dans un système d'informations, la partie la plus importante du système est le
choix de la base de données. En effet, celle-ci contient l'essence même de ce
que vous vendez. Elle est clé car permet la sauvegarde et la mise à disposition
aux utilisateurs.

Le choix d'une base de donnée doit donc être adapté au métier et aux problèmes
auxquels répond application.

La table `video_games` suivante sera utilisée pour démontrer la manière de stocker la donnée
ainsi que les avantages et inconvénients:

|id|name|release date|multiplayer|genre|
|---|---|---|---|---|
|1|League of Legends|27-10-2009|true|moba|
|2|World of Warcraft|23-11-2004|true|mmorpg|
|3|Doom Eternal|20-03-2020|true|fps|


## Ligne orientée (Row oriented) - Postgres
Ici, la donnée sera stockée comme suit:

```csv
1,League of Legends,27-10-2009,true,moba
2,World of Warcraft,23-11-2004,true,mmorpg
3,Doom Eternal,20-03-2020,true,fps
``` 
Avec cet arrangement, il est plus facile de lire et écrire des lignes dans la
base de données. Concrêtement, il est plus facile de traiter les informations
relatives à *un* jeu. Il est souvent plus performant pour écrire de grosses
quantités de données, car les changements sont localisés à une ligne. Le
support pour la compression est présent mais peu performant, car dans une ligne
il a plusieurs types différents. Les changements de schéma ne sont pas une
difficulté.

## Colonne orientée (Column oriented) - ClickHouse
Maintenant, en reprennant l'exemple précédent:

```csv
1,2,3
League of Legends,World of Warcraft,Doom Eternal
27-10-2009,23-11-2004,20-03-2020
true,true,true
moba,mmorpg,fps
``` 
Ici, la lecture de gros chargements de données est facilité car les éléments
d'une colonne sont les uns à cotés des autres. Les opérations de GROUPBY et
aggrégations sont facilités. La compression est très optimale. La donnée étant
explosée sur plusieurs colonnes, les changements de schéma deviennent plus
complexe pour le moteur. L'insertion de gros chargements de données est plus
complexe car elle demande l'accès à plusieurs fichiers différents en écriture.
Cela dit, il existe aujourd'hui différents moyens pour réduire l'impact.
Notamment chez ClickHouse avec leur notion d'engine pour gérer les tables.

### Aggrégation: Data Cube et Materialized Views

https://en.wikipedia.org/wiki/Materialized_view

https://clickhouse.com/docs/en/materialized-view

p101


## Document Databases (NoSQL - MongoDB)

Dans un model document, la flexbilité du schéma est totale. En effet, les
objets sont souvent stockés et représentés par du JSON. Il est facile à lire,
flexible. Chaque document peut avoir ses champs indépendaments des autres, même
si cela peut engendrer un coût pour de grosses charges de données. Ce type de
base de données par sa nature, supporte mal les jointures et l'accès à des
champs très imbriqués.

L'exemple précédent serait stocké comme ceci.

```JSON
[
  {
    "id":1, 
    "name": "League of Legends",
    "release_date": "27-10-2009",
    "multiplayer": true,
    "genre": "moba"
  },
  {
    "id":2, 
    "name": "World of Warcraft",
    "release_date": "23-11-2004",
    "multiplayer": true,
    "genre": "mmorpg"
  },
  {
    "id":3, 
    "name": "Doom Eternal",
    "release_date": "20-03-2020",
    "multiplayer": true,
    "genre": "fps"
  }
]
```

## Conclusion

Par leur manière de stocker la donnée, une base en ligne et en colonne sont
fait pour des cas d'utilisation différents. Un système transactionnel, où les
faut travailler sur quelques lignes précises, un base en ligne sera plus adpaté
grâce à sa capacité à pouvoir mettre en place des transactions. S'il faut faire
des analyses sur un grand nombre de ligne et sortir une tendance, un graphe etc
une base de donnée en colonne est requise. Dans un dernier cas, où il faut
stocker des dures difficilements structurables, et dont il faut exploiter des
champs dans un scope précis, NoSQL to the rescue.

### Sources

Clickhouse docs: https://clickhouse.com/docs/knowledgebase/columnar-database
Wikipedia: https://en.wikipedia.org/wiki/Data_orientation 
Book: Design Data Intensive Applications - Chapter 3
