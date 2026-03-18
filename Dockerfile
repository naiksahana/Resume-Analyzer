FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package

EXPOSE 8080

CMD ["java", "-jar", "target/*.jar"]