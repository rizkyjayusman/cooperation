# Step : Test and package
FROM maven:3.6.3-openjdk-8 as target
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn clean package -DskipTests
VOLUME /tmp
RUN mkdir -p /app/
RUN mkdir -p /app/logs/

# Step : Package image
FROM openjdk:8-jre-alpine
COPY --from=target /build/target/cooperation-1.0.0.jar /app/my-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker", "-jar", "/app/my-app.jar"]