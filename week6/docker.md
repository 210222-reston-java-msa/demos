> *I reccomend this resource for understanding the basics of virtualization vs. containerization.  Read:* [What is Docker? In Simple English](https://blog.usejournal.com/what-is-docker-in-simple-english-a24e8136b90b#:~:text=Terms%20definition,all%20out%20as%20one%20package)    🔍

## Docker
*Docker is an open platform for developing, shipping, and running applications. Docker enables you to separate your applications from your infrastructure so you can deliver software quickly.*

- The first half of this document contains guides on how to run a Docker container in an EC2.
- The second half guides you in how to Dockerize your own Angular application.

<br>
<br>

## *Running* `nsnake` *Container in an EC2* 🐍

1. SSH into an EC2 instance.  You will only need 1 security rule which is supplied to you by default (SSH port 20).

2. Once you've ssh's in, run the following commands:

``` sh
# Create a group of users within your EC@ instance with Docker permissions
sudo groupadd docker
sudo usermod -a -G docker ec2-user
sudo service docker start

# Make a folder to contain your Dockerfile -> cd into it
mkdir snakedocker
cd snakedocker

# Use the nano command to open up a text editor
nano Dockerfile

# The following commands go inside your Dockerfile

# FROM speifies the base image, running an ubuntu OS
FROM ubuntu:18.04

# RUN specifies the duty of the container.  It should install this software
RUN apt-get update && apt-get install -y libncurses5-dev && apt-get install -y nsnake

# CMD specifies the instruction that is to be executed when a Docker container starts
# In this case, we are running the executable located at /usr/games/nsnake within the nginx container
CMD ["/usr/games/nsnake"]
```

3. Exit the `nano` text editor with the keyboard commands `ctrl` + `X`, then press `Y` to save.

4. Build the image from the `Dockerfile` with the following command: 
    >`sudo docker build -t snake:auto .`
    
5. Finally, run the image with the following command and you'll load up the game thanks to the `CMD` command.
    >`sudo docker run -it snake:auto` <br>
        the `-it` flag allows you to interact with the executable file, which, in this case, is the game itself.

<br>

<hr>

<br>

## How to `Dockerize` and Angular app 🦸

1. Open an angular applicaiton (like [hero-frontend](https://github.com/210222-reston-java-msa/demos/tree/main/week5/hero-frontend)) in VScode.

2. Create a new file (directly in the `hero-frontend` directory) called `Dockerfile`
    > The file must be titled `Dockerfile` *exactly*, with no extensions.

3. Create a distribution directory which will hold all of the files to be deployed on nginx. Inside `hero-frontend` the following command:
    > `npm run build --prod`
    
4. Within this file, write the following code:
```sh
# FROM specifies the base image (also called the parent image).  In this case we will use Ubuntu OS
FROM nginx:1.17.1-alpine

# Copy all files from the dist directory to the directory where ngix hosts the files to serve at a specified port
COPY dist/hero-frontend /usr/share/nginx/html
```

5. Build an image from the Dockerfile with the following command: <br>
    > `docker build -t hero-frontend:latest`

6. Create a command by running the imamge on port 80 with the following command. <br>
    > `docker run -p 80:80 -d --name my-hero-container  hero-frontend:latest`

<br>

<hr>

<br>

# Docker Images
Blueprint for a container 

[This video from VMWare draws an analogy to docker images being like Java classes with containers being analogous to Java objects](https://www.youtube.com/watch?v=EnJ7qX9fkcU&t=4s)

Images form a kind of heirarchy. One image will be "From" another with added info, dependencies, commands, applications, etc. The  added info and command each form a new layer on the image. With each of these layers being indicated in a the Dockerfile that defines what's needed for the image.

Images are named and tagged with the version. They also have an id which uniquely identifies them. 

## Existing Docker Images
Pull images from some existing registry(repository of images). The default configuration is from the DockerHub. 
- `docker pull *image name* `
- `docker run *image name*` (this will pull the image if it doesn't already exist in the local system)

## Building Our Own Images 
 - Dockerfile
 - From existing container
    - `docker commit` 

We can then `push` our images to a given registry including DockerHub

## Managing Images 

We can use the docker CLI to manage the images on our local system. We can list out the existing images, get their details, remove and update them. 

Additionally, we can use the CLI to aid in connecting to a registry to quickly and easily distribute changes. In this case updating the software is as simple as updating the image. 

Users can easily pull new images and spin up containers/applications with the modifications made. 

<br>

<hr>

<br>

# Docker Containers

Runnable isolated instance of a set of processes and their dependencies.

A Docker container is built from a Docker image. The image lays out everything the processes that run in the container will need.  

Docker Containers are managed by the Docker Daemon as part of the Docker Engine. The Docker Engine allows Docker containers to be standardized and very portable. 


### Under the hood
The underlying nature of Docker containers is based on the capabilities provided by namespaces and cgroups. Docker containers also take advantage of a file system called UnionFS. Docker manages all this in tandem in a wrapper refered to as *container format*. The container format used by default is `libcontainer`. 
 
Docker containers when run on a Linux system typically share the Host OS- just as one would expect of a containerized app. *The goal is lightweight after all.* 

However, in the case of Windows, Docker containers may use an additional layer of virtualization enabling you to run Linux containers on a Windows OS. This is why it's necessary to have Hyper-V and virtualization enabled when trying to install Docker on a Windows OS. (Because truly it is akin to running a container in a VM.)

### Benefits
And they allow for all the benefits outlined in the containerization notes. i.e.(copied verbatim for convenience) 
 - Secure 
    - Isolation and Virtualization keep your containerized apps more secure
- Standardized and thus Portable
    - Think write once run anywhere
- Lightweight 
	- shares the host operating system's kernel 
- Flexible and Loosely Coupled 
- Scalable
    - Easy to spin up and because of this lightweight ease they can be scaled up quickly 

