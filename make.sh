#!/bin/bash

podman run -d --network=host -h localhost --name postgresdb -e POSTGRES_USER=developer -e POSTGRES_PASSWORD=develop -e POSTGRES_HOST_AUTH_METHOD=trust -e POSTGRES_DB=db  -h localhost -p 5432:5432 postgres:14
podman build -t app1 -f ./app1.DOCKERFILE .
podman build -t app2 -f ./app2.DOCKERFILE .

podman run -d --name app1 --network=host -h localhost  localhost/app1:latest
podman run -d --name app2 --network=host -h localhost  localhost/app2:latest
