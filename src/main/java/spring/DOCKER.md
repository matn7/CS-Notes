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

















