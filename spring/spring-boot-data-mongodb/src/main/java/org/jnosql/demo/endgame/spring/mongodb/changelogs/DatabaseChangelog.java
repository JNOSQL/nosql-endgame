package org.jnosql.demo.endgame.spring.mongodb.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.jnosql.demo.endgame.spring.mongodb.model.God;

import java.util.ArrayList;
import java.util.List;


@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "insertTestDataToDatabase", author = "Tegridy")
    public void initData(MongoDatabase db){

        List<Document> gods = new ArrayList<>();
        gods.add(createMongoDocument(new God("Zeus", "justice")));
        gods.add(createMongoDocument(new God("Hera", "childbirth")));
        gods.add(createMongoDocument(new God("Poseidon", "hurricane")));
        gods.add(createMongoDocument(new God("Demeter", "harvest")));
        gods.add(createMongoDocument(new God("Athena", "wisdom")));
        gods.add(createMongoDocument(new God("Apollo", "light")));
        gods.add(createMongoDocument(new God("John Doe", "no power")));
        gods.add(createMongoDocument(new God("Sally Doe", "no power")));

        db.getCollection("gods").insertMany(gods);
    }

    private Document createMongoDocument(God god) {
        return new Document()
                .append("name", god.getName())
                .append("power", god.getPower());
    }
}
