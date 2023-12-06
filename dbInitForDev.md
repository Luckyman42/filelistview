
$ podman run --network=host --name postgresdb -e POSTGRES_USER=developer -e POSTGRES_PASSWORD=develop -e POSTGRES_HOST_AUTH_METHOD=trust -h localhost -p 5432:5432 postgres:14

$ podman container ps -a
CONTAINER ID  IMAGE                          COMMAND     CREATED         STATUS         PORTS       NAMES
containerID  docker.io/library/postgres:14  postgres    10 seconds ago  Up 10 seconds              postgresdb

$ podman exec -it containerID bin/bash
$ psql -U developer -W
$ CREATE DATABASE db;