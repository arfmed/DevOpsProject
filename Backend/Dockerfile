FROM openjdk:11-jre-slim
WORKDIR /app

COPY target/eventsProject-1.0.0-SNAPSHOT.jar /app/eventsProject.jar
EXPOSE 8082
# Command to run the application when the container starts
CMD ["java", "-jar", "eventsProject.jar"]


