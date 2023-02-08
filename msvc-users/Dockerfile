FROM openjdk:11-jdk-slim as builder
WORKDIR /app/msvc-users

COPY ./pom.xml /app 
COPY ./msvc-users/.mvn ./.mvn
COPY ./msvc-users/mvnw .
COPY ./msvc-users/pom.xml .

RUN ./mvnw clean package -Dmanven.test.skip -Dmanven.main.skip -Dspring-boot.repackage.skip && rm -R ./target/

COPY ./msvc-users/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:11-jdk-slim

WORKDIR /app

COPY --from=builder /app/msvc-users/target/msvc-users-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

CMD [ "java", "-jar", "msvc-users-0.0.1-SNAPSHOT.jar" ]