# Les soucis des systèmes distribués

Travailler avec des systèmes qui gèrent une charge de données amène une
quantité de problèmes supplémentaires.

La différence principale entre un logiciel qui tourne sur une machine ou sur
plusieurs machines est sa nécessité à devoir gérer correctement les erreurs
afin de rendre le système déterministe. _Debug un seul event qui se passe mal
dans une pipeline, revient à chercher une aiguille dans une botte de foin_.

Un logiciel distribué doit être capable de gérer les erreurs liés à son
environement (network, disk, parsing data, attacks, ou bien un pickup qui fonce
dans le data center). Un tel logiciel, ne peut pas simplement "redémarrer"
après un blue screen, cela pourraît prendre trop de temps, ou perdre voir même
corrompre de la donnée. 

Le système peut entrer dans un état avec des erreurs partielles. En effet, si
la donnée provient de deux sources différenetes, peut être que l'une est K.O.
mais pas l'autre. Il faut alors se poser la question de quoi faire. Un résultat
partiel peut-il être renvoyé ? Faut-il prévenir l'admin sys ou bien
l'utilisateur final ? Un résultat peut être delayé par le réseau, si une
application à des contraintes de temps, peut être renvoyer un résultat
incomplet est acceptable.

