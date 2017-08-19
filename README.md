# Hands-on Microservices with Java
 
The term “Microservices Architecture” is now a popular term and in order to keep updated as a software developer, I’ve been putting some effort to get a good understand about this architecture and the better way of implementing it in Java using Spring technologies.

I was working in a nice company with a great team and a good tech stack, however, we were not using the cutting edge Java features such Java 8 and Microservices architectures in that moment, so I had to start looking for that knowledge outside the company. I wanted to play with the Java 8 and Microservices and the best way of doing it is hands on code building something, then I decided to create a To Do system using as many fun staffs as possible and I will try to write a series of posts talking about this experience.

The intention of this serie is to have a source code walkthrough with many concepts and technologies put in place and combined within a whole system composed with different microservices. I’m not planning to go deep in the concepts and tools, we have a lot of posts about those out there, the intention here is to present an application example containing patterns, tools, technologies used to develop microservices.

This is a studying application and we have intentionally made it as simple as possible to make the source code easy to understand. The application will be available and ready to run on your computer for use as a reference.

We are going to work with a "To Do" application that will be composed of 8 applications (Reminder, User, Service discovery server, Mailer, OAuth Server, System integration test, API Gateway, Web application client).

Some patterns, tools and technologies that you will see in this system:

Spring Boot, Spring Data, Spring Cloud Eureka, Load Balancing with Ribbon, Declarative REST Clients with Feign, Software Circuit Breakers with Hystrix, Monitoring using Hystrix dashboard and Turbine, Security with Spring Security OAuth, Oauth2 with JWT, Aspect Oriented Programing with Spring AOP, Distributed events with Kafka, Maven Multimodule project, Event Sourcing, CQRS, REST, Web Sockets and all developed using Java 8.

This first post is going to provide an overview of the whole project and in the coming posts, I will explain more deeply about what and how we are using the components in each microservice.

![Alt text](microservices-architecture.jpg?raw=true "microservices architecture")

In the image above you can see how our system interacts along with all microservices. The user will access a Web Application written using Angular 2, it will connect to an OAuth Authorization Server which will be a central point of where users and authorities can be assigned. 

This server will return a JSON Web Token containing info about the client with its authorities and the grated scope. After the user being authenticated and with the token, the Web Application will be able to talk to the API gateway, it will take the JWT, verify if it’s coming from the Authorization Server then make calls to the microservices to build the response.

The User service is being used by the OAuth server to get the user’s authentication details and also it is being used by the API gateway to get User’s information.

The Remainder Service is where are placed the ToDo functionalities, The ToDo service has a scheduled job to check for reminders and notify the user by email, the emails are sent by the Mailer Service which is triggered from Remainder ervice by event using Kafka.The System Integration Test is a Java application that is reaching the Reminder service’s endpoints.

The System Integration Test is a Java application that is reaching the Reminder service’s endpoints.

## Connecting Microservices
In the Microservice architecture, we have to deal with many microservices running in different IPs and ports and we need to find a way of managing each address without hard coding, that is where Netflix Eureka comes to rescue, it is a client-side service discovery that allows services to find and communicate with each other automatically. We are using Spring Cloud Eureka in our system and you will need to have a look at how it works to understand how our services REST are communicating between different microservices. Once Eureka cares about where the services are running we can add instances and apply load balancing to distribute the incoming application traffic between our microservices. In our system, we are using Netflix Ribbon as a client-side load balancer that enables us to achieve fault tolerance and increase the reliability and availability through redundancy. We are using Netflix Foreign for writing declarative REST client and integrate Ribbon and Eureka to provide a load balance HTTP client.

Our system have some dependencies and we are trying to isolate our application from dependency failure using Netflix Hystrix Circuit Breaker, it helps stop cascading failures and allows us to fail fast and rapid recovery, or add fallbacks. Hystrix maintains a thread-pool (or semaphore) for each dependency and rejects requests (instead of queuing requests) if the thread-pool becomes exhausted. It provides circuit-breaker functionality that can stop all requests to a dependency. You can also implement fallback logic when a request fails, is rejected or timed-out.

## Authentication
Security is something very important when we are developing any system and with microservice architecture is not different. The question: “How can I maintain security in my microservices” comes up immediately, and the first answer is OAuth2! And definitely OAuth2 is a very good solution, it is a well-known authorization technology. It is widely used for Google, Facebook, Github at their APIs. It’s impossible to talk about security and don’t mention Spring Security and in our project we are using it along with OAuth2.

Spring Security and OAuth2 are obvious choices when talking about secure distributed system, however, we are adding one more element to our security concern, it is JWT(JSON Web Token). Using only OAuth we would need to have an OAuth Authorization Server to authenticate the user, generate the token and also an endpoint for the Resource servers to ask if the token is valid and which permission does it grant, requiring twice more request to the Authorization Server than we really need. JWT provides a simple way of transmitting the permissions and user data in the access token and once all data is already in the token string the resource servers don’t need to ask for token checks. All the information is serialised into JSON first, encoded with base64 and finally signed with a private RSA key, it assumes that all resource servers will have a public key to check if the token was signed for the proper private key and deserialize the token to have the information.

You can have a look at the OAuth2 Authorization Server and the Resource Server implementation in the OAuth-server and in the API gateway, those were done following mainly this post 

## REST
In our system we have 2 interaction styles, we have synchronous and asynchronous, for the async style we are using distributed events with Kafka, following the model publish/subscribe and for synch, we have REST style using Request/Response supporting JSON and XML.

There are 4 levels of maturity of RESTful, starting at level 0, as described for Martin Fowler here and our services is in the level 2 because I decided not implement the Hypermedia Controls using the HATEOAS design pattern for the simplicity.

Because we are using Spring Cloud, we have out-of-box some scalability patterns which are placed in our HTTP connections that worth to be mentioned: Circuit breaker, Bulkheads, Load Balancing, Connection pooling, timeouts and retry. 
 
## Distributed Event
As mentioned above our communication between the Reminder service and Mailer service is done asynchronously using Kafka to distribute our events across the others Microservices. In the Reminder service we have a scheduled task to check for reminders time and publish the event 

RemainderFound that will have a subscribed event in the Mailer service which will start the process of sending the email to the user. I invite you to have a look at how we are doing this integration and how I wrote the serialization/ deserialization of the data that's sent to Kafka in the Kafka event Module.
 
## Eventsourcing and CQRS
Monolithic applications typically have a single relational database and we can use ACID transactions, as a result, our application can simply begin a transaction, change multiple rows, and commit the transaction if everything go right and rollback if something goes wrong. Unfortunately to deal with data access in the microservice architecture is much more complex due to the data be distributed in different databases and implement the business transactions across multiple services is a big challenge.

In our "ToDo" project we are using events to deal with business transaction that spans multiple services, and you can look at the implementation of Event Sourcing with CQRS applied in the Mailer service. You will see how to separate Reads and Writes that enable us to scale each part easily. We are using a relational database as an event store and then distributing the events using Kafka. I’m not using Kafka as the event store because is simpler to construct the aggregates from a relational database and we are trying to make things easy here.

As you can notice, we already have a lot of things in this project, but there are still many challenges that are not addressed here yet, but this is a project in development and we are planning adding more stuffs into it, such as Spring cloud config, Containers with Docker, continuous integration with Jenkins, Distributed trace with Spring Sleuth, Logging management with ELK and more. So keep tuned on our Github repository to see more fun things.


## How to use

* Start all services -> sudo sh start_all.sh  
* Authenticate -> curl -X POST -vu todo-app:123456 http://localhost:8017/oauth/token -H "Accept: application/json" -d "password=1234&username=apssouza22@gmail.com&grant_type=password&scope=write&client_secret=123456&client_id=todo-app"  

* Get data using the access_token -> localhost:8018/accounts?access_token={access_token} or curl -H "Authorization: Bearer $TOKEN" "localhost:8018/path"

### URLs
Monitoring stream - http://localhost:8021/turbine.stream

To dos http://localhost:8015/todos

Users http://localhost:8016/accounts

Eurreka server - http://localhost:8010/

Boot admin - http://localhost:8026