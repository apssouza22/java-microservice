#Todo microservice experience

The term "Microservices Architecture" is now a popular term and in order to keep updated as a software developer I've been putting some effort to get a good understand about this architecture and the better way of implement it in Java using Spring technologies. 
 
I was working in a nice company with a great team and a good tech stack, however we were not using the cutting edge Java features such Java 8 and Microservices architectures yet, so I had to start to looking at those knowledge outside the company and by myself. 
 
The intention of this serie is give an Idea of many concepts and technologies put in place and combined in a whole system composed with differents microservices. This will not go deep in the concepts and tools, the intention here is present you some components that can be used when you are developing microservices. The most important is that you will have all the patterns, tools, technologies working together ready to run in your computer.
 
We are going to work with a To Do that will be composed with 8 microservices (Reminder, User, Service discovery server, Mailer, Oauth Server, System integration test, Api gateway, Web application client). 
 
Some patterns, tools and technologies you will see in this system: 
Spring Boot, Spring Data, Spring Cloud Eureka, Load Balancing with Ribbon, Declarative REST Clients with Feign, Software Circuit Breakers with Hystrix, Api Gateway with Spring Cloud Zuul, Security with Spring Security OAuth, Oauth2 with JWT, Aspect Oriented Programing with Spring AOP, Containers with Docker, Distributed events with Kafka, Maven Multimodule project, Event Sourcing, CQRS, REST, Web Sockets, Java 8.
 
As you can notice, we already have a lot of things in this project, but it still in development and we are planning adding more stuffs into it, such as Spring cloud config, continuous integration with Jenkins,  Distributed trace with Spring Sleuth, Logging management with ELK and more.
 
This first post is going to provide an overview about the whole project and in the coming post I will explain more deep about what and how we are using things and in each microservice.
 
![Alt text](microservices-architecture.jpg?raw=true "microservices architecture")

In the image above you can see how our system interact along with all microservices. The user will access a web application written using Angular 2 that will connect to an OAuth Authorization Server that will be a central point of where users and authorities can be assigned. This server will return a Json Web Token containing info about the Oauth client with its authorities and the grated scope. After the user be authenticated and with the token, the Web Application will be able to talk to the API gateway, that will take the JWT, verify if it's coming from the Authorization server then make calls to the microservices to build the response. 
The User service is being used by the Oauth server to get the user's authentication details and also it is being used by the API gateway to get User's information.
The Remainder Service is where are placed the To Do functionalities, The ToDo service has a schedule job to check for remainders and try to notify the user by email, The emails are sent by the Mailer Service that is triggered by event using Kafka.
The System Integration Test is a Java application that is reaching the Reminder service's endpoint.




#How to use

Start al services -> sudo sh start_all.sh
Authenticate -> curl -X POST -vu todo-app:123456 http://localhost:8017/oauth/token -H "Accept: application/json" -d "password=1234&username=apssouza22@gmail.com&grant_type=password&scope=write&client_secret=123456&client_id=todo-app"

Get data using the access_token -> localhost:8018/path?access_token={access_token} or curl -H "Authorization: Bearer $TOKEN" "localhost:9090/foo"

## Monitoring 					
http://localhost:8021/turbine.stream