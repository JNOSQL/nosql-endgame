package org.jnosql.demo.micronaut.mongodb.animals;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Singleton;
import static com.mongodb.client.model.Filters.eq;

@Singleton
public class AnimalRepository {

    private final MongoClient mongoClient;

    public AnimalRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public List<Animal> findByType(String type) {
        return Flowable.fromPublisher(
                getCollection().find(eq(Animal.DISCRIMINATOR_KEY, type)))
                .toList()
                .blockingGet();
    }

    public Animal insert(Animal animal) {
        Single.fromPublisher(getCollection().insertOne(animal)).blockingGet();
        return animal;
    }

    private MongoCollection<Animal> getCollection() {
        return mongoClient
                .getDatabase("animaldb")
                .getCollection("animal", Animal.class);
    }


}
