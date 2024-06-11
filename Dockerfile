# Use an official Ubuntu as the base image for building
FROM ubuntu:latest AS build

# Update and install OpenJDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Install Maven
RUN apt-get install -y maven

# Copy the entire project into the Docker image
COPY . .

# Run Maven to package the application
RUN mvn clean package -DskipTests

# Use a minimal OpenJDK image for running the application
FROM openjdk:17-jdk-slim

# Expose the port the application will run on
EXPOSE 8080

# Copy the packaged application from the build stage
COPY --from=build /target/PublisherSubscriber-1.jar app.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
