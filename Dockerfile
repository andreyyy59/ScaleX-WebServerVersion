# Usa la imagen oficial de OpenJDK 21
FROM openjdk:21-jdk

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

FROM eclipse-temurin:21-jdk

# Establece el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR
COPY target/*.jar app.jar

# Exponer el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]