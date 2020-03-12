FROM maven:3.6.3 AS build
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY . .
RUN mvn install -DskipTests

FROM openjdk:8-jdk-alpine
ENV APP_NAME=jwt
ENV APP_VERSION=1.0.0
ENV APP_PORT=8080
ENV ARTIFACT_NAME=$APP_NAME-$APP_VERSION.jar

ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
RUN mkdir -p target/jars
COPY --from=build target/jars .
EXPOSE $SERVER_PORT
RUN echo $ARTIFACT_NAME
ENTRYPOINT java -jar ${ARTIFACT_NAME}