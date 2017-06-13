# Microservices programmatically 
 
The term "Microservices Architecture" is now a popular term and in order to keep updated as a software developer I've been putting some effort to get a good understand about this architecture and the better way of implement it in Java using Spring technologies. 
 
I was working in a nice company with a great team and a good tech stack, however we were not using the cutting edge Java features such Java 8 and Microservices architectures in that moment, so I had to start to looking at those knowledge outside the company and by myself. I wanted to play with the Java 8 and microservices and the best way of doing it is building something, then I decided to create a To Do system using as many fun staffs as possible and I will try to write a serie of posts talking about this experience.
 
The intention of this serie is have a source code walkthrough with many concepts and technologies put in place and combined in a whole system composed with differents microservices. I'm not planning to go deep in the concepts and tools, the intention here is present with an application example, some components used to develop microservices.You will have all the patterns, tools, technologies working together ready to run in your computer.
 
We are going to work with a To Do that will be composed with 8 microservices (Reminder, User, Service discovery server, Mailer, Oauth Server, System integration test, Api gateway, Web application client). 
 
Some patterns, tools and technologies that you will see in this system: 
Spring Boot, Spring Data, Spring Cloud Eureka, Load Balancing with Ribbon, Declarative REST Clients with Feign, Software Circuit Breakers with Hystrix, Monitoring using Hystrix dashboard and Turbine, Security with Spring Security OAuth, Oauth2 with JWT, Aspect Oriented Programing with Spring AOP, Containers with Docker, Distributed events with Kafka, Maven Multimodule project, Event Sourcing, CQRS, REST, Web Sockets and Java 8.
 
As you can notice, we already have a lot of things in this project, but it still in development and we are planning adding more stuffs into it, such as Spring cloud config, continuous integration with Jenkins,  Distributed trace with Spring Sleuth, Logging management with ELK and more.
 
This first post is going to provide an overview about the whole project and in the coming post I will explain more deep about what and how we are using the components in each microservice.
 
![Alt text](microservices-architecture.jpg?raw=true "microservices architecture")

In the image above you can see how our system interact along with all microservices. The user will access a Web Application written using Angular 2 that will connect to an OAuth Authorization Server that will be a central point of where users and authorities can be assigned. This server will return a Json Web Token containing info about the Oauth client with its authorities and the grated scope. After the user be authenticated and with the token, the Web Application will be able to talk to the API gateway, that will take the JWT, verify if it's coming from the Authorization server then make calls to the microservices to build the response. 
The User service is being used by the Oauth server to get the user's authentication details and also it is being used by the API gateway to get User's information.
The Remainder Service is where are placed the To Do functionalities, The ToDo service has a schedule job to check for remainders and notify the user by email, The emails are sent by the Mailer Service that is triggered from Remainder service by event using Kafka.
The System Integration Test is a Java application that is reaching the Reminder service's endpoints.
 
## Connecting Microservices
In the Microservice architecture we have to deal with many microservices working in different IP and port and we will need to find a way of manage each address without hard coding and Eureka comes to rescue, it is a client side service discovery that allows services to find and communicate with each other. We are using Spring Cloud Eureka in our system and you will need to have a look at how it works to understand how our services REST are communicating between the microservices.
 
## Authentication
Security is something very important when we are developing any system and with microservice architecture is not different. The question: "How can I maintain security in my microservices" comes up immediately, and the first answer is OAuth2! And definitely OAuth2 is a very good solution, it is a well known authorization technology. It is widely used for Google, Facebook, Github at their APIs. It's impossible to talk about security and don't mention Spring security and therefore we are using Spring Cloud Security  with OAuth2.
Spring Security and OAuth2 are obvious choices when talking about secure distributed system, due to the wide use by the market, however we adding one more element to our security concert, it is JWT(Json Web Token). Using only OAuth we would need to have a OAuth Authorization server to authenticate the user, generate the token and also a endpoint  for the Resource servers to ask if the token is valid and which permission does it grant, requiring twice more request to the Authorization server than we really need. JWT provides a simple way of transmitting the permissions and user data in the access token and once the all data is already in the token string the resource servers don't need to ask for token checks. All the information is serialized into JSON first, encoded with base64 and finally signed with a private RSA key assuming that all resource servers will have a public key to check if the token was signed for the proper private key then deserialize the token to have the information.
You can check the OAuth2 Authorization server and the Resource server implementation in the OAuth-server and in the API gateway and those were done following mainly this blog post 
 
## REST
In our system we have 2 interaction styles, we have synchronous and  asynchronous, for async style we are using distributed events with Kafka following the model publish/subscribe and for synch we have REST style using Request/Response supporting JSON and XML.
 
There are 4 levels of maturity of RESTfull, starting at level 0, as described for Martin Fowler here and our services is in the level 2 because I decided not implement the Hypermedia Controls using the HATEOAS design pattern.
 
Because we are using the Spring Cloud we have out-of-box some scalability patterns are placed in our HTTP connections that need to be mentioned: Circuit breaker, Bulkheads, Load balancing, Connection pooling, timeouts and retry.

 
## Eventsourcing and CQRS
…
 
## Distributed Event
...
 
## Monitoring
...
 




## How to use

* Start al services -> sudo sh start_all.sh  
* Authenticate -> curl -X POST -vu todo-app:123456 http://localhost:8017/oauth/token -H "Accept: application/json" -d "password=1234&username=apssouza22@gmail.com&grant_type=password&scope=write&client_secret=123456&client_id=todo-app"  


* Get data using the access_token -> localhost:8018/path?access_token={access_token} or curl -H "Authorization: Bearer $TOKEN" "localhost:8018/path"

### Monitoring stream				
http://localhost:8021/turbine.stream