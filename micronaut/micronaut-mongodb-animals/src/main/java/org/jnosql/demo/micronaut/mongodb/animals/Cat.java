package org.jnosql.demo.micronaut.mongodb.animals;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator(value = Cat.TYPE, key = Animal.DISCRIMINATOR_KEY)
public class Cat extends Animal {
    public static final String TYPE = "some_cat_type";

    public Cat() {
        super(TYPE);
    }


}
