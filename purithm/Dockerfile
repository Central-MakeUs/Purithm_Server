FROM openjdk:17
VOLUME /tmp
COPY /build/libs/*SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]