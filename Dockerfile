FROM openjdk:21
WORKDIR /app

# Copiar o JAR da aplicação para o contêiner
COPY target/enterprise-system-api-0.0.1-SNAPSHOT.jar /app/enterprise-system-api.jar

# Expor a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
CMD ["java", "-jar", "/app/enterprise-system-api.jar"]
