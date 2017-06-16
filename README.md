# Hands-on Microservices with Java
 
The term “Microservices Architecture” is now a popular term and in order to keep updated as a software developer I’ve been putting some effort to get a good understand about this architecture and the better way of implement it in Java using Spring technologies.
I was working in a nice company with a great team and a good tech stack, however we were not using the cutting edge Java features such Java 8 and Microservices architectures in that moment, so I had to start to looking at those knowledge outside the company and by myself. I wanted to play with the Java 8 and microservices and the best way of doing it is hands on code building something, then I decided to create a To Do system using as many fun staffs as possible and I will try to write a serie of posts talking about this experience.
The intention of this serie is have a source code walkthrough with many concepts and technologies put in place and combined in a whole system composed with differents microservices. I’m not planning to go deep in the concepts and tools, the intention here is present with an application example with some components used to develop microservices.You will have all the patterns, tools, technologies working together ready to run in your computer.
Note: We have intentionally made the implementation as simple as possible to make the source code easy to understand.
We are going to work with a To Do that will be composed with 8 applications (Reminder, User, Service discovery server, Mailer, OAuth Server, System integration test, API Gateway, Web application client).
Some patterns, tools and technologies that you will see in this system:
Spring Boot, Spring Data, Spring Cloud Eureka, Load Balancing with Ribbon, Declarative REST Clients with Feign, Software Circuit Breakers with Hystrix, Monitoring using Hystrix dashboard and Turbine, Security with Spring Security OAuth, Oauth2 with JWT, Aspect Oriented Programing with Spring AOP, Distributed events with Kafka, Maven Multimodule project, Event Sourcing, CQRS, REST, Web Sockets and all developed using Java 8.
This first post is going to provide an overview about the whole project and in the coming post I will explain more deep about what and how we are using the components in each microservice.

![Alt text](microservices-architecture.jpg?raw=true "microservices architecture")

In the image above you can see how our system interact along with all microservices. The user will access a Web Application written using Angular 2 that will connect to an OAuth Authorization Server that will be a central point of where users and authorities can be assigned. This server will return a Json Web Token containing info about the Oauth client with its authorities and the grated scope. After the user be authenticated and with the token, the Web Application will be able to talk to the API gateway, it will take the JWT, verify if it’s coming from the Authorization server then make calls to the microservices to build the response.
The User service is being used by the Oauth server to get the user’s authentication details and also it is being used by the API gateway to get User’s information.
The Remainder Service is where are placed the To Do functionalities, The ToDo service has a schedule job to check for remainders and notify the user by email, The emails are sent by the Mailer Service that is triggered from Remainder service by event using Kafka.
The System Integration Test is a Java application that is reaching the Reminder service’s endpoints.

## Connecting Microservices
In the Microservice architecture we have to deal with many microservices running in different IPs and ports and we need to find a way of managing each address without hard coding, that is where Netflix Eureka comes to rescue, it is a client side service discovery that allows services to find and communicate with each other automatically. We are using Spring Cloud Eureka in our system and you will need to have a look at how it works to understand how our services REST are communicating among the differents microservices. Once Eureka cares about where the services are running we can add instances and apply load balancing to distribute the incoming application traffic between our microservices. In our system we are using Netflix Ribbon as a client-side load balancer that enable us to achieve fault tolerance and increase the reliability and availability through redundancy. We are using Netflix Foreign for writing declarative REST client and integrate Ribbon and Eureka to provide a load balance http client.
Our system have some dependencies and we are trying to isolate our application from dependency failure using Netflix Hystrix Circuit Breaker, it helps stop cascading failures and allows us to fail fast and rapidly recovery, or add fallbacks. Hystrix maintains a thread-pool (or semaphore) for each dependency and rejects requests (instead of queuing requests) if the thread-pool becomes exhausted. It provides circuit-breaker functionality that can stop all requests to a dependency. You can also implement fallback logic when a request fails, is rejected or timed-out.

## Authentication
Security is something very important when we are developing any system and with microservice architecture is not different. The question: “How can I maintain security in my microservices” comes up immediately, and the first answer is OAuth2! And definitely OAuth2 is a very good solution, it is a well known authorization technology. It is widely used for Google, Facebook, Github at their APIs. It’s impossible to talk about security and don’t mention Spring Security and therefore we are using Spring Cloud Security with OAuth2.
Spring Security and OAuth2 are obvious choices when talking about secure distributed system, due to the wide use by the market, however we are adding one more element to our security concern, it is JWT(Json Web Token). Using only OAuth we would need to have an OAuth Authorization server to authenticate the user, generate the token and also a endpoint for the Resource servers to ask if the token is valid and which permission does it grant, requiring twice more request to the Authorization server than we really need. JWT provides a simple way of transmitting the permissions and user data in the access token and once the all data is already in the token string the resource servers don’t need to ask for token checks. All the information is serialized into JSON first, encoded with base64 and finally signed with a private RSA key assuming that all resource servers will have a public key to check if the token was signed for the proper private key then deserialize the token to have the information.
You can check the OAuth2 Authorization server and the Resource server implementation in the OAuth-server and in the API gateway and those were done following mainly this blog post
 
## REST
In our system we have 2 interaction styles, we have synchronous and asynchronous, for async style we are using distributed events with Kafka following the model publish/subscribe and for synch we have REST style using Request/Response supporting JSON and XML.
There are 4 levels of maturity of RESTful, starting at level 0, as described for Martin Fowler here and our services is in the level 2 because I decided not implement the Hypermedia Controls using the HATEOAS design pattern.
Because we are using the Spring Cloud we have out-of-box some scalability patterns which are placed in our HTTP connections that worth to be mentioned: Circuit breaker, Bulkheads, Load balancing, Connection pooling, timeouts and retry.
 
 
## Distributed Event
As mentioned above our communication between the Remainder service and Mailer service is done asynchronously using Kafka to distribute our events across the others microservices. In the Remainder service we have a schedule task to check for remainders time and publish an event RemainderFound that will have a subscribed event in the Mailer service which will start the process of sending email to the user. I invite you have a look at how we are doing this integration and how I wrote the serialization/ deserialization of the data sent to Kafka in the Kafka event Module.
 
## Eventsourcing and CQRS
In monolithic applications typically has a single relational database and we can use ACID transactions, as a result, our application can simply begin a transaction, change multiple rows, and commit the transaction.
Unfortunately to deal with data access in the microservice architecture is much more complex because the data are distributed in differents databases and implement business transactions across multiple services is a big challenge.
One choose is use events to deal with business transaction that span multiple services and you can see the implementation of Event sourcing with CQRS applied in the Mailer service. You will see how to separate Reads and Writes and enable us to scale each part easily.
We are using a relational database as an event store and then distributing the events using Kafka. I’m not using Kafka as the event store because is simpler to construct the aggregates from a relational database.
As you can notice, we already have a lot of things in this project, but still have many challenges that are not addressed here yet, but this is a project in development and we are planning adding more stuffs into it, such as Spring cloud config, Containers with Docker, continuous integration with Jenkins, Distributed trace with Spring Sleuth, Logging management with ELK and more. So keep tuned on our Github repository that we will try to keep updating it with more fun things.
 




## How to use

* Start al services -> sudo sh start_all.sh  
* Authenticate -> curl -X POST -vu todo-app:123456 http://localhost:8017/oauth/token -H "Accept: application/json" -d "password=1234&username=apssouza22@gmail.com&grant_type=password&scope=write&client_secret=123456&client_id=todo-app"  


* Get data using the access_token -> localhost:8018/path?access_token={access_token} or curl -H "Authorization: Bearer $TOKEN" "localhost:8018/path"

### Monitoring stream				
http://localhost:8021/turbine.stream