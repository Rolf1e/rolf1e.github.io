# Projet: Architecture multi tier avec JEE

À la fin du TP, vous serez, si mon job est un succès, capable d'implémenter un 3-Tier à l'aide de Java EE.
Nous utiliserons Spring comme framework web. Au choix postgresql, SQLite ou bien mysql comme base de données.

Seul ou par groupe de deux.

### Rendu

Le minimum: (12 points)

- Le schéma de votre infrastructure n-tier
- Une base de données avec au moins deux tables (On ne compte pas les tables de jointures). Avec une ou des relations.
- Le modèle de la base lié grâce à Hibernate et Spring Data.
- Le projet devra compiler pour java 11+ à l'aide de Maven.
- Une authentification en Basic avec Spring Sécurité.
- Au moins deux endpoints correspondant à des besoins utilisateurs.
- Une API utilisant JSON et Spring REST.
- La partie front qui appelle l'API.

Ce qui peut être ajouté: (8 points)

- Une suite de tests Spring.
- Une web socket.
- Mise en place d'un cache guava.
- Une CI avec Jenkins ou Git Action qui compile le code et lance les tests.

BONUS:

- La CD est déployée et hostée.

### Quelques rappels

- Effectuer un schéma de votre organisation !
- Diviser correctement les différentes couches de votre projet.
- Pensez à utiliser l'API des `Collection`s et des `Stream`s de Java pour rendre vos transformations facilement
  lisibles !
- Utiliser une [convention de rangement](../cours/spring/spring.md#mettre-un-peu-dordre-dans-tout-ça) dans votre projet Spring.

### Idée de sujets

- Celui du cours, un journal.
- Un shop (amazon, dealabs).
- Un réseau social.
- Un blog.
