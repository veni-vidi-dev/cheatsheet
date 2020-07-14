FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/spaceship_crm_jrest_jooq.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080