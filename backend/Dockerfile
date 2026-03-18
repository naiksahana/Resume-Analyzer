FROM eclipse-temurin:17-jdk

WORKDIR /app

# 👇 copy ONLY backend folder properly
COPY backend/ .

RUN chmod +x ./mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/*.jar"]