# Estágio 1: Construir o aplicativo com Maven
FROM maven:3.8.3-openjdk-17 AS builder

COPY ./backend/pom.xml /app/
COPY ./backend/src /app/src/

WORKDIR /app

RUN mvn clean package

# Estágio 2: Criar a imagem final com o aplicativo construído e o código-fonte
FROM openjdk:17-jdk-slim

COPY --from=builder /app/target/trabalho-0.0.1-SNAPSHOT.jar /app/app.jar
COPY /backend/src /app/src/

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
