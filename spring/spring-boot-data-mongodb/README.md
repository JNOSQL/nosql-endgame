### Local setup

#### 1. Setup the MongoDB database

- Make sure you have installed MongoDB Community v.4.4 (or higher) locally
- Start the service (e.g. on Mac using _brew_ : `brew services start mongodb-community@4.4`)
- Connect to the service: `mongo`
- Create a new database: `use mythology`
- Add some dummy data: 

```js
db.gods.insertMany([{ "_id" : ObjectId("5f789b086997ea37498ed9a2"), "name" : "Zeus", "power" : "justice" },{ "_id" : ObjectId("5f789b316997ea37498ed9a3"), "name" : "Hera", "power" : "childbirth", "_class" : "org.jnosql.demo.endgame.spring.data.God" },{ "_id" : ObjectId("5f789b806997ea37498ed9a4"), "name" : "Poseidon", "power" : "hurricane" },{ "_id" : ObjectId("5f789b926997ea37498ed9a5"), "name" : "Demeter", "power" : "harvest" }, { "_id" : ObjectId("5f789ba16997ea37498ed9a6"), "name" : "Athena", "power" : "wisdom" },{ "_id" : ObjectId("5f789bb16997ea37498ed9a7"), "name" : "Apollo", "power" : "light" },{ "_id" : ObjectId("5f78a318a8577a08354131b8"), "name" : "John Doe", "_class" : "org.jnosql.demo.endgame.spring.data.God" },{ "_id" : ObjectId("5f78a359a8577a08354131b9"), "name" : "Sally Doe", "_class" : "org.jnosql.demo.endgame.spring.data.God" }])`
```

#### 2. Run the Spring Boot application
Build the project and run the main class `SprintBootDataMongodbApplication.java`

#### 3. Query the database
You can query the database using Postman. You can find the postman queries used for this talk under the `resources` folder of this project.
