FROM gradle:8.5.0 as build
WORKDIR /root
COPY . .
RUN ["gradle", "clean"]
RUN ["gradle", "javadoc"]
RUN ["gradle", "bootJar"]
