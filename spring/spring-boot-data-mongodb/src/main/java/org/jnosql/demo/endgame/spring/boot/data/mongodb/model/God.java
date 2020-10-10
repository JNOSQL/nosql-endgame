package org.jnosql.demo.endgame.spring.boot.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gods")
public class God {

    @Id
    private String id;

    private String name;
    private String power;

    public God() {

    }

    public God(String name, String power) {
        this.name = name;
        this.power = power;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPower() {
        return power;
    }


    @Override
    public String toString() {
        return "God [id=" + id + ", name=" + name + ", power=" + power + "]";
    }
}
