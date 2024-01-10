Avec Spring5/SpringBoot2:
   Fichier META-INF/spring.factories
   comportant 
      org.springframework.boot.autoconfigure.EnableAutoConfiguration=org.mycontrib.mysecurity.chain.autoconfigure.MySecurityAutoConfiguration
   
   =======================
   
Avec Spring6/SpringBoot3:
   Fichier META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
   comportant
      org.mycontrib.mysecurity.chain.autoconfigure.MySecurityAutoConfiguration
 