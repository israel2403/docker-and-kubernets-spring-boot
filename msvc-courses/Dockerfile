FROM openjdk:11-jdk-slim as builder
WORKDIR /app/msvc-courses
COPY ./parent.xml /app/pom.xml
COPY ./.mvn ./.mvn
COPY ./mvnw ./mvnw
COPY ./pom.xml ./pom.xml
RUN  ./mvnw clean package -Dmanven.test.skip -Dmanven.main.skip -Dspring-boot.repackage.skip && rm -R ./target/
COPY ./src ./src
RUN  ./mvnw clean package -DskipTests
FROM openjdk:11-jdk-slim
WORKDIR /app

COPY --from=builder /app/msvc-courses/target/msvc-courses-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

CMD [ "java", "-jar", "msvc-courses-0.0.1-SNAPSHOT.jar" ]