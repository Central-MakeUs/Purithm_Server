# Build Stage
FROM openjdk:17 AS build
WORKDIR /app
COPY /build/libs/*SNAPSHOT.jar app.jar

# Run Stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/app.jar app.jar
VOLUME /tmp
ENTRYPOINT ["java","-jar","/app.jar"]