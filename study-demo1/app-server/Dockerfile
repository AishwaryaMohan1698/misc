FROM openjdk:8
ADD target/docker-app-server.jar docker-app-server.jar
EXPOSE 8010
ENTRYPOINT ["java","-jar","docker-app-server.jar"]