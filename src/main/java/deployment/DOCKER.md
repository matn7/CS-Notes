# Docker

- Docker is a standard for Linux containers
- A "Container" is an isolated runtime inside of Linux
- A "Container" provides a private machine like space under Linux
- Containers will run under any modern Linux Kernel

## Containers

- Have their own process space
- Their own network interface
- Run processes as **root** (inside the container)
- Have their own disk space
    - can share with host

## Docker Terminology

- Docker Image - The representation of a Docker Container. Like JAR or WAR file in Java
- Docker Container - The standard runtime of Docker. Effectively a deployed and running Docker Image.
Like a Spring Boot Executable JAR
- Docker Engine - The code which manages Docker stuff. Creates and runs Docker Containers

## Docker Engine Runtime

Client docker CLI :arrow_forward: REST API :arrow_forward: server docker daemon

***

## Docker Editions

### Docker Enterprise Edition

- Announced Match 2nd, 2017
- CaaS (Container as a Service) platform subscription
- Enterprise class support
- Quarterly Released
- Backported patches for 1 year
- Certfied infrastructure

- Important for regulatory compliance (PCI, SOX, SAS-70)

### Docker Community Edition

- Free Docker edition for developers and operations
- Monthly edge release with latest features for developers
- Quarterly releases for operations

### Which use

- Functionally, the two editions are the same
    - Like CentOS vs Red Hat Enterprise Linux
- For Java developers fine to use Docker Community Edition
- Docker Enterprise Edition is not available on some commercial OS such as RHEL, SUSE

***

## Why Docker

- Before containers and docker the apps are deploted and run on Application server created on top
of physical server

### What is container

- Container consists of:
    - Runtime Environment
    - An Application
    - Dependencies and Libraries
    - Configuration files

**Advantages**

- Lightweight and faster
- Consumes very less system resources

### Docker

- Open source platform and it consists:
    - Docker Engine - a runtime and software packaging tool
    - Docker Hub - Service for sharing the application in the cloud similar to github
- The output of a Docker build is a Docker Image
- To run Docker, we need to have docker running in our machine
- When the Docker image is run it creates a container

**Advantages**

- Rapid application deployment
- Easy sharing of artifacts
- Faster and Light Weight

**Dockerfile**

- FROM - It pulls the image from the docker hub. Here it pulls the java image alpine-oraclejdk8:slim
- ADD - Add command takes two arguments, one is source and the destination
- COPY - ./docker-entrypoint.sh /docker-endpoint.sh - This step copies the docker-entrypoint.sh
in to the docker image that gets built
- RUN chmod +x / docker-entrypoint.sh
- ENTRYPOINT - argument sets the concrete default app that is used every time a container is created using the
image. Often times ENTRYPOINT with CMS, you can remove "application" from CMS and just leave "arguments"
which will be passed to the ENTRYPOINT















