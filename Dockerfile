FROM amazoncorretto:17-alpine-jdk AS build
LABEL authors="Mohammed Kamil"
ARG JAR_FILE=./build/libs/*.jar
WORKDIR workspace
COPY ${JAR_FILE} catalog.jar
RUN java -Djarmode=layertools -jar catalog.jar extract

FROM amazoncorretto:17-alpine-jdk
RUN adduser -D spring
USER spring
WORKDIR workspace
COPY --from=build workspace/dependencies/ ./
COPY --from=build workspace/spring-boot-loader/ ./
COPY --from=build workspace/snapshot-dependencies/ ./
COPY --from=build workspace/application/ ./
ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]