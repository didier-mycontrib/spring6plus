dans ce package , un modèle agnostic purement métier
 (aucune annotations de JPA ou autre)
 
En plus d'entités du domaine (à états) avec éventuelles annotations lombok
on peut envisager des "values objects" immuables 
éventuellement codés via des "record" de java >=17

 
Le noyau hyper-central "core.domain" ne dépend que de lui même
C'est le coeur de l'architecture en oignon (petite variante de l'architecture haxagonale) 