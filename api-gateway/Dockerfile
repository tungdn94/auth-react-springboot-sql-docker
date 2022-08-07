FROM maven:3.8.6-jdk-11-slim AS build
WORKDIR /build
COPY . .
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:11.0.6-slim AS deploy
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
# Install curl
RUN apt-get update && \
    apt-get install -y curl && \
	apt-get clean
ENTRYPOINT ["java", "-jar", "app.jar"]
