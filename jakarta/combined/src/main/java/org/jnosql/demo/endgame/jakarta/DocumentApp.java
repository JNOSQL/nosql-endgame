package org.jnosql.demo.endgame.jakarta;

import jakarta.nosql.document.DocumentDeleteQuery;
import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;

import static jakarta.nosql.document.DocumentDeleteQuery.delete;

public class DocumentApp {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {

            God hunter = new God(1L, "Ullr", "Hunting");

            DocumentTemplate template =  container.select(DocumentTemplate.class)
                            .get();

            template.insert(hunter);
            final Optional<God> god = template.find(God.class, 1L);
            System.out.println("query : " + god);

            DocumentDeleteQuery deleteQuery = delete().from("God")
                    .where("_id").eq(1L).build();

            template.delete(deleteQuery);

            System.out.println("query again: " +
                    template.find(God.class, 1L));
        }

        System.exit(0);
    }
}
