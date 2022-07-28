FROM openjdk:11
MAINTAINER breno
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]