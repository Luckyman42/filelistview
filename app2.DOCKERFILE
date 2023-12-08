FROM gradle:8.5.0 as build
WORKDIR /root
COPY . .
RUN ["gradle", "clean"]
RUN ["gradle", "javadoc"]
RUN ["gradle", "bootJar"]

FROM eclipse-temurin:17-jdk-alpine AS App2
WORKDIR /app
EXPOSE 8082
ENV ServerName APP-2
COPY --from=build /root/build /app/build
ENTRYPOINT  ["java", "-jar", "/app/build/libs/filelistview-0.0.1.jar", "--server.port=8082"]
