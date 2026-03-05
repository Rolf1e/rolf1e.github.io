# Event Streaming

Event Streaming (ou streaming processing) est un paradigme de traitement de données qui permet de **traiter des flux de
données en temps rée**l. Il est souvent utilisé pour traiter des données provenant de capteurs, de logs, ou de réseaux
sociaux.

On définit Event comme étant une unité de données qui représente un changement d'état ou une action qui a eu lieu dans
le système.

Technologies de streaming processing: Apache Kafka, Apache Flink, Apache Spark Streaming, NATS.

## Message brokers

Un message broker est un système de messagerie, c'est un pattern d'architecture. Il permet de [publier (publisher) et
consommer (consummer)](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern) des messages de manière
asynchrome. Il peut être utilisé comme une queue pour encaisser le traffic, stocker des événements, comme un bus pour
faire communiquer deux services entre eux.

Exemples de message brokers: RabbitMQ, Apache Kafka, NATS.

## Avantages

- Résistance aux changements de volumétrie (traffic load)
- Facilité de scale up et down
- Fault tolerance, on traite des messages de manière indépendante, si un message échoue, les autres peuvent être
  traités.

## Inconvénients

- Prix de l'infrastructure
- Le système d'information devient plus complexe à maintenir, il faut gérer les messages, les erreurs, les délais de
  traitement, etc.
- Ajout de la composante "temps" dans le système d'information.
- Le tooling pour debug ce genre de système est coûteux, complexe et pas toujours efficace.

## Sources

- Designing Data Intensive Applications (M. Kleppemann) Chapitre 11 Streaming Processing.
- https://en.wikipedia.org/wiki/Stream_processing
- https://www.ibm.com/think/topics/event-streaming
- https://en.wikipedia.org/wiki/Message_broker
- https://www.confluent.io/learn/event-streaming/

- https://codingchallenges.fyi/challenges/challenge-nats
