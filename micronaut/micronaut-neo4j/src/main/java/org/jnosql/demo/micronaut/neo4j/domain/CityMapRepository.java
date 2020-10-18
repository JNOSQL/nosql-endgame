package org.jnosql.demo.micronaut.neo4j.domain;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

@Singleton
public class CityMapRepository implements CityRepository {
   private final Map<Long,City> data = new ConcurrentHashMap<>();

   public void save(City c) {
      data.putIfAbsent(c.id, c);
   } 
   public Stream<City> findByName(String name) {
      return data.values().stream().filter(c -> c.name.contains(name));
   }
   
   public int delete(String name) {
	   //TODO make this nicer with lambda
	   int i = 0;
	   if (name != null) {
		   for (City city : data.values())  {
			   if (name.equals(city.name)) {
				@SuppressWarnings("unlikely-arg-type")
				City removed = data.remove(city);
				   if (removed != null) i++;				   
			   }	   
		   }
	   }
	   return i;
   }
   
	@Override
	public long countAll() {
		return data.size();
	}
}