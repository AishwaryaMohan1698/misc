FROM openjdk:8
ADD target/docker-app-zuul.jar docker-app-zuul.jar
EXPOSE 8030
ENTRYPOINT ["java","-jar","docker-app-zuul.jar"]