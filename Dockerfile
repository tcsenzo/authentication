# Set the base image to java8
FROM vixns/java8

MAINTAINER Leonardo Wolter <leocwolter@gmail.com>

# Define environment variables
ENV AUTHENTICATION_HOME=/opt/authentication

# Add artifacts
ADD build/authentication.jar $AUTHENTICATION_HOME/authentication.jar
ADD build/application.properties $AUTHENTICATION_HOME/application.properties
ADD run.sh $AUTHENTICATION_HOME/run.sh

# Main command
ENTRYPOINT $AUTHENTICATION_HOME/run.sh