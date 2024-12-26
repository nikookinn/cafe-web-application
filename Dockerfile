FROM openjdk:21-jdk-slim

WORKDIR /app

COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./


RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline

COPY . .

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/piramida-web-app.jar"]

