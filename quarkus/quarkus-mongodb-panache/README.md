# quarkus-mongodb-panache project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

It also shows how easy we can create a microservice manipulating database data very easily as well. 

## Running the application in dev mode

To run a MongoDB session, run the `docker-compose.yml` file from the root of this project 
with the following command:
`docker-compose up`

Then run the Quarkus application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```
## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.
