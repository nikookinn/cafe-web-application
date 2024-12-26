
FROM openjdk:21-jdk-slim


WORKDIR /app


COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./


RUN ./mvnw dependency:go-offline


COPY src/ ./src/


RUN ./mvnw clean package -DskipTests

# Java uygulamasını çalıştırıyoruz
CMD ["java", "-jar", "target/piramida-web-app.jar"]
