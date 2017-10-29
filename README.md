# Hands-on Microservices with Java

Read the post talking about this [project](https://medium.com/hands-on-microservices-with-java/hands-on-microservices-with-java-e8a5b5b022ee)
 
“Microservices Architecture” example with many concepts and technologies put in place and combined within a whole system composed with different microservices. 
Some patterns, tools and technologies that you will see in this system:

Spring Boot, Spring Data, Spring Cloud Eureka, Load Balancing with Ribbon, 
Declarative REST Clients with Feign, Software Circuit Breakers with Hystrix, 
Monitoring using Hystrix dashboard and Turbine, Administrating using Spring admin,
Log management with Elastic search, Logstash and Kibana (ELK), Server load balancing with Nginx,
Infrastructure management with Docker-compose, JMX application monitoring,
Security with Spring Security OAuth, Oauth2 with JWT, Aspect Oriented Programing, 
Distributed events with Kafka, Maven Multimodule project, Event Sourcing, 
CQRS, REST, Web Sockets, Continuous deploy with Jenkins, and all developed using Java 8.

![Alt text](microservices-architecture.jpg?raw=true "microservices architecture")


## How to use

* run package-projects.sh
* run docker-orchestrate.sh
* docker-compose -f infra-docker-compose.yml -p todo up (wait until all services be up)
* docker-compose -p todo up 

## Continuous deploy using Jenkins Pipeline
We have created a docker image in order to have continuous deploy in our project [here](https://github.com/apssouza22/build-deploy).

This image will contain all necessary to build our project, create the Docker images and 
deploy on AWS using ECS containers. 

To make this integration easy, we have added the `Jenkinsfile` with the steps necessary to have
the Docker image built. To use it, you will need just to configure a Job on Jenkins using Pipeline plugin
and paste the content of the Jenkinsfile in the Pipeline script box. Have a look at this 
[video](https://www.youtube.com/watch?v=u3xLXEnlu2M&t=1023s&index=2&list=PLoO1q0-ZB3v6ZN6qvk0dsRRuxjiAQDuZx)
to check how to work with Jenkins pipeline

### Accessing the services
* Authenticate -> curl -X POST -vu todo-app:123456 http://localhost:8017/oauth/token -H "Accept: application/json" -d "password=1234&username=apssouza22@gmail.com&grant_type=password&scope=write&client_secret=123456&client_id=todo-app"  

* Get data using the access_token -> localhost:8018/accounts?access_token={access_token} or curl -H "Authorization: Bearer $TOKEN" "localhost:8018/path"

### URLs
Monitoring stream - http://localhost:8022/turbine.stream

To-dos http://localhost:8015/todos

Users http://localhost:8016/accounts 

Eureka server - http://localhost:8010/

Config server - http://localhost:8888/

Boot admin - http://localhost:8026

Kimbana - http://localhost:5601

Elasticsearch Info: http://localhost:9200

Elasticsearch Status: http://localhost:9200/_status?pretty

NGINX Status: localhost/nginx_status

docker-compose -p todo up
docker-compose -p todo down

## OBS
* In order to make ELK work we need to reserve 3GB RAM to docker(docker settings - advanced - memory )
* Have a look at the Readme of each service/ module to see the explanation about it.
* On Kimbana create a filter called `filebeat-*` to see the logs

## Useful Commands

### Creating to-do via Curl
```
curl -d '{"userEmail":"alex@test.com", "caption":"post caption", "description":"desc", "priority": 1}' -H "Content-Type: application/json" -X POST http://localhost:8015/todos
```

### Stopping, Starting, Restarting...

```
# running separated container
docker run -d -p 8026:8026  --network todo_net --add-host eureka:172.19.0.3 todo/admin-server

# orchestrate start-up of containers, tailing the logs...
docker-compose -p music up -d container-name && docker logs elk --follow # ^C to break

# stopping containers
docker-compose todo stop
docker-compose todo down

# starting containers
docker-compose -p todo up

# removing containers
docker-compose todo rm

```

### Application Startup Issues

```bash
# stop / start Tomcat
docker exec -it container-name sh /usr/local/tomcat/bin/startup.sh
docker exec -it container-name sh /usr/local/tomcat/bin/shutdown.sh

# check logs for start-up issues...
docker exec -it container-name cat /usr/local/tomcat/logs/catalina.out
docker logs container-name
```

### Kafka
```
# event consume
/opt/kafka/bin/kafka-console-consumer.sh --zookeeper zookeeper:2181 --topic todo-mail --from-beginning

# producer console
/opt/kafka/bin/kafka-console-producer.sh --broker-list kafka:9092 --topic todo-mail

# Listing topics
/opt/kafka/bin/kafka-topics.sh --list --zookeeper zookeeper:2181
```

## TODO
* Integrate mail service to reminder-service
* Integrate turbine in the Admin dashboard
* Added a readme for each microservices and modules
* Test the integration between the Filebeat and ELK