# Start with a base image that has Java installed
FROM openjdk:17-jdk-slim

# Information about who maintains the image
LABEL authors="glukasze"

# Add the application's JAR file to the Docker image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Execute the JAR file when the container starts and start the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]