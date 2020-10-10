package org.jnosql.demo.endgame.jakarta;

import org.eclipse.jnosql.artemis.DatabaseQualifier;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class ColumnApp5 {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {

            God hunter = new God(1L, "Ullr", "Hunt");

            GodRepository repository =
                    container.select(GodRepository.class, DatabaseQualifier.ofColumn())
                            .get();

            repository.save(hunter);

            System.out.println("Query by name : " + repository.findByName("Ullr"));

        }
        System.exit(0);
    }
}
