Le but du projet va être de partir d'un 3-Tier classique et de le faire grandir
pour en devenir un projet important !


Vous avez à votre disposition un
[docker-compose](./tp/tp_distributed/docker-compose.yml) initialisant une base
postgres sql (base de données principale) et un clickhouse qui vient de se
brancher dessus.

Dans un premier temps, vous allez choisir un sujet, prennez quelques choses sur
lequel des analyses sont possibles. Je vous donne en exemple un dump de [hacker
news](https://news.ycombinator.com/).

Le but va être de réaliser un 3-Tier. D'abord une api qui va venir lire notre
base de données. Ensuite vous y connecterez un front (here nothing fancy, un
fichier html avec un peu d'ajax est suffisant. Vous pouvez aussi vous amuser
avec des frameworks si cela vous dit !).

Vous réaliserez un [schéma N-Tier](https://github.com/Rolf1e/rolf1e.github.io/blob/main/ulco/m2/j2e/cours/images/n_tier.png)
comme celui-ci de votre projet. Détaillez la partie métier comme [ici](https://github.com/Rolf1e/rolf1e.github.io/blob/main/ulco/m2/j2e/cours/images/business_layer.png)

Votre projet doit contenir une authentification, en basic c'est largement
suffisant. Il doit y avoir les bases postgres et clickhouse qui tournent. 
Le but va être de réaliser un affichage simple de la donnée. Par exemple, la
liste des articles hacker-news et un moyen de les cherchers / filtrers par
auteurs. Dans un deuxième temps, on fera des graphes sur cette même donnée à
l'aide de clickhouse !


- [ ] schéma n tier
- [ ] n tier (minimum 3Tier)
  - [ ] Vos bases de données
    - [ ] postgres
      - [ ] une gestion d'utilisateur (basic)
    - [ ] clickhouse
      - [ ] un chart d'analyses (trend, histogram, ...)
  - [ ] Une API en Java Spring ou en Golang, qui requête le tier de donnée et sert le front
  - [ ] Un front minimaliste pour visualiser la donnée via l'API

