# Des applications qui gèrent beaucoup de données ?

De nos jours, l'information (la DATA) est reine. Elle est aujourd'hui le moyen
le plus efficace de se faire de l'argent à grande échelle. Il y a vingt ans
c'était l'indexation du Web par Google, aujourd'hui c'est ChatGPT et les LLM
qui se goiffrent et recrachent des masses de données inimaginables. En passant
par Amazon, Météo France ou bien les grandes banques. Il devient de plus en
plus complexe de gérer, transformer, interprêter cette masse d'informations.
Dans ce cours, j'essayerai de vous sensibiliser, vous faire découvrir et vous
donner des outils afin d'être prêt à pouvoir comprendre et participer à
l'informatique d'aujourd'hui. 

Dans le but de créer et maintenir des systèmes d'informations complexes, voici
trois points importants:

- Fiabilité (Reliability)
- Mise à l'échelle (Scalability)
- Maintenabilité (Maintenability)

Ils sont clés dans la capacité d'une application à pouvoir traiter et calculer
de grosses quantités de données, car ils permettent d'évaluer l'état du
système.


## Fiabilité (Reliability)

Dans ce premier point, il faut que le système puisse être tolérant aux erreurs.
Aussi bien matérielles (hardware), logicielles (software) mais aussi et surtout
aux erreurs humaines (human errors). 

Le système doit effectuer les requêtes utilisateurs de manière attendue. Il
faut qu'il soit capable de tolérer une incompréhension ou de mal utiliser le
logiciel. L'application gère les accès illégaux et les abus (bots, cyber
attaques).

Exemple d'échecs possibles:
- hardware: vie d'un disque, machines virtuelles
- software: failles logicielles, hanging process, dépendences
- humans:  documentation d'api, avoir des sandbox, mises en place de tests,
quick recovery (cicd), monitoring, onboarding

## Mise à l'échelle (Scalability)

Une fois que le système répond correctement, et est capable de gérer les cases
d'erreurs. Il peut arriver qu'il voit son traffic passer de 10 000  à 100 000
utilisateurs concurrents, voir son "load" de données augmenter
significativement. Ici, il n'y a pas de recette miracle, la solution vient de
la connaissance engrengée au cours de la vie de l'application. 

Quelques points pour éclairer les choix: 

Étudier son traffic, en essayant par période. Par exemple, que se passe-t'il si
la charge double ? Que se passe-t'il si les utilisateurs demandent deux fois
plus de données ? S'il y a deux fois plus d'utilisateurs en même temps ? Il
faut définir des paramètres de charges (load parameters), sur lesquels, il
faudra jouer pour améliorer l'application. (requests per seconds, ratio of
reads writes in database, simultaneously actives users, cache hits, ... )

A partir de ces données, il faut étudier la performance. Est-ce que
l'application est ralentie par le réseau ? Le cpu ? Les disques ? L'écriture
dans la base de données ? Le but est de garder la performance de l'application
en changeant les paramètres de charge.

Par exemple, pour du batch processing, mesurer le "Throughput" est un bon indicateur.
Throughput: output ou production d'un logiciel sur une période de temps.

Pour les systèmes en ligne, mesurer le temps de réponse est un indicateur commun.

Maintenant que mesurer les changements de charge est fait, il devient possible
de l'adapter. Il existe deux possibilités:

- Scaling up
- Scaling out

Le premier changer pour une machine plus performante. Le deuxième, diviser le
travail sur plusieurs machines.

Aujourd'hui, grâce à des technologies comme Kubernetes, il est possible de
rendre les systèmes d'informations élastiques aux changements de charge.

Il faut noter, qu'il est souvent plus facile de maintenir un système immuable
(stateless) et non distribué. Par exemple, pour une base de données, d'abord
améliorer les performances d'une machine avant de décider de la répartir sur
plusieurs noeuds. Là où une API Rest peut facilement être distribuées sur
plusieurs machines (surtout via un load balancer).

Une chose importante à savoir sur les architectures qui opérent à grande échelle
est qu'elles deviennent spécifiques. En effet, générifier à grande échelle est
virtuellement impossible, principalement à cause du dilemme de lecture et d'écriture
[(reads and writes)](https://www.johnnunemaker.com/database-performance-simplified/).

Pourquoi un tel dilemme ? Simplement par la nature de comment les rendre plus
performants. Pour écrire de manière performante, il faut éviter les mise à jour
(updates), ne pas supprimer (deletes), ne pas avoir d'index. Il est généralement
préférable d'insérer en batch pour éviter de surchager l'IO et le réseau. Lire
rapidement est plus difficile, il faut se souvenir d'où sont rangées nos
lignes. Cela passe principalement par des index. Pour des charges plus
complexes, des clés de répartitions adaptées à la donnée devient important.
Pour beaucoup de base de données, et éviter de scanner de grandes parties de la
base, la donnée est physiquement écrite sur des fichiers différents.

Par exemple ClickHouse [range ses colonnes et ses lignes dans différents
fichiers](https://en.wikipedia.org/wiki/ClickHouse#Features)
[MergeTree Engine chez ClickHouse](https://clickhouse.com/docs/en/engines/table-engines/mergetree-family/mergetree#mergetree-data-storage)


## Maintenabilité (Maintenability)

Dans une application sur le long terme, la maintenance de celui-ci est le plus
important, car il assure de garder vos clients au grès des mises à jour. Cela
dit, c'est très souvent la partie la moins fascinante à faire. Qui aime
résoudre les bugs des autres ?! 

Dans cet optique, voici trois points importants pour assurer une maintenance plaisante:

- Opérabilité
- Simplicité
- Évolution

### Opérabilité

Il doit être facile pour les équipes infras de continuer de faire tourner
l'application. Le monitoring, afin de pouvoir surveiller et restorer
l'application si besoin. Les logs doivent être claire pour comprendre le
problème. Les patchs de sécurité et les machines doivent être à jour. Une CICD
opérationelle, pour deploy et rollback efficacement. Documenter l'application,
surtout le dataflow et les interactions entre systèmes.

### Simplicité

L'onboarding doit être facile, en essayant d'avoir un flux de données logique.
Trouver une abstraction cohérente et compréhensible au dessus de l'application.
Il est souvent plus facilement de comprendre une fonctionalité si elle est
facile d'utilisation. Même si son implémentation est complexe. Par exemple SQL,
est une abstraction simple sur tout un système de fichiers et de gestion de
mémoire.

### Évolution

Les développeurs qui vont venir dans le futur doivent pouvoir facilement faire
des changements. On peut penser aux patrons de conceptions. Des pratiques comme
le TDD, BDD et autres venant de l'Agile peuvent aider à passer le savoir et
assurer une évolution plus tranquille.

## Sources
- [Designing Data-Intensive Applications](https://dataintensive.net/)
