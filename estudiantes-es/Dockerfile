FROM maven:3.8.1-openjdk-21-slim AS build


RUN mkdir /sources
COPY ./ /sources


RUN echo "Building app..." && cd /sources && mvn clean package -DskipTests


FROM openjdk:21-oracle


WORKDIR /app
COPY --from=build /sources/target/estudiantes-es*.war /app/estudiantes-es.war
EXPOSE 8081


CMD ["java", "-jar", "estudiantes-es.war"]