FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/org.jnosql.demo.micronaut.neo4j*.jar org.jnosql.demo.micronaut.neo4j.jar
CMD java ${JAVA_OPTS} -jar org.jnosql.demo.micronaut.neo4j.jar