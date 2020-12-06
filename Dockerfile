FROM openjdk:8-jdk-alpine
ADD springboot-demo-0.0.1-SNAPSHOT.jar /springboot-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springboot-demo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
