package org.jnosql.demo.micronaut.mongodb.animals;

import java.util.Objects;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator(key = Animal.DISCRIMINATOR_KEY)
public abstract class Animal {
    public static final String DISCRIMINATOR_KEY = "type";

    private String type;

    public Animal(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(type, animal.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{");
        sb.append("type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
