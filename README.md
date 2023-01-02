## Build & run a docker image from Dockerfile
* Build project using maven command, `mvn clean install`.
* Run command docker build . -t udemy/micro-service-one (-t specifies the tag to add to the image).
* Run a container for the image using the command `docker run image_id`

## Build a docker image with paketo buildpacks
* Add xml tag to pom.xml file
<!-- build Dockerfile/image with command: mvn spring-boot:build-image -->
    <image>
        <name>udemy/micro-service-one</name>
    </image>
under build>plugins>plugin>configuration to be able to build docker image with *buildpacks*.
* Build docker image with command `mvn spring-boot:build-image` 

## Build & run docker image using docker-compose
* Run command `docker-compose up` will pull the image from Docker Hub (if they are not already pulled) and run the containers for these specific images.

### Useful commands:
* docker ps
* docker images
* docker image rm -f image_id