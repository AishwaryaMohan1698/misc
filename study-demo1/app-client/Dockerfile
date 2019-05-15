FROM openjdk:8
ADD target/docker-app-client.jar docker-app-client.jar
EXPOSE 8020
ENTRYPOINT ["java","-jar","docker-app-client.jar"]