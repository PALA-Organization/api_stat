FROM maven:3.6.3-jdk-11

WORKDIR /jee-server

COPY . .

RUN mvn package

EXPOSE ${WEB_APP_PORT}

ENTRYPOINT ["java", "-jar", "/jee-server/target/server-last.jar"]
