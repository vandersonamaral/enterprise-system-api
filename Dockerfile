FROM openjdk:21


WORKDIR /app


COPY target/enterprise-system-api-0.0.1-SNAPSHOT.jar /app/


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "enterprise-system-api-0.0.1-SNAPSHOT.jar"]