# Usa la imagen oficial de OpenJDK 21
FROM openjdk:21-jdk-slim

# Directorio de trabajo en el contenedor
WORKDIR /app

# Puerto que expone la aplicación (Render usa $PORT)
EXPOSE 8080

# Variable de entorno para el JVM (opcional, pero recomendado)
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Copiar el JAR de la aplicación
COPY target/ScaleX-1.jar app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dserver.port=$PORT -jar /app/app.jar"]