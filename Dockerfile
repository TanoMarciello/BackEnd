FROM amazoncorretto:19-alpine-jdk
MAINTAINER agustin
EXPOSE 3306
COPY target/afunes-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]