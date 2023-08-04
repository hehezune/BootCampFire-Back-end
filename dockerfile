FROM openjdk:17-alpine

ARG JAR_FILE=/build/libs/campfire-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /campfire.jar

ENTRYPOINT ["java","-jar","/campfire.jar"]