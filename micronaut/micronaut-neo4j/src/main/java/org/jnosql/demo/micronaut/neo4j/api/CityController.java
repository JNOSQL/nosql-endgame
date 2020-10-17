package org.jnosql.demo.micronaut.neo4j.api;

import java.util.stream.Stream;

import javax.inject.Inject;

import org.jnosql.demo.micronaut.neo4j.domain.City;
import org.jnosql.demo.micronaut.neo4j.domain.CityRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;

@Controller("/api/city")
public class CityController {

	@Inject
	CityRepository repo;
	
    @SuppressWarnings("rawtypes")
	@Post("/new")
    public HttpResponse create(@QueryValue(value = "name") String name, @QueryValue(value = "id") Long id) {
    	//System.out.println("Name=" + name + " Country=" + country); TODO change to logging    	
    	//System.out.println(cities + " cities."); TODO change to logging
    	final City city = new City();
    	city.name = name;
    	if (id!=null) {
    		city.id = id;
    	} else {
    		final long cities = repo.countAll();
    		city.id = cities+1;
    	}
    	repo.save(city);
    	return HttpResponse.created(city);
    }
    
    @Get("/named/{name}")
    public Stream<City> cities(String name) {
        return repo.findByName(name);
    }
    
    @SuppressWarnings("rawtypes")
	@Delete("/delete/{name}")
    public HttpResponse delete(String name) {
    	Stream<City> cities = repo.findByName(name);
    	City c = cities.findFirst().get();
    	int i = 0;
    	if (c != null) {
    		i = repo.delete(c);
    	}
        if (i>0) {
        	return HttpResponse.ok(c);
        } else {
        	return HttpResponse.noContent();
        }
    }
    
    @Get("/count")
    public long count() {
        return repo.countAll();
    }
    
}