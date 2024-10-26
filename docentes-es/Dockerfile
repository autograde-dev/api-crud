FROM maven:3.9.5-eclipse-temurin-21 AS build

RUN mkdir /sources
COPY ./ /sources


RUN echo "Building app..." && cd /sources && mvn clean package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /sources/target/docentes-es*.war /app/docentes-es.war
EXPOSE 8082


CMD ["java", "-jar", "docentes-es.war"]