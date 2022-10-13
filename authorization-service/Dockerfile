FROM openjdk:8u131-jdk-alpine
EXPOSE 8081
COPY ./target/auth-service.jar auth-service.jar 
ENTRYPOINT ["java","-jar","/auth-service.jar"]