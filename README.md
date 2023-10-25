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

## Refreshing configuration properties values on runtime
* Add `@RefreshScope` on the microservice main method
* Add `management.endpoints.web.exposure.include=*` 
* Make changes on the external configuration file
* By now the changes are visible through the configuration server's endpoint (ex: localhost:8071/accounts/prod)
* Make a POST call to the micro service's actuator endpoint (ex: localhost:8181/actuator/refresh) => shows the updated config properties at runtime
* call the micro service's custom endpoint for config properties (ex: localhost:8181/account/properties) => values are up to date

## Encrypting configuration values
* Use configuration server endpoint /encrypt and pass the specific value to the body. (You can use /decrypt)
* Update the configuration value on the git repository and add {cipher} on the beginning of the encrypted value

## Spring cloud support for services discovery & registration
* Spring cloud netflix's eureka service which will act as a service discovery agent
* Spring cloud load balancer library for client-side load balancing
* Netflix Feign client to look up for a service between microservices

## Eureka
* Spring Cloud Eureka is a service registry that allows microservices to register themselves and discover other services in a distributed environment.
* Create Eureka server with Eureka server dependency.
* Add Eureka client dependency to microservice
* Set `eureka.client.serviceUrl.defaultZone` on the micro service app properties

## Heartbeat
A heartbeat signal is sent from each micro service (accounts loans ..) to the eureka server, to signal them that they are still alive services.

## Circuit breaker pattern
Offered by the Resilience4J project, on the accounts micro service pom.xml are the dependencies for Resilience4J
monitors an endpoint using the annotation @CircuitBreaker & implementing the fallback method

## Retry pattern


**** October course update
Section 6 ( course 64 -> 68 ):
- separate config from business logic
- inject config to the needed microservice
- maintain config in centralized repository

in spring boot ecosystem, 3 ways to handle configuration declaration/injection into ms
1) config spring boot properties and profiles
2) applying external configuration with spring boot
3) implementing a configuration server with spring cloud config server

15 factor methodology:
According to the 15-Factor methodology, configuration encompasses any element likely to change between deployments, such as credentials,
resource handles, and service URLs. Cloud native applications address this challenge by maintaining the immutability of the application
artifact across environments. Regardless of the deployment environment, the application build remains unchanged. In cloud native
applications, each deployment involves combining the build with specific configuration data. This allows the same build to be deployed to
multiple environments while accommodating different configuration requirements.

Overview with 15 factor methodology:

(github code base) ---> one build      ---> dev config  ---> Dev env
                        for all envs   ---> QA config   ---> QA env
                                       ---> Prod config ---> Prod env

by default spring booot look for config/properties inside application.properties
it also allows sensible overriding of config values

in spring boot project, 3 ways to inject property values to the busines logic,
1) AVOID HARD CODED using @Value
2) AVOID HARD CODED implementing Environment interface
3) RECOMMENDED using @ConfigurationProperties("prefix") on MyConfig (model) class and implementing getters and setters of the config values
 ( properties will be mapped to class attributes )





