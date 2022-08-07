FROM maven:3.8.6-jdk-11-slim AS build-common
WORKDIR /build
COPY ./common/. .
RUN mvn clean package -Dmaven.test.skip


FROM maven:3.8.6-jdk-11-slim AS build
WORKDIR /build
COPY ./jwt-service/. .
COPY --from=build-common /build/target/*.jar /local-repo/common.jar
RUN mvn install:install-file \
    -Dfile=/local-repo/common.jar \
    -DgroupId=tung.daongoc \
    -DartifactId=common \
    -Dversion=0.0.1-SNAPSHOT \
    -Dpackaging=jar \
    -DgeneratePom=true
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:11.0.6-slim AS deploy
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
# Install curl
RUN apt-get update && \
    apt-get install -y curl && \
	apt-get clean
ENTRYPOINT ["java", "-jar", "app.jar"]
