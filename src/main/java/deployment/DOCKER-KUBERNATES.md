# Docker

- Docker makes it really easy to install and run software without worrying about setup or
dependencies

```
docker run -it redis
```

- What is Docker
    - Docker is a platform or ecosystem around creating and running containers

- Image
    - Single file with all the deps and config required to run a program

- Container
    - Instance of an image. Runs a program

- Docker Client CLI
    - Tool thet we are going to issue commands to

- Docker Server (Docker Daemon)
    - Tool that is responsible for creating images, running containers etc

```
docker run hello-world
```

- Namespacing: Isolating resources per process (or group of processes)
    - Processes, Hard drive, Network, Users, Hostnames, IPC
- Control Groups (cgroups): Limit amount of resources used per process
    - Memory, CPU Usage, HD I/O, Network Bandwidth

- Container: group of processes assigne to it

## Docker Client CLI

```
docker run hello-world
```

```
docker run <image_name> <command>
```

```
docker run busybox echo hi there
docker run busybox ls
```

- Listing running containers

```
docker ps
docker ps -a
docker ps | wc -l
```

- Container lifecycle

```
docker run = docker create + docker start

docker create <image_name>
docker start <container id>
```

```
docker create hello-world
docker start -a bc219e7a88bb1fabb72c19fd502dfb16c5cb12f24a43c5699f796c6fe10d61eb

// -a wath for output from container
```

- Removing stopped container

```
docker system prune
```

- Retrieving log output

```
docker create busybox echo hi there
docker start e207a006e63dee92d178f0b4f4cd619987c3b6782ad3e332ed6d49bcb9c015a7

docker logs e207a006e63dee92d178f0b4f4cd619987c3b6782ad3e332ed6d49bcb9c015a7
```

- Stopping Containers

```
docker stop <container_id>
docker kill <container_id>
```

- SIGTERM: stop conteiner with cleanup time
- SIGKILL: shut down immediately

```
docker create busybox ping google.com
docker start bb656a399364f374a4a701fdd34783381894f550979365dca464d407c061205f

docker stop bb656a399364 // after 10 s kill signal

docker start bb656a399364
docker kill bb656a399364 // kill immediately
```

- Multi-command container

```
redis-server
redis-cli
```

**with docker**

- Execute an additional command in a container

```
docker exec -it <conteiner_id> <command>
```

```
docker run redis
docker exec -it b67b8ebf2444 redis-cli
```

- STDIN, STDOUT, STDERR: communicate information to linux process
- STDIN: communicate information into a process
- STDOUT: communicate information outside a process
- STDERR: line STDOUT but error info

```
-it flag
-i means execute STDIN on redis cli
-t show text pretty

docker exec -i b67b8ebf2444 redis-cli
```

- shell access to running container

```
docker exec -it b67b8ebf2444 sh
```

```
bash | powershell | zsh | sh

Command Processors
```

- Starting with a shell

```
docker run -it busybox sh
```

- Container isolation

---

## Creating Docker Images

```
Dockerfile -> Docker Client -> Docker Server -> Usable Image!
```

- **Creating a Dockerfile**
    - Specify a base image
    - Run some commands to install additional programs
    - Specify a command to run  on container startup

```
docker build .

docker run 81615c9d8a9c
```

- Dockerfile

```Dockerfile
# Use an existing docker image as a base
FROM alpine

# Download and install a dependency
RUN apk add --update redis

# Tell the image what to do when it starts
# as a container
CMD ["redis-server"]
```

- Instruction elling Docker Server what to do
    - FROM alpine : startup pre installed set of programs that are useful to you! Sort of OS
    - RUN apk add --update redis : package manager preinstalled in alpine base image
    - CMD

- Alpine base image

- Docker commit
```
docker run -it alpine sh
#  apk add --update redis

// On second terminal
docker ps // container id from previous step
docker commit -c 'CMD ["redis-server"]' d9332e55b0c9

// Container id from previous step
docker run 5a9f892320a6e23c098da4c84bbb22986b48bf1272f736a710011e696187c4ab
```

---

## Docker project

- Node JS application

- alpine: in Docker world image as small and compact as possible
- COPY: instruction to move file and folders from our local system to filesystem in container

```
// build with tar
docker build -t majka/simpleweb .
docker run majka/simpleweb
```

- Container port mapping

```
// localhost port : container port
docker run -p 8080 : 8080 <image id>

docker run -p 8080:8080 majka/simpleweb
```

- Shell into container

```
docker run -it majka/simpleweb sh

docker run -p 8080:8080 majka/simpleweb

docker exec -it 15dbf4eedbbf sh
```

- Any following command will be executed relative to this path in the container

```
WORKDIR /usr/app
```

- Final version

```Dockerfile
# Specify a base image
FROM node:alpine

WORKDIR /usr/app

# From current working directory to ./ in container
COPY ./package.json ./

# Install some dependencies
RUN npm Install

COPY ./ ./

# Default command
CMD ["npm", "start"]
```

***

## Docker Compose

```
Node App ---> Redis (in memory data store)


+------------------+
| Docker Container |
|------------------|
| Node App         |------+
|                  |      |     +------------------+
+------------------+      |     | Docker Container |
                          +---->|------------------|
+------------------+            |                  |
| Docker Container |      +---->|   Redis          |
|------------------|      |     +------------------+
| Node App         |------+
|                  |
+------------------+
```

- Node App

```Dockerfile
FROM node:alpine

WORKDIR '/app'

COPY package.json .
RUN npm install
COPY . .

CMD ["npm", "start"]
```

```
docker build -t majka/visits:latest .
```

- Redis

```
docker run redis
```

- Docker compose
    - Separate CLI that gets installed along with Docker
    - Used to start up multiple Docker containers at the same time
    - Automates some of the long-winded arguments we were passing to 'docker run'

**docker-compose.yml**

```yml
version: '3'
services:
  redis-server:
    image: 'redis'
  node-app:
    build: .
    ports:
      - "4001:8081"
```

```
docker run myimage -----> docker-compose up


docker build .     -----> docker-compose up --build
docker run myimage
```

```
docker run -d redis
docker ps
docker stop <docker_id>
```

**Lunch in background**
```
docker-compose up -d
```

**Stop Containers**
```
docker-compose down
```

**Container restarts**

| "no" | Never attempt to restart this container if it  stops or crashes |
|---|---|
| always | If this container stops always attempt to restart it |
| on-failure | Only restart if the container stops with an error code |
| unless-stopped | Always restart unless forcibly stoped |

**Docker Container status**
```
docker-compose ps
```

***

## Production Grade Workflow

- React application

```
npm install -g create-react-app
create-react-app frontend

npm run start       // Starts up dev server
npm run test        // Runs tests
npm run build       // Builds a production version of the application
```

**Dockerfile.dev**
```Dockerfile
FROM node:alpine

WORKDIR '/app'

COPY package.json .
RUN npm install

COPY . .

CMD ["npm", "run", "start"]
```

```
docker build -f Dockerfile.dev .

docker run -p 3000:3000 <image_id>
```

### Docker Volumes

```
docker run -p 3000:3000 -v /app/node_modules -v $(pwd):/app <image_id>

docker build -f Dockerfile.dev .
docker run -p 3000:3000 -v /app/node_modules -v $(pwd):/app bef5d9a97007
```

### Docker Compose

- Makes execute `docker run` easier

```yml
version: '3'
services:
  web:
    build:
      context: .
      dockerfile: Dockerfile.dev
    ports:
      - "3000:3000"
    volumes:
      - /app/node_modules
      - .:/app
```

```
docker-compose up
```

- Run test

```
npm run test

docker build -f Dockerfile.dev .
docker run -it d3d34d267455 npm run test

docker-compose up
docker exec -it 9eb9e2d28f6f npm run test
```

```Dockerfile.dev
  tests:
    build:
      context: .
      dockerfile: Dockerfile.dev
    volumes:
      - /app/node_modules
      - .:/app
    command: ["npm", "run", "test"]
```

```
docker-compose up --build
docker attach 93e1fcc6fa7f

# Shell into container
docker exec -it 93e1fcc6fa7f sh
```

### nginx

- Web server. Taking incomming traffic, route it and response to it

### Multi-Step Docker Builds

```
1. Use node:alpine
2. Copy the package.json file
3. Install dependencies     // deps only needed to execute 'npm run build'
4. Run 'npm run build'
5. Start nginx              // Where nginx compong from ?
```

```Dockerfile
FROM node:alpine as builder
WORKDIR '/app'
COPY package.json .
RUN npm install
COPY . .
RUN npm run build

FROM nginx
COPY --from=builder /app/build /usr/share/nginx/html
```

```
docker build .
// 80 in nginx port inside docker container
docker run -p 8080:80 c2edc713ec9c
```

***

## CI deployment with AWS

### Github Setup

```
1. Create github repo
2. Create local git repo
3. Connect local git to github remote
4. Push work to github
```

### Travic CI

```
1. Tell Travis we need a copy of docker running
2. Build our image using Dockerfile.dev
3. Tell Travis how to run our test suite
4. Tell travis how to deploy our code to AWS
```

```yml
sudo: required
services:
  - docker

before_install:
  - docker build -t matn7/docker-react -f Dockerfile.dev .


script:
  - docker run -e CI=true matn7/docker-react npm run test
```

### AWS Elastic Beanstalk

**.travis.yml"

```yml
deploy:
  provider: elasticbeanstalk
  region: us-west-2
  app: "docker"
  env: "Docker-env"
  bucket-name: "elastickeanstalk-us-west-2-<some_nums>"
  bucket-path: "docker"
  on:
    branch: "master"
  access_key_id:
    secure: $AWS_ACCESS_KEY
  secret_access_key:
    secure: $AWS_SECRET_KEY
```

**Exposing port in Dockerfile**

```Dockerfile
EXPOSE 80
```

***

## Multi Container Deployment

- Single Container Deployment Issues
    - The app was simple - no outside dependencies
    - Our image was built multiple times
    - How do we connect to a database from a container?

**Multi container**

![Alt text](docker_img/deployment.png "Deployment")


```console
create-react-app client
```

```Dockerfile
FROM node:alpine
WORKDIR '/app'
COPY ./package.json ./
RUN npm install
COPY . .
CMD ["npm", "run", "start"]
```

```console
docker build -f Dockerfile.dev .
```

```Dockerfile
FROM node:alpine
WORKDIR "/app"
COPY ./package.json ./
RUN npm install
COPY . .
CMD ["npm", "run", "dev"]
```

```yml
version: '3'
services:
  postgres:
    image: 'postgres:latest'
  redis:
    image: 'redis:latest'
  nginx:
    restart: always
    build:
      dockerfile: Dockerfile.dev
      context: ./nginx
    ports:
      - '3050:80'
  api:
    build:
      dockerfile: Dockerfile.dev
      context: ./server
    volumes:
      - /app/node_modules
      - ./server:/app
    environment:
      - REDIS_HOST=redis
      - REDIS_PORT=6379
      - PGUSER=postgres
      - PGHOST=postgres
      - PGDATABASE=postgres
      - PGPASSWORD=postgres_password
      - PGPORT=5432
  client:
    build:
      dockerfile: Dockerfile.dev
      context: ./client
    volumes:
      - /app/node_modules
      - ./client:/app
  worker:
    build:
      dockerfile: Dockerfile.dev
      context: ./worker
    volumes:
      - /app/node_modules
      - ./worker:/app
```

### Nginx Path Routing

- Nginx will look for all of these requests (index.html, main.js, values/all, values/current) and decide
which server route request to.
-----------+
```

![Alt text](docker_img/nginx.png "Nginx")

**default.conf**

```conf
upstream client {
    server client:3000;
}

upstream api {
    server api:5000;
}

server {
    listen: 80;

    location / {
        proxy_pass http://client;
    }

    location /api {
        rewrite /api/(.*) /$1 break;
        proxy_pass http://api;
    }
}
```

### Nginx Image

```console
docker-compose up --build
```

**test app**
```
http://localhost:3050/
```





