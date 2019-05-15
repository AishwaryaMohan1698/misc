FROM openjdk:8
ADD target/docker-app-config-client.jar docker-app-config-client.jar
EXPOSE 8050
ENTRYPOINT ["java","-jar","docker-app-config-client.jar"]