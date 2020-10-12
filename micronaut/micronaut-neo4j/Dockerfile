FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/neo4japp*.jar neo4japp.jar
CMD java ${JAVA_OPTS} -jar neo4japp.jar