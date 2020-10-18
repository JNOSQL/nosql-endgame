package org.jnosql.demo.micronaut.neo4j.domain;

import java.util.*;

public class City {
  public long id;
  public String name;
  public String country;
  public Double lon, lat;
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public Map asMap() {
     Map result = new HashMap();
     result.put("id",id);
     result.put("name",name);
     result.put("country",country);
     result.put("lat",lat);
     result.put("lon",lon);
     return result;
  }
  public City() {}
  @SuppressWarnings("rawtypes")
  public City(Map data) {
	final Long newId = (Long)data.get("id");	
	this.id = (newId != null) ? newId : -1;	
	this.name = (String)data.get("name");
	this.country = (String)data.get("country");
	this.lat = (Double)data.get("lat");	
	this.lon = (Double)data.get("lon");	 
  }
}

class CityResult {
    public List<City> results;
}
