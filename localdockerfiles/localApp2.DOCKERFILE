FROM eclipse-temurin:17-jdk-alpine AS App2
WORKDIR /app
EXPOSE 8082
ENV ServerName APP-2
COPY --from=localhost/localbuilder:latest /root/build /app/build
ENTRYPOINT  ["java", "-jar", "/app/build/libs/filelistview-0.0.1.jar", "--server.port=8082"]
