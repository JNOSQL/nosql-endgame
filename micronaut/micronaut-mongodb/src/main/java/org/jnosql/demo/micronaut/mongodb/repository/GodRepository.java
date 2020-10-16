package org.jnosql.demo.micronaut.mongodb.repository;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Singleton;

import org.jnosql.demo.micronaut.mongodb.model.God;

import static com.mongodb.client.model.Filters.eq;

@Singleton
public class GodRepository {

    private final MongoClient mongoClient;

    public GodRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public List<God> findByName(String name) {
        return Flowable.fromPublisher(
                getCollection().find(eq(God.DISCRIMINATOR_KEY, name)))
                .toList()
                .blockingGet();
    }

    public God insert(God god) {
        Single.fromPublisher(getCollection().insertOne(god)).blockingGet();
        return god;
    }

    private MongoCollection<God> getCollection() {
        return mongoClient
                .getDatabase("mythology")
                .getCollection("gods", God.class);
    }
}
