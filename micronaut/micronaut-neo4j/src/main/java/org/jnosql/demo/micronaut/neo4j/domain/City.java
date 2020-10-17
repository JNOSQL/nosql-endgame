package org.jnosql.demo.micronaut.neo4j.domain;

import java.util.*;

public class City {
  public long id;
  public String name;
  
@SuppressWarnings({ "rawtypes", "unchecked" })
public Map asMap() {
     Map result = new HashMap();
     result.put("id",id);
     result.put("name",name);
     return result;
  }
  public City() {}
  @SuppressWarnings("rawtypes")
public City(Map data) {
	this.id = (Long)data.get("id");
	this.name = (String)data.get("name");
  }
}

class CityResult {
    public List<City> results;
}
