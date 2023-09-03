FROM amazoncorretto:17-alpine-jdk
LABEL authors="Mohammed Kamil"
ARG JAR_FILE=./build/libs/*.jar

WORKDIR workspace
COPY ${JAR_FILE} catalog.jar

ENTRYPOINT ["java", "-jar", "catalog.jar"]