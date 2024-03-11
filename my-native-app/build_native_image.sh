mvn -DskipTests=true -Pnative clean spring-boot:build-image > resBuild.txt 2>&1
#docker image rm my-native-app:0.0.1-SNAPSHOT