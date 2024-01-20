FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/patient-hub-application.jar /app/patient-hub-application.jar
EXPOSE 8080
CMD ["java", "-jar", "patient-hub-application.jar"]

