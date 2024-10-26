FROM maven:3.9.5-eclipse-temurin-21 AS build


RUN mkdir /sources
COPY ./ /sources


RUN echo "Building app..." && cd /sources && mvn clean package -DskipTests


FROM eclipse-temurin:21-jre


WORKDIR /app
COPY --from=build /sources/target/estudiantes-es*.war /app/estudiantes-es.war
EXPOSE 8081


CMD ["java", "-jar", "estudiantes-es.war"]