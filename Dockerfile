FROM openjdk:17-jdk-slim-buster

ARG JAR_FILE=target/personalfinancesapi-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} personalfinances.jar

ENTRYPOINT ["java","-jar","/personalfinances.jar"]

EXPOSE 8080