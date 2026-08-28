FROM eclipse-temurin:21-jdk-alpine
MAINTAINER algebra-it.hr
COPY target/library-0.0.1-SNAPSHOT.jar library.jar
EXPOSE 8888
ENTRYPOINT [ "java","-jar","/library.jar" ]