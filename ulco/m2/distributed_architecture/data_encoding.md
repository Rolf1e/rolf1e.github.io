# Encodage de la donnée 

Dans les systèmes d'informations, un élément important est l'encodage de la
donnée ou comment représenter l'information traitée par les différents
composants.

La donnée est représentée de deux manières:

- En mémoire dans les applications, la donnée est dans les objets, les
  structures, les tableaux, etc. Ici, elle peut être facilement manipulée pour
  la transformer.

- Persistante, quand elle doit être écrite dans un fichier ou envoyer sur le réseau. 

Dans le second cas, la donnée n'est vouée qu'a être transportée. Il devient
alors intéressant de changer sa structure pour rendre cette tâche plus facile.
Pour cela, il est plus facile de la représenter sous forme de séquence de
bytes. Des formats textuels JSON, XML, CSV. Ou des formats binaires comme
protobuf, avro, bson sont alors utilisés.

Deux opérations sont alors applicables à l'information. 

- Encodage (encoding, serialization ou marshalling)
- Decodage (decoding, deserialization, parsing ou unmarshalling)

Il existe pour cela des librairies qui sont disponibles dans tous les langages
pour faire ce genre de tâches. Par exemple en Java, il existe un package entier
(`io`)[https://docs.oracle.com/javase/8/docs/technotes/guides/serialization/index.html]
spécifique pour passer d'une représentation à l'autre.

Petite critique d'utiliser les packages internes à un langage, généralement,
ceux-ci lient le format à ce que le langage peut faire. Par exemple, Java
encodera un objet dans un format binaire que seul nativement une autre
application Java pourra lire. L'avantage, c'est qu'il est très simple et donc
efficient de pouvoir lire et écrire un objet Java, mais si un application en Go
doit lire derrière, cela risque de demander un peu plus de travail.

Un des problèmes quand un format est partagé entre deux applications est le
schéma. En effet, comment garder les deux applications au courant des mises à
jour du format de données. Des technologies comme
[protobuf](https://protobuf.dev/#what-are-protocol-buffers) ou bien
[avro](https://avro.apache.org/docs/), ils fonctionnent de la manière. Le
développeur exprime dans un langage propre au protocole puis en générant du
code permettant d'interagir avec la data. Il devient alors facile de serializer
et deserializer. 

Le cas du JSON est intéressant. La donnée en elle même est le schéma, il est
facile pour un humain de lire est comprendre la donnée qui est transférée. Cela
dit, il est complexe de compresser, encoder. 

// TODO Rajouter une partie sur l'évolution
