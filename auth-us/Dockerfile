FROM maven:3.8.1-openjdk-21-slim AS build


RUN mkdir /sources
COPY ./ /sources


RUN echo "Building app..." && cd /sources && mvn clean package -DskipTests


FROM openjdk:21-oracle


WORKDIR /app
COPY --from=build /sources/target/auth-es*.war /app/auth-es.war
EXPOSE 8083


CMD ["java", "-jar", "auth-es.war"]