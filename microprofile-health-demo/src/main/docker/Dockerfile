FROM eclipse-temurin:21-jre

RUN mkdir -p /opt/application
COPY target/*-runner.jar /opt/application/backend.jar
WORKDIR /opt/application
CMD ["java", "-jar", "backend.jar"]