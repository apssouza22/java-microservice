#!/bin/sh

set -ex

mvn clean package 
mvn -f ./remainder-service/pom.xml clean package 
mvn -f ./eureka-server/pom.xml clean package 
mvn -f ./oauth-server/pom.xml clean package 
mvn -f ./user-service/pom.xml clean package 
mvn -f ./api-gateway/pom.xml clean package 
mvn -f ./mail-service/pom.xml clean package 
mvn -f ./turbine-monitoring/pom.xml clean package 
mvn -f ./admin-server/pom.xml clean package 
