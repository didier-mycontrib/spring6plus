Une partie de cette application repose sur kafka (localhost:9092)
Le service kafka peut éventuellement être mis en oeuvre via
une machine virtuelle (vagrant/virtualBox/linux)
comportant elle même "kafka" intégré dans un conteneur docker.
-------------
Le sous projet "va-sta-dev-serv" du référentiel 
https://github.com/didier-mycontrib/msa-vagrant
comporte une configuration "docker" operationnelle pour kafka (localhost:9092)

===============
Un message envoyé dans un topic_xy de kafka sera :
  - soit reçus par plusieurs consumers s'ils sont de groupes différents
  - soit reçus par un seul des consumers d'un même groupe 
  
Avec kafka , la notion de clef (facultative) sert juste à garantir un ordre de réception cohérent si plusieurs partitions ...  