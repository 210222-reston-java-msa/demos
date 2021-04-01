# Phase 4: Centralized Configuration

In this phase we will address the growing problem of distributed configuration. We are seeeing that becomes more inconvenient to track all of the configuration for our microservice application. Before our architecture expands too far, we will leverage Centralized Configuration with [Spring Cloud Config](https://spring.io/guides/gs/centralized-configuration/). This will allow us to create a GitHub repository to house all of our configuration. From there, we will create a config-service that will inject the configuration into our services at runtime.

The end result of this phase will be provided for comparison.

### Prerequisites

You will need an account on any Git Repository Service, such as [GitHub](https://github.com/) or [GitLab](https://gitlab.com/).

## Step 1: Create Configuration Repository

If we want to centralize our configuration, then we need to store it all in a single location, accessible in the cloud. A perfect solution for this is a Git Repository. Any Git Repository will do, but in this demo, we will be using GitHub.

Create a new Git Repository. You may name it whatever you wish, but ours will simply be named `config`. It is located [here](https://github.com/201026-reston-java-msa/config).

Normally, we would clone this Git Repository to update the configuration locally, but for this demonstration, we will just modify the repository through GitHub's UI.

## Step 2: Copy configuration

Now we need to make sure that our Config Repository has all of the same configuration that our applications already have. Click on `Add file > Create new file` on GitHub, and create a file named `flashcard.properties`. Then enter the contents according to the below snippet:

```properties
spring.application.name=flashcard

# Database Credentials
spring.datasource.url=jdbc:h2:mem:memdb
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true

# JPA Settings
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

It is the same information as our `application.properties`. We should heavily consider not including the database credentials here, as this exposes our username and password publicly. However, since we are only using an in-memory database, we include it here.

Note that we **do not** include the `server.port` property here. This is because for this demonstration, we have a flashcard-service and a flashcard-service2, which need to have different ports. If we included that property here, it would override our changed port. This is something we would do once we containerize our services. At that point, having the same port internal to each docker container is good design.

Then perform the same process for each of:

* `quiz.properties`
* `gateway.yml`

`quiz.properties`:
```properties
server.port=8090
spring.application.name=quiz

# Database Credentials
spring.datasource.url=jdbc:h2:mem:memdb
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true

# JPA Settings
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

`gateway.yml`:
```yaml
server:
  port: 8080

spring:
  application.name: gateway
  cloud:
    gateway:
      default-filters:
      - PrefixPath=/
      routes:
      # ============================
      - id: flashcard
        uri: lb://flashcard
        predicates:
        - Path=/flashcard/**
        filters:
        - StripPrefix=1
        - name: CircuitBreaker
          args:
            name: flashcard-fallback
            fallbackUri: forward:/fallback/flashcard
      # ============================
      - id: quiz
        uri: lb://quiz
        predicates:
        - Path=/quiz/**
        filters:
        - StripPrefix=1
```

Note: We **do** have the `server.port` property for these services.

## Step 3: Create Config Service

Now that we have a centralized repository with our configuration, we just need to create our config-service. This service will be responsible for injecting that configuration into our other services.

Create a new Service named `config-service` with the following Spring Starter Dependencies:

* Spring Boot DevTools
* Spring Boot Actuator
* Consul Discovery
* Config Server

Update the `ConfigServiceApplication` according to the below snippet:

```java
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigServiceApplication.class, args);
  }
}
```

Update the `application.properties` file to be similar to the below:

```properties
server.port=8888
spring.application.name=config

# Configure Git Repo for Centralized Configuration
spring.cloud.config.server.git.uri=https://github.com/201026-reston-java-msa/config
spring.cloud.config.server.git.default-label=main
```

Note: Port 8888 is the default port for Cloud Config Servers and the git URI should be for the repository you created in Step 1

## Step 4: Update Other Services
Now we need to add the `Config Client` Spring Starter Dependency to the following services:

* `quiz-service`
* `flashcard-service`
* `flashcard-service2`
* `gateway-service`

At this point we no longer need most of the configuration in the above services. Update each of their `application.properties` or `application.yml` to the below snippets:

quiz-service:
```properties
spring.application.name=quiz
spring.cloud.config.discovery.enabled=true
spring.cloud.config.discovery.serviceId=config
```

flashcard-service:
```properties
server.port=8089
spring.application.name=flashcard
spring.cloud.config.discovery.enabled=true
spring.cloud.config.discovery.serviceId=config
```

flashcard-service2:
```properties
server.port=8088
spring.application.name=flashcard
spring.cloud.config.discovery.enabled=true
spring.cloud.config.discovery.serviceId=config
```

gateway-service:
```yaml
spring.application.name: gateway
spring.cloud.config.discovery.enabled: true
spring.cloud.config.discovery.serviceId: config
```

These configuration tell each service that the Config Server can be found with Consul named config. Only the 2 flashcard-services have the server.port property included due to the manner in which we are demonstrating multiple instances. Normally those properties are not necessary.

## Step 5: Launch Services

We should now launch of all our applications. Note that the services should eventually run successfully regardless of order, but a proper order can order be helpful for a smooth launch.

1. config-service
2. flashcard-service
3. flashcard-service2
4. quiz-service
5. gateway-service

Our services will receive their configuration, such as port, from the config-service when they initially launch.

## Step 6: Dynamic Configuration

We can create our own properties in the configuration repository and use them in our services. To demonstrate this, we will create a `message` property for the quiz-service and return that property from a `/message` endpoint.

We'll start off by creating a `MessageController` class in the `com.revature.controllers` package of quiz-service according to the below snippet:

```java
@RestController
@RequestMapping("/message")
@RefreshScope
public class MessageController {

  @Value("${message}")
  private String message;

  @GetMapping
  public String getMessage() {
    return message;
  }
}
```
Note that `@Value` annotation was imported from `org.springframework.beans.factory.annotation`

The `@RefreshScope` annotation will refresh the configuration for this Controller without having to restart the service.

The `@Value` annotation will inject the value of the corresponding property into the field. This allows us to return the property at our `/message` endpoint.

Since we do not have the `message` property in our configuration yet, we must add it in order to allow the quiz-service to successfully launch.

Additionally, we need to expose the actuator refresh endpoint `/actuator/refresh`. We send a POST request to this endpoint to tell the service to refresh its configuration.

In the Config Repository, edit the `quiz.properties` file to include the following:

```properties
# Actuator Endpoints
management.endpoints.web.exposure.include=*

# Dynamic Configuration
message=Hello World!
```

Now when we send a GET request to `http://localhost:8080/quiz/message`, we receive the response `Hello World!` as expected.

Now if we change the value of the property in the Config Repository, our application will dynamically provide the new message without needing to restart!

To see this, change the message property to something else, such as `Hello World, this was updated dynamically! Isn't that neat?`.

After approximately 30 seconds, the config-service should obtain the new configuration, which will then be injected into the quiz-service. Then the quiz-service will update its Spring Beans when we send a POST request to `http://localhost:8080/quiz/actuator/refresh`.

You should see the response change from `Hello World!` to `Hello World, this was updated dynamically! Isn't that neat?`.

## Summary

We have increased the maintainability of our application's codebase by centralizing the various configuration files into a single Git Repository. Now we will have centralized versioning for every microservice configuration, with the new ability to dynamically refresh the configuration at runtime.

The final issue to tackle in Phase 5 is to resolve database consistency by using a Messaging Queue to synchronize all instances of a service.
