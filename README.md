# Hands-on Microservices with Java

Read the post talking about this project https://medium.com/hands-on-microservices-with-java/hands-on-microservices-with-java-e8a5b5b022ee
 
“Microservices Architecture” example with many concepts and technologies put in place and combined within a whole system composed with different microservices. I’m not planning to go deep in the concepts and tools, we have a lot of posts about those out there, the intention here is to present an application example containing patterns, tools, technologies used to develop microservices.

This is a studying application and we have intentionally made it as simple as possible to make the source code easy to understand. The application will be available and ready to run on your computer for use as a reference.

We are going to work with a "To Do" application that will be composed of 8 applications (Reminder, User, Service discovery server, Mailer, OAuth Server, System integration test, API Gateway, Web application client).

Some patterns, tools and technologies that you will see in this system:

Spring Boot, Spring Data, Spring Cloud Eureka, Load Balancing with Ribbon, Declarative REST Clients with Feign, Software Circuit Breakers with Hystrix, Monitoring using Hystrix dashboard and Turbine, Security with Spring Security OAuth, Oauth2 with JWT, Aspect Oriented Programing with Spring AOP, Distributed events with Kafka, Maven Multimodule project, Event Sourcing, CQRS, REST, Web Sockets and all developed using Java 8.


![Alt text](microservices-architecture.jpg?raw=true "microservices architecture")


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

docker-compose -p todo up
docker-compose -p todo down