FROM openjdk:17-alpine
COPY target/ApiRestCalculadora-0.0.1-SNAPSHOT.jar apirestcalculadora.jar
ENTRYPOINT ["java","-jar","/apirestcalculadora.jar"]