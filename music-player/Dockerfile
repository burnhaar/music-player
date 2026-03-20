FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY pom.xml ./
RUN apk add --no-cache maven
COPY src ./src
RUN mvn clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/musicapp-0.0.1-SNAPSHOT.jar"]
