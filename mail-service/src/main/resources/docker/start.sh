#!/bin/bash

curl -XPUT 'http://elk:9200/_template/filebeat?pretty' -d@/etc/filebeat/filebeat.template.json
/etc/init.d/filebeat start
java -Djava.security.egd=file:/dev/./urandom -jar -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n  /app.jar
