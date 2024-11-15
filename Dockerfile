# Usar a imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiar o arquivo JAR gerado pelo Maven/Gradle para o contêiner
COPY target/empresa-api.jar empresa-api.jar

# Comando para executar o Spring Boot no contêiner
ENTRYPOINT ["java", "-jar", "empresa-api.jar"]
