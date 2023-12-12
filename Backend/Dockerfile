# Use OpenJDK 8 as the base image
FROM openjdk:8-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/eventsProject-1.0.0-SNAPSHOT.jar /app/eventsProject.jar

# Expose the port on which the application will run
EXPOSE 8089

# Command to run the application when the container starts
CMD ["java", "-jar", "eventsProject.jar"]
