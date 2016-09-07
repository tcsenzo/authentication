#!/bin/bash

# Defaults
AUTHENTICATION_HEAP_OPTS=${AUTHENTICATION_HEAP_OPTS:--Xms512m -Xmx512m -XX:MaxMetaspaceSize=256m}

exec java -Dfile.encoding=UTF-8 $AUTHENTICATION_HEAP_OPTS -jar $AUTHENTICATION_HOME/authentication.jar --spring.config.location=file:///opt/authentication/application.properties