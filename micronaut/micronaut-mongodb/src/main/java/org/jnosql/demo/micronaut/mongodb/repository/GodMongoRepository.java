package org.jnosql.demo.micronaut.mongodb.repository;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Singleton;

import org.bson.conversions.Bson;
import org.jnosql.demo.micronaut.mongodb.model.God;
import static com.mongodb.client.model.Filters.eq;
import static org.jnosql.demo.micronaut.mongodb.repository.SubscriberHelpers.*;

@Singleton
public class GodMongoRepository implements GodRepository {
	private static final String DB_NAME = "mythology";
	private static final String COL_NAME = "gods";
	
    private final MongoClient mongoClient;

    public GodMongoRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
    
    @Override
    public List<God> findAll() {
    	List<God> result = Flowable.fromPublisher(
    			getCollection().find())
    			.toList()
    			.blockingGet();
        return result;
    }

    @Override
    public List<God> findByName(String name) {
    	final Bson filter = eq("name", name);
    	List<God> result = Flowable.fromPublisher(                
        		getCollection().find(filter))
                .toList()
                .blockingGet();
        return result;
    }    

	@Override
	public boolean save(God god) {
        return Single.fromPublisher(getCollection().insertOne(god)).blockingGet().wasAcknowledged();
	}

	@Override
	public long delete(String name) {		
        // delete one god
        Bson filter = eq("name", name);
        //ObservableSubscriber<Long> subscriber = new ObservableSubscriber<Long>();
        //return Flowable.fromPublisher(getCollection().deleteOne(filter)).blockingFirst().getDeletedCount();
        getCollection().findOneAndDelete(filter).subscribe(new PrintSubscriber<God>("Delete Result: %s"));
        return -1;
	}

	@Override
    public long countAll() {
    	return Flowable.fromPublisher(getCollection().countDocuments()).blockingFirst().longValue();
    }

    private MongoCollection<God> getCollection() {
        return mongoClient
                .getDatabase(DB_NAME)
                .getCollection(COL_NAME, God.class);
    }
}
