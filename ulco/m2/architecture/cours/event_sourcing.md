# Event Sourcing

## Problème

Dans le cas d'un service de livraison de colis, qui doit faire du CRUD pour enregister la commande et la transaction en
base de données pour la compatabilité, mais aussi vers un message broker pour la gestion de l'envoie physique.
Il est dans ce cas, difficile de garder la cohérence entre les deux (dans la même transaction, on a aucune garantie d'un
message envoyé au milieu, que se passe-t'il si la transaction échoue). Des erreurs de réseau, parsing, stockage peuvent
arriver.

Comment faire une mise à jour atomique et envoyer des messages à un message broker de manière fiable ?

[2PC]( https://en.wikipedia.org/wiki/Two-phase_commit_protocol) n'est pas envisageable, car lié la base de données et le
message broker n'est pas une option désirable.

## Solution - Event Sourcing

La solution est de stocker les événements qui ont eu lieu dans le service plutôt que de stocker l'état actuel. À chaque
fois qu'une entitée métier change, un nouvel événement est ajouté à la liste d'événements.
On reconstruit l'état actuel de l'entitée métier en rejouant tous les événements. Les événements sont immuables et
stockés dans un event store, une base de données qui se comporte comme un message broker.

## Avantages

- Publier des événements de manière fiable, même en cas de panne du service.
- Un journal des événements ([audit log](https://microservices.io/patterns/observability/audit-logging)) complet
  existe (facilite l'observabilité et le debugging).

## Inconvénients

- Courbe d'apprentissage
- La reconstruction de l'état peut demander du temps, il devient nécessaire d'avoir [CQRS](./cqrs.md) pour facilter les
  lectures.

## Quand l'utiliser

Quand un service effectuer un CRUD et envoyer des messages / événements à un message broker.

## Events Streaming

A ne pas totalement confondre avec l'[event streaming](./event_streaming.md). Event Sourcing utilise les mêmes
principes, mais pas au même niveau d'abstraction.

Event sourcing admet difficilement les mutations, préférant ajouter un événement modificateur ou correctif.

## Sources

- https://microservices.io/patterns/data/event-sourcing.html
- https://en.wikipedia.org/wiki/Event-driven_architecture
- différence avec event streaming: Designing Data Intensive Applications (M. Kleppemann) Chapitre 11 Streaming Processing - Event
  Sourcing - page 457.
