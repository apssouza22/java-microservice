#!/bin/bash

echo "Starting all services..."

echo "Starting Eureka server, port 8010..."
java -jar ./eureka-server/target/commmon-eureka-server-1.jar >> /var/log/microservice/eureka-server.log &

echo "Starting Oauth server, port 8017..."
java -jar ../todo-ms/oauth-server/target/oauth2-server-0.0.1-SNAPSHOT.jar >> /var/log/microservice/oauth-server.log &

echo "Starting remainder service, port 8015..."
java -jar ../todo-ms/remainder-service/target/remainder-service-0.0.1-SNAPSHOT.jar >> /var/log/microservice/remainder-service.log &

echo "Starting user service, port 8016..."
java -jar ../todo-ms/user-service/target/user-service-0.0.1-SNAPSHOT.jar >> /var/log/microservice/user-service.log &

echo "Starting aggregate services, port 8018..."
java -jar ../todo-ms/aggregate-services/target/aggregate-service-0.0.1-SNAPSHOT.jar >> /var/log/microservice/aggregate-service.log &
