dans ce package , d'éventuels services purement métiers
 (sans dépendances externes : pas de persistance , aucun SPI)
 
Autrement dit , le noyau hyper-central "core.domain" ne dépend que de lui même
C'est le coeur de l'architecture en oignon (petite variante de l'architecture haxagonale) 