package org.jnosql.demo.endgame.jakarta;

import org.eclipse.jnosql.artemis.DatabaseQualifier;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;

public class KeyValueApp4 {

    public static void main(String[] args) throws InterruptedException {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {

            God hunter = new God(1L, "Ullr", "Hunt");

            GodRepository repository =
                    container.select(GodRepository.class
                    , DatabaseQualifier.ofKeyValue())
                            .get();

            repository.save(hunter);
            Optional<God> result = repository.findById(1L);
            System.out.println("Find by id: " + result);

        }

        System.exit(0);

    }
}
