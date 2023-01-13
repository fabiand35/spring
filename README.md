## Main
* Team: Fabian Droll, Davis Schnebelt
* License: MIT
* Language: Java (Version 19)
* Framework: Spring Boot (https://spring.io/projects/spring-boot) 2.7.6
* ORM: JPA (https://spring.io/guides/gs/accessing-data-jpa/) mit Hibernate (https://hibernate.org/) und PostgreSQL (spring-boot-starter-jpa)
* Docker Image: ghrc.io/fabiand35/personal
* Port: 9000

# OpenAPI generated server

## Overview
This server was generated by the [OpenAPI Generator](https://openapi-generator.tech) project.
By using the [OpenAPI-Spec](https://openapis.org), you can easily generate a server stub.
This is an example of building a OpenAPI-enabled server in Java using the SpringBoot framework.


The underlying library integrating OpenAPI to Spring Boot is [springdoc](https://springdoc.org).
Springdoc will generate an OpenAPI v3 specification based on the generated Controller and Model classes.
The specification is available to download using the following url:
http://localhost:8080/v3/api-docs/

Start your server as a simple java application

You can view the api documentation in swagger-ui by pointing to
http://localhost:8080/swagger-ui.html

Change default port value in application.properties

## Generate Java Code
```
docker run --rm -v "${PWD}:/local" openapitools/openapi-generator-cli generate \
-i /local/personnel-v1.yml \
-g spring \
-o /local/out/spring
```

# Build and Run Docker

## Local

### Build
* `docker build -t personal ./`

### Run
* `docker run -p 9000:9000 --network="host" --env-file ./env.list personal:latest`

# Config

## Environment variables

With default values for local development:
```
POSTGRES_PERSONAL_USER=postgres
POSTGRES_PERSONAL_PASSWORD=postgres
POSTGRES_PERSONAL_DBNAME=personal
POSTGRES_PERSONAL_HOST=localhost
POSTGRES_PERSONAL_PORT=5432
KEYCLOAK_HOST=localhost
KEYCLOAK_REALM=biletado
BACKEND_URL=http://localhost/api/reservations/
```

# CI/CD
CI/CD ist realized with GitHub [Actions](https://github.com/fabiand35/spring/actions).
The workflows are located in `.github/workflows/docker-image.yml`.

A Docker image is created and uploaded to the registry.

# Authentication
The JWT token is parsed by the [Spring Security](https://spring.io/projects/spring-security) framework.

The configuration is in the class `.org.openapitools.configuration.CustomSecurityConfiguration`.

# How to use
Start the docker compose application in the `compose` directory with: `docker-compose up`.
You can now test the personal backend under `localhost`.

If you want to test backend locally in IntelliJ you can use the run profile `OpenApiGeneratorApplication`
from the `.run` directory. For local tests, docker-compose needs to be running.
