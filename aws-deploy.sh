#!/bin/bash
set -ex

loginString="$(aws ecr get-login)"
${loginString/-e none/ }
REPOSITORY_URI=${REPOSITORY_URI}

docker tag todo/oauth-server:latest ${REPOSITORY_URI}:oauth-server
docker push ${REPOSITORY_URI}:oauth-server

docker tag todo/user-service:latest ${REPOSITORY_URI}:user-service 
docker push ${REPOSITORY_URI}:user-service

docker tag todo/api-gateway:latest ${REPOSITORY_URI}:api-gateway 
docker push ${REPOSITORY_URI}:api-gateway

docker tag todo/config-server:latest ${REPOSITORY_URI}:config-server 
docker push ${REPOSITORY_URI}:config-server


docker tag todo/eureka-server:latest ${REPOSITORY_URI}:eureka-server 
docker push ${REPOSITORY_URI}:eureka-server

docker tag todo/reminder-service:latest ${REPOSITORY_URI}:reminder-service 
docker push ${REPOSITORY_URI}:reminder-service

docker tag todo/admin-server:latest ${REPOSITORY_URI}:admin-server
docker push ${REPOSITORY_URI}:admin-server


docker tag todo/mail-service:latest ${REPOSITORY_URI}:mail-service
docker push ${REPOSITORY_URI}:mail-service

ecs-cli compose --verbose --file ./aws-compose.yml up



