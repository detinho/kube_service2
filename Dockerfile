FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/kube_service2-0.0.1-SNAPSHOT.jar /opt/app
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/kube_service2-0.0.1-SNAPSHOT.jar"]