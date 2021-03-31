# Web & MicroServices
> *The first half of this document is a refresher on SOAP and REST.* <br>
> *The second half of this document is about Microservices and tools we use within them* - *A common accronym for Microservices is **MSA***.
<br>

## Web Services
A **web service** is a collection of open protocols and standards used for exchanging data between applications or systems.<br>
A **web service** is any service that:
- Is available over the Internet
- Uses a standardized XML messaging system
- Is not tied to any one operating system or programming language
- Is self-describing via a common XML grammar
- Is discoverable via a simple find mechanism

## What is a RESTful Web Service?
A RESTful Web Service is a lightweight, maintainable, and scalable service that is built on the REST architecture.
* A RESTful Web Service will expose an API from your application in a secure, uniform, stateless manner to the calling client. 
* The calling client can perform predefined operations using the RESTful service. 
* The underlying protocol for REST is HTTP.

## What is REST?
* REST stands for **RE**presentational **S**tate **T**ransfer.
* REST is a way to access resources which lie in a particular environment.
* [Overview of REST](https://restfulapi.net/rest-architectural-constraints/)

>  For example, you could have a server that could be hosting important documents or pictures or videos. All of these are an example of resources. If a client, say a web browser needs any of these resources, it has to send a request to the server to access these resources. Now REST services defines a way on how these resources can be accessed.

### 6 REST Constraints (*know these and google what they mean!*)
There are six architectural constraints which makes any web service are listed below:
1. Uniform Interface
2. Stateless
3. Cacheable
4. Client-Server
5. Layered System
6. Code on Demand (optional)

## What is SOAP?
- Simple Object Access Protocol: used to "expose" and "consume" webservices
- XML-based web service protocol
- Platform and language independent
- Legacy protocol: most companies switching to RESTful web services
- Can be used in conjunction with any transport-layer protocol (HTTP, SMTP, FTP, etc)
  - When used with HTTP, POST requests are used
  - HTTP must set that content type to XML
- Uses a contract (WSDL)
  - WSDL = Web service description language
  - Contract first vs contract last development
- Built-in security
- SOAP message elements
  - envelope - (mandatory) defines the start and end of a message
  - header - (optional) specifies attributes)
  - body - (mandatory) this is the XML data
  - fault - (optional) describes error that may have occured
 
 Resources for the differences:
 - [UpWork Resource](https://www.upwork.com/resources/soap-vs-rest-a-look-at-two-different-api-styles)
 
 ```
 <xml version=1.0>
 <soap-env:Envelope xmlns=soap-env=”www.w3.org/2001/12/soap-envelope” soap-env:encodingStyle=”www.w3.org/2001/12/soap-encoding”>
  <soap-env:Header>
  </soap-env:Header>
  <soap-env:Body>
      <soap-env:Fault>
      </soap-env:Fault>
  </soap-env:Body>
</soap-env:Envelope>
```
#### WSDL
- Web Service Description Language
- XML file that describes the service
- "Contract" or "Endpoint"
- WSDL elements are placed within the namespaces like this:
  - `xmlns:xsd, xmlns:soap`
- WSDL specifies ports, messaes, operations, services

<br>

<hr>

<br>

# Service Oriented Architecture - *SOA* :star:
- SOA involves designing your application as a collection of services
  - **What's a Service?** Services are programs that run independently and achieve some business logic and communicate over a network.
  <br>
- See Martin Fowler's Essay on SOA ["Service Oriented Ambiguty"](https://martinfowler.com/bliki/ServiceOrientedAmbiguity.html)
- See OpenGroup's [introduction to Service Oriented Architecture](https://www.opengroup.org/soa/source-book/soa/p1.html)

> **SOA is better suited for large and complex business application environments that require integration with many heterogeneous applications**; smaller applications are not a good fit for SOA as they don’t need a messaging middleware component. **Microservices, on the other hand, are better suited for smaller and well-partitioned, web-based systems in which microservices give you much greater control as a developer**. 

<br>

<hr>

<br>

# Microservices - *MSA* :star:
Microservices - also known as the microservice architecture - is an architectural style that structures an application as a collection of services that are:
- Highly maintainable and testable. 
- Loosely coupled. 
- Independently deployable. 
- Organized around business capabilities

- MSA is a subset of SOA, but it satisfies a few other conditions: :star: :star: :star:
  - **Each Microservice MUST satisfy the SRP (Single Responsibility Principle)
    - Each Module should be responsible for ONE thing! (And do it well).
  - MSA can't be composed of other services
  - MSA achieve scalability and resilience through independence (we can take down or spin up instances of each service).
<br>
> Like SOA, microservices architectures are made up of loosely coupled, reusable, and specialized components. However, rather than being adopted enterprise-wide, microservices are typically used to build individual applications in a way that makes them more agile, scalable, and resilient.
<br>
- In older models of Enterprise level SOA, services communicate using SOAP, so that means that each service has a `WSDL` which it uses to communicate with another service.
- Today, most companies use RESTful Services
- MSA in general, use **Service Registries and Discovery Clients** to allow communication without centralized routing.

<br>

<hr>

<br>

# Benefits of MSA over Monoliths :star:
Microservices are:
- Loosely coupled 
- Modular
- Fault tolerant (no single point of failure)
- Scalable: independent services easily horizontally scale, and we only scale out the parts we need
  - Think of horizontal scaling as adding more nodes rather than increasing the power of one individual node
   - Increasing the power of an individual node would be vertical scaling.
 - MSA's are **Implementation Agnostic** -- This means that services can be programmed in different languages and still communicate with eachother.
 - Testable: you test each service independtly
 - Good for development within large organizations: many small autonomous teams (agile, devOps-y approach) teams maintain RESTful microservices.
  - Even large orgs like FANG (Facebook, Amazon, Netflix, Google) does development with MSA.
  
  ## Drawbacks of MSA:
  - Chattiness of MicroServices
    - HTTP traffic can reduced by replacing it with **Messaging Queue's** like *Kafka* and *RabbitMQ*.
  - Spring Cloud Sleuth and Zipkin allows logging and tracing across your services.
  - Docker, Kubernetes (container orchestrator) or Docker Swarm and cloud platforms enable us to deploy MSA's

<br>

<hr>

<br>

# Netflix OSS for Microservices:
- **Eureka Server**: Service Registry.  MSA's register their URI and status with Eureka
  - enable Eureak with `@EnableEureakServer`
- **Eureka Client**: Discovery Client -- retrieves the registry from Erueka Server to locate multiple instances of any other service
  - Enable by putting `@EnableDiscoveryClient`
- **Ribbon** = loadbalancer.
  - Any service that's running Ribbon will balance its outgoing traffic between instance to other services.
- **Zuul**: Gateway.  Allows external entities to access some of our resources/services with the MSA network.
  - Enable wirh `@EnableZuulProxy`
  - Configure routing in an `application.properties` file or `application.yml`
 - **Hystrix**: Circuit Breaker -- provides fallback so our app doesn't crash (prevents cascading failure)

## Spring Cloud MSA Tools
- **Config Server** provides a centralizes and version controlled configuration for MSA.
  - Enable with `@EnableConfigServer`
- Spring REST and Spring Data to build a RESTful API
- RabbitMQ: Messaging Queue - Broadcast info to all listening MSA's instead of sending many HTTP requests.
 
