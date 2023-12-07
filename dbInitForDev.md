podman run --network=host --name postgresdb -e POSTGRES_USER=developer -e POSTGRES_PASSWORD=develop -e POSTGRES_HOST_AUTH_METHOD=trust -e POSTGRES_DB=db  -h localhost -p 5432:5432 postgres:14

