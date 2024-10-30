FROM maven:3.9.5-eclipse-temurin-21 AS build


WORKDIR /sources
COPY ./ /sources


RUN echo "Building app..." && mvn clean package -DskipTests


FROM eclipse-temurin:21-jre


WORKDIR /app
COPY --from=build /sources/target/auth-us*.war /app/auth-us.war
EXPOSE 8080


CMD ["java", "-jar", "auth-us.war"]