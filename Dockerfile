FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -Dmaven.test.skip=true
FROM eclipse-temurin:17-alpine
COPY --from=build /target/ECS3.war ECS3.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ECS3.war"]
