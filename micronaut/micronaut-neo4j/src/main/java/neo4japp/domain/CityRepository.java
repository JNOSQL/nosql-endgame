package neo4japp.domain;

import java.util.*;
import java.util.stream.*;

public interface CityRepository {
	void save(City c);
	Stream<City> findByName(String name);
}
