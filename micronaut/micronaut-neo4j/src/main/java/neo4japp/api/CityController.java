package neo4japp.api;

import java.util.stream.Stream;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import neo4japp.domain.City;
import neo4japp.domain.CityRepository;

@Controller("/api/city")
public class CityController {

	@Inject
	CityRepository repo;
	
//    @Get("/")
//    public HttpResponse home()
//    {
//            return HttpResponse.ok(); 
//    }
	
    @Post("/new")
    public HttpResponse createNewCity(@QueryValue(value = "name") String name, @QueryValue(value = "country") String country) {
    	System.out.println("Name=" + name + " Country=" + country);
    	final City city = new City();
    	city.name = name;
    	city.country = country;
    	repo.save(city);
    	return HttpResponse.ok();
    }
    
    @Get("/named/{name}")
    public Stream<City> cities(String name) {
        return repo.findByName(name);
    }
    
}