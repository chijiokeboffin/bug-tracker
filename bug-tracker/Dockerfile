FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bug-tracker.jar
ENTRYPOINT ["java", "-jar", "bug-tracker.jar"]