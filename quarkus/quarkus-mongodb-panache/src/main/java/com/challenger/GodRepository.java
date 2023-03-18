package com.challenger;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GodRepository implements PanacheMongoRepository<God> {

  public God findByName(String name){
    return find("name", name).firstResult();
  }

  public List<God> findOrderedByName(){
    return findAll(Sort.by("name")).list();
  }

}
