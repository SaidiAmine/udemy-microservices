#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim as build

#Information around who maintains the image
MAINTAINER SaidiAmine

# Add the application's jar to the container
COPY target/micro-service-one-0.0.1-SNAPSHOT.jar micro-service-one-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/micro-service-one-0.0.1-SNAPSHOT.jar"]