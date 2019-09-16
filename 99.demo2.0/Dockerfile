FROM openjdk:11.0.2-jdk-stretch
VOLUME /tmp
ADD ./demo-0.0.2-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Dserver.port=9090","-Djava.security.egd=file:/dev/./daniel","-jar","/app.jar"]