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
    public HttpResponse create(@QueryValue(value = "name") String name, @QueryValue(value = "country") String country) {
    	//System.out.println("Name=" + name + " Country=" + country); TODO change to logging
    	final long cities = repo.countAll();
    	//System.out.println(cities + " cities."); TODO change to logging
    	final City city = new City();
    	city.id = cities+1;
    	city.name = name;
    	city.country = country;
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
    	int i = 0;
    	if (name != null) {
    		i = repo.delete(name);
    	}
        if (i>0) {
        	return HttpResponse.ok(String.format("%s Has Fallen", name));
        } else {
        	return HttpResponse.noContent();
        }
    }
    
    @Get("/count")
    public long count() {
        return repo.countAll();
    }
    
}