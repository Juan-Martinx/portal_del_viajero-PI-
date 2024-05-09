FROM eclipse-temurin:17-jdk-alpine as build

ADD ./target/Pdv-backend-0.0.1-SNAPSHOT.jar /opt/pdv-backend/Pdv-backend-0.0.1-SNAPSHOT.jar
EXPOSE 9999

ENTRYPOINT [ "java", "-jar", "/opt/pdv-backend/Pdv-backend-0.0.1-SNAPSHOT.jar" ]