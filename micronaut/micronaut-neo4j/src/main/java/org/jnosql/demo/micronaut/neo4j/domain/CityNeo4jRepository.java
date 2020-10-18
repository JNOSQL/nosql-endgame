/*
 * Copyright (c) 2020 Werner Keil and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Werner Keil
 */
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
          return s.readTransaction(tx -> tx.run(statement, singletonMap("name", name)))
                  .list(record -> new City(record.get("c").asMap())).stream();
      }
   }
   
   public int delete(String name) {
	   try (Session s = driver.session()) {
		  String statement = "MATCH (c:City) WHERE c.name contains $name DELETE c";
		  StatementResult r = s.run(statement, singletonMap("name", name));		  
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