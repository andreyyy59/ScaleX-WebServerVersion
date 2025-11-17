FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copiar el JAR con el nombre exacto
COPY target/ScaleX-1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
