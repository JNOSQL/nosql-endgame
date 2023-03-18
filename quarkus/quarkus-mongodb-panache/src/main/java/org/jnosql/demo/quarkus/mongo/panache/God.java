package org.jnosql.demo.quarkus.mongo.panache;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection="gods")
public class God extends PanacheMongoEntity {

  public String name;
  public String power;

  public God() {

  }

  public God(String name, String power) {
    this.name = name;
    this.power = power;
  }
}
