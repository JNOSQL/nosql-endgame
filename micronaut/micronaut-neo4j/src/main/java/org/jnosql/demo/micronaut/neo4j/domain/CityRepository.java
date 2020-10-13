package org.jnosql.demo.micronaut.neo4j.domain;

import java.util.stream.*;

public interface CityRepository {
	void save(City c);
	Stream<City> findByName(String name);
	int delete(City c);
	long countAll();
}
