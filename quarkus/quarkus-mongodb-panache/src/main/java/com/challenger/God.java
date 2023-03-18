package com.challenger;

import io.quarkus.mongodb.panache.PanacheMongoEntity;

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
