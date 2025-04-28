# 1. Build Stage - construim .jar-ul
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Runtime Stage - rulăm aplicația
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=build /app/target/chat-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]
