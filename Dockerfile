FROM maven:3.8.6-openjdk-18 as prebuilder
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY target/openapi-spring-*.jar /




FROM prebuilder as builder
WORKDIR /src/target/
COPY --from=prebuilder /usr/app/openapi-spring-*.jar /
ENTRYPOINT java -jar /openapi-spring-*.jar