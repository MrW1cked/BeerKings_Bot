FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY src src
COPY pom.xml ./
RUN mvn clean install

# PACKAGE STAGE
FROM eclipse-temurin:17-jdk
COPY --from=build /target/BeerKings-1.0.0-SNAPSHOT BeerKings-1.0.0-SNAPSHOT
CMD ["java", "-jar", "BeerKings-1.0.0-SNAPSHOT"]