FROM openjdk:17-jdk-alpine
RUN mkdir /app
COPY target/assignment-0.0.1-SNAPSHOT.jar  /app/assignment-0.0.1-SNAPSHOT.jar
COPY runscript.sh /app
WORKDIR /app
ENTRYPOINT /bin/sh runscript.sh