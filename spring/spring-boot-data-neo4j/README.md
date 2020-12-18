### Local setup

#### 1. Setup Neo4J database

Neo4J comes with a preconfigured movies database which is used in the examples of this talk. To set up your Neo4J database locally without bothering too much about versioning, you can use Docker.   

- Download Docker
- Start Docker locally
- Initiate a Neo4J instance:

```shell
docker run --publish=7474:7474 --publish=7687:7687 -e 'NEO4J_AUTH=neo4j/secret'  neo4j:4.0.3
```

- Navigate to localhost:7474
- Initiate the movies database using the preset visual queries

#### 2. Run the Spring Boot application
Build the project and run the main class `SprintBootDataNeo4jApplication.java`

#### 3. Query the database
You can query the database using Postman. You can find the postman queries used for this talk under the `resources` folder of this project.
