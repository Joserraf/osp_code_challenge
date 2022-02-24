FROM maven:3.6.3-openjdk-8-slim AS build
COPY src ./app/src
COPY pom.xml ./app
RUN mvn -f ./app/pom.xml clean package

FROM openjdk:8-alpine AS app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build ./app/target/codechallenge-0.0.1-SNAPSHOT.jar ./app/codechallenge-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./app/codechallenge-0.0.1-SNAPSHOT.jar"]