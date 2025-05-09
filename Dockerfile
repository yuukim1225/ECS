FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -Dmaven.test.skip=true
FROM eclipse-temurin:17-alpine
COPY --from=build /target/maven-test-0.1.0-shaded.war maven-test-0.1.0-shaded.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "maven-test-0.1.0-shaded.war"]
