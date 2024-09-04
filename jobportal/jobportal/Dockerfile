FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://jobportaldb.c1ymyeg8iphg.eu-central-1.rds.amazonaws.com:5432/jobportaldb
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=dupadupa123

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
