package org.jnosql.demo.endgame.jakarta;

import jakarta.nosql.mapping.column.ColumnTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class ColumnApp2 {

    public static void main(String[] args) throws InterruptedException {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {

            God hunter = new God(1L, "Ullr", "Hunting");

            ColumnTemplate template =
                    container.select(ColumnTemplate.class)
                            .get();

            template.insert(hunter, Duration.ofSeconds(1L));

            final Optional<God> god = template.find(God.class, 1L);
            System.out.println("query : " + god);
            TimeUnit.SECONDS.sleep(2L);

            System.out.println("query again: " +
                    template.find(God.class, 1L));
        }
        System.exit(0);
    }
}
