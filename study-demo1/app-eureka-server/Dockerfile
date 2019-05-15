FROM openjdk:8
ADD target/docker-eureka-server.jar docker-eureka-server.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","docker-eureka-server.jar"]