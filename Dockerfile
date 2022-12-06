FROM maven:3.8.6-openjdk-18 as builder
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY /src/ $APP_HOME/src
COPY /pom.xml $APP_HOME
RUN mvn package


FROM openjdk:18 as personal
WORKDIR /src/target/
COPY --from=builder /usr/app/target/openapi-spring-*.jar /
ENTRYPOINT java -jar /openapi-spring-*.jar