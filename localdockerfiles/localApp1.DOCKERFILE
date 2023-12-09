FROM eclipse-temurin:17-jdk-alpine AS App1
WORKDIR /app
EXPOSE 8081
ENV ServerName APP-1
COPY --from=localhost/localbuilder:latest /root/build /app/build
ENTRYPOINT  ["java", "-jar", "/app/build/libs/filelistview-0.0.1.jar", "--server.port=8081"]
