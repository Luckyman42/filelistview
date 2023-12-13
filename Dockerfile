FROM gradle:8.5.0 as build
WORKDIR /root
COPY . .
RUN ["gradle", "clean"]
RUN ["gradle", "javadoc"]
RUN ["ls", "-al", "/root/build/resources/main/"]
RUN ["gradle", "bootJar"]

FROM eclipse-temurin:17-jdk-alpine AS app
WORKDIR /app
EXPOSE 8080
ENV ServerName APP
COPY --from=build /root/build /app/build
ENTRYPOINT  ["java", "-jar", "/app/build/libs/filelistview-0.0.1.jar", "--server.port=8080"]
