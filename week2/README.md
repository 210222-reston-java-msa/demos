# Week 2: Servlets, HTTP & REST
*The following is an overview of the topics to be covered in Week 2.* <br>
*You will be asked to answer the questions at the **bottom** in QC and your 1-on-1's with me.*

## HTTP Study Guide:
- Hyper Text Transfer Protocol

- Primary networking protocol used for client-server communication
  - The internet (world-wide-web) 

- This is also specifically used for RESTful web services

- For now, it's important to understand the differences between different
  HTTP verbs (methods), such as <code>GET</code> and <code>POST</code> as well as HTTP status codes

- All HTTP messages are composed of a **header** and a **body** which contains different
  pieces of information.. 
    - The header normally represents the metadata about the request. 
    - The body represents the data in the message, or the message itself.
    -
- **GET vs POST**
  - **GET** is utilized to retrieve data. We're retrieving data from some url, or endpoint,
    and normally clients and servers are not expecting these messages to contain content
    within the body. (Although, you definitely still can)
  - **POST** is generally used to send/update information on the url/endpoint, which means
    that the server os expecting the message to have information in the body.

- HTTP responses have a status code that represent the status of the request. We'll talk
  more about them in a moment, but we have seen examples such as 200 OK, and 201 CREATED *see the **HTTP Status Codes** listed below***

- HTTP responses can also have a body. If a GET request was performed, generally,the response is stored in the body.


- **HTTP request contents**
  - HTTP version
  - URL
  - HTTP verb / method
  - Request Headers
  - Request Body
  
- **HTTP response contents**
  - HTTP version
  - Status code
  - Response Headers
  - Response Body

- **HTTP verbs**
  - GET
  - POST
  - PUT
  - DELETE
  - PATCH
  - HEAD
  - OPTIONS
  - TRACE
  
### HTTP Response status codes
  - 100-199: informational
  - 200-299: success
    - 201: Created
    - 204: No content
  - 300-399: redirect
    - 301: Moved permanently
    - 304: Not modified
  - 400-499: client error
    - 403: Forbidden
    - 404: Not Found
    - 405: Method not allowed
    - 415: Unsupported media type
    - 451: Unavailable for legal reasons
  - 500-599: server error
    - 501: Not implemented
    - 502: Bad Gateway
    - 503: Service unavailable

## Client/Server Architecture
- A Client and a Server establish a connection according to a set of rules called a protocol. 
- [**HTTP**](https://developer.mozilla.org/en-US/docs/Web/HTTP/Overview) is a protocol which allows the fetching of resources, such as HTML documents. 
    - It is the foundation of any data exchange on the Web and it is a **client-server protocol**, which means requests are initiated by the recipient, usually the Web browser (client).
- *Describe the difference between **2 Tier**, **3 Tier**, **n-Tier** architecture*

<hr>

# Web Services (REST & SOAP) 
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

### 6 REST Constraints (*know these and google what they mean!*) :star:
There are six architectural constraints which makes any web service are listed below:<br>

  :one: Uniform Interface<br>
  :two: Stateless<br>
  :three: Cacheable<br>
  :four: Client-Server<br>
  :five: Layered System<br>
  :six: Code on Demand (optional)<br>

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

## :interrobang: SOAP Questions

14.  What does the acronym SOAP stand for?
15.  What protocols and data format do SOAP services use?
16.  What is the “contract” for a SOAP service?
17.  What’s the structure of a SOAP message?
18.  What are the SOAP messaging modes? Messaging Exchange Patterns?
19.  Are SOAP messages delivered with GET or POST requests?
    
## :interrobang: REST Questions

20.  What does the acronym REST stand for? What makes a service “RESTful”?  
21.  What protocols and data format do REST services use?  
22.  What are the architectural constraints of REST?  
23.  What is a “resource” in a REST service?  
24.  What does the “uniform interface” constraint mean? Give an example of some RESTful endpoints you would create for an API. Should the URLs contain nouns, verbs, or adjectives?      

<hr>

# Servlet Study Guide
*You are expected to know the following topics*:

- **Servlet Inheritance Hierarchy**
  - Servlet interface
  - GenericServlet abstract class
  - HttpServlet abstract class
- Servlet container
- Servlet lifecycle
- **Deployment descriptor**
  - What folder is it in?
  - web.xml tags
- Creating custom servlets
- Eager vs lazy loading / instantiation of servlets
- ServletConfig object
- ServletContext object
- RequestDispatcher
- Forwarding vs redirecting
- **Session tracking**
  - HttpSession API
  - Cookies
  - URL rewriting
  - HTML hidden fields
- Retrieving request parameters
- Retrieving data submitted from a form
- Sending a plain text response from a servlet 
- Sending a JSON object via mapping with Jackson
- Front controller design pattern

## Session Management
- The practice of storing information about the user in the server in order
  to improve user experience

**Why do sessions matter**?
- User Experience: Saving session information locally prevents
  needless hits to your database, which improves performance and allows you
  to personalize a user's experience.
  
- Security: Verify that a user actually has access to a resource that was
  provided by your website. Disallow access to sensitive information.

## Sending Users to different locations on the Web / Redirects
- Servlets take requests from clients and forward them to requested resources. How?
  - There are 2 ways:
    - `SendRedirect`
      - Supplied by Response object
      - This is a method: response.sendRedirect(location);
      - Actually send a response back to the client and then
        sends a new request back.
      - As a result, the information in the previous request is lost
      - Returns a 300 Series status code
    - `Forward`
      - Supplied by Request object
      - This method is declared on the RequestDispatcher Interface
      - When you use forward, the request never leaves the server
      - You can't request dispatch into another location (stay within same domain)
      - You make only 1 request, not 2

  - Main Takeaway:
    - Use sendRedirect when your client asks for a resource that is in some
      other location (the extra request is visible in the client as a new request)
    - Use forward if the your client is asking for a resource from the same
      project (something you have access to immediately)
    - Forward is handled by the server
    - sendRedirect is handled by the browser/client

## Servlet Config
- ServletConfig (interface) provides objects that are used by the Servlet
  containers (aka web container) to pass information to a servlet during
  initialization
  - getInitParameter()
  - getServletContext()
- Serializable interface is a marker interface (an empty interface) that can be
  implemented in order to allow an object to be "serialized" (to basically save
  to a file) and "deserialized".
  - In particular, if our Servlet classes implement Serializable, then we can serialize
    them to allow the servlet to "survive" restarting the web container.
- GenericServlet is an abstract class which supports any protocol, HTTP, UDP, TCP, etc
  - It is not necessarily designed for HTTP
- HttpServlet abstract class which allows you to create a servlet suitable
  for handling http requests.
  - doPOST, doGET, etc
  - Note, HttpServlet is an abstract class, so it is meant to be extended

## ServletConfig vs ServletContext

- The ServletConfig is unique to each individual servlet
  - No other servlet can access another's config
- The ServletContext is shared across all servlets

# :interrobang: Servlet QC Questions

1.  What is a servlet? What about a servlet container? Which servlet container have you worked with?

2.  Describe the servlet class inheritance hierarchy. What methods are declared in each class or interface?

3.  How would you create your own servlet?

4.  What is the deployment descriptor? What file configures your servlet container?   

5.  Explain the lifecycle of a servlet - what methods are called and when are they called?

6.  Is eager or lazy loading of servlets the default? How would you change this?

7.  What are some tags you would find in the web.xml file?

8.  What is the difference between the ServletConfig and ServletContext objects? How do you retrieve these in your servlet?

9.  What is the purpose of the RequestDispatcher?

10.  Explain the difference between RequestDispatcher.forward() and HttpServletResponse.sendRedirect()

11.  What are some different ways of tracking a session with servlets?

12.  What is object mapping? Any Java libraries for this?

13.  How would you send text or objects back in the body of the HTTP response from a servlet?

14.  What is the difference between getParameter() and getAttribute() methods?

15.  Explain the front controller design pattern
