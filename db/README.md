##### Create the database container before starting the project

##### Remove existing containers (and images if required)

`docker container rm -f cache-example-db`

`docker image rm -f cache-example-db`

##### Build image and start/run the container
(Open a terminal and navigate to this directory before running following commands)

`docker build -t cache-example-db .`

`docker run --name cache-example-db -d -p 5432:5432 cache-example-db`

If the container is already created and you only want to start

`docker start cache-example-db`
