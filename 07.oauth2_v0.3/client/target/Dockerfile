# Start with a base image containing Java runtime
FROM openjdk:11

# Add Author info
LABEL maintainer="parkdod@me.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 9000

# The application's jar file
ARG JAR_FILE=client-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} client.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/client.jar"]