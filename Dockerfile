FROM gradle:7.2.0 AS build
COPY . /gradle/
WORKDIR /gradle/
RUN gradle build --exclude-task test --no-daemon

FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build /gradle/build/libs/*.jar /app/runnableApp.jar
ADD /src/main/resources/application.properties /app/application.properties
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/runnableApp.jar"]