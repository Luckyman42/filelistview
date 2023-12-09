podman run -d --name db --network=host -h localhost  luckyman824/flatlistview:db
podman run -d --name app1 --network=host -h localhost  luckyman824/flatlistview:app1
podman run -d --name app2 --network=host -h localhost  luckyman824/flatlistview:app2
