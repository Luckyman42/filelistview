#!/bin/bash

podman run -d --network=host -h localhost --name postgresdb -e POSTGRES_USER=developer -e POSTGRES_PASSWORD=develop -e POSTGRES_HOST_AUTH_METHOD=trust -e POSTGRES_DB=db  -h localhost -p 5432:5432 postgres:14
podman build -t localbuilder -f ./build.DOCKERFILE .
podman build -t localapp1 -f ./localdockerfiles/localApp1.DOCKERFILE .
podman build -t localapp2 -f ./localdockerfiles/localApp2.DOCKERFILE .

podman run -d --name app1 --network=host -h localhost  localhost/localapp1:latest
podman run -d --name app2 --network=host -h localhost  localhost/localapp2:latest
