#!/bin/sh

set -ex

mvn clean install
mvn -f ./remainder-service/pom.xml -Pdockerimage docker:build 
mvn -f ./eureka-server/pom.xml  -Pdockerimage docker:build 
mvn -f ./config-server/pom.xml  -Pdockerimage docker:build 
mvn -f ./oauth-server/pom.xml  -Pdockerimage docker:build 
mvn -f ./user-service/pom.xml  -Pdockerimage docker:build 
mvn -f ./api-gateway/pom.xml  -Pdockerimage docker:build 
mvn -f ./admin-server/pom.xml  -Pdockerimage docker:build 
mvn -f ./mail-service/pom.xml  -Pdockerimage docker:build
