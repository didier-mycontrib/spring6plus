mvn -DskipTests=true -Pnative clean spring-boot:build-image > resBuild.txt 2>&1
#docker image rm my-native-app:0.0.1-SNAPSHOT
#bug connu (pas encore resolu) avec spring_boot3.2 +hibernate : pas de native image contructible (pb de compatibilité)