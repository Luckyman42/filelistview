.PHONY: build

all: networkcreate dbrun build run
	echo done

networkcreate:
	if podman network exists shared; then \
		echo network already exists; \
	else \
		 podman network create shared;\
		 echo network creation success;\
    fi

dbrun:
	if podman container exists postgresdb; then \
		echo db already exists; \
	else \
		podman run -d --rm --net shared --name postgresdb -e POSTGRES_USER=developer -e POSTGRES_PASSWORD=develop -e POSTGRES_HOST_AUTH_METHOD=trust -e POSTGRES_DB=db -p 5432:5432 postgres:14;\
		echo db start success;\
	fi


build:
	podman build -t filelistview_local .

run:
	podman run -d --rm --net shared -it -e SERVER_NAME=App1 -p 8081:8080 localhost/filelistview_local
	podman run -d --rm --net shared -it -e SERVER_NAME=App2 -p 8082:8080 localhost/filelistview_local


