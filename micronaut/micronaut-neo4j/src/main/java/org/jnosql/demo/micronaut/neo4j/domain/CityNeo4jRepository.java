package org.jnosql.demo.micronaut.neo4j.domain;

import javax.inject.*;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.StatementResult;

import static java.util.Collections.*;

import java.util.List;
import java.util.stream.*;
import io.micronaut.context.annotation.*;

@Primary
@Singleton
public class CityNeo4jRepository implements CityRepository {
   @Inject Driver driver;

   public void save(City city) {
      try (Session s = driver.session()) {
	      String statement = "MERGE (c:City {id:$name.id}) ON CREATE SET c+=$name";
          s.writeTransaction(tx -> tx.run(statement, singletonMap("name", city.asMap())));
      }
   }

   public Stream<City> findByName(String name) {
      try (Session s = driver.session()) {
	      String statement = "MATCH (c:City) WHERE c.name contains $name RETURN c";
          return s.readTransaction(tx -> tx.run(statement, singletonMap("name",name)))
                  .list(record -> new City(record.get("c").asMap())).stream();
      }
   }
   
   public int delete(City city) {
	   try (Session s = driver.session()) {
		  String statement = "MATCH (c:City) WHERE c.name contains $name DELETE c";
		  StatementResult r = s.run(statement);		  
		  return r.summary().counters().nodesDeleted();
	   }
   }

	@Override
	public long countAll() {
		try (Session s = driver.session()) {
			  String statement = "MATCH (c:City) RETURN count(c) as count";
			  StatementResult r = s.run(statement);
			  List<Value> v = r.single().values();
			  return v.size() > 0 ? v.get(0).asLong() : 0;
		   }
	}
}