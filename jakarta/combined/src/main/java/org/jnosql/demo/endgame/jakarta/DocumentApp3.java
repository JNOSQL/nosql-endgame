package org.jnosql.demo.endgame.jakarta;

import org.eclipse.jnosql.artemis.DatabaseQualifier;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;

public class DocumentApp3 {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {

            God hunter = new God(1L, "Ullr", "Hunt");

            GodRepository repository =
                    container.select(GodRepository.class, DatabaseQualifier.ofDocument())
                            .get();

            repository.save(hunter);

            Optional<God> god = repository.findById(1L);
            System.out.println("Query by id : " + god);
            System.out.println("Query by Name : " + repository.findByName("Ullr"));

            repository.deleteById(1L);

        }

        System.exit(0);
    }
}
