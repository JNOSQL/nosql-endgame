package org.jnosql.demo.endgame.jakarta;

import org.eclipse.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;

public class GraphApp {

    public static void main(String[] args)  {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {


            GraphTemplate template =
                    container.select(GraphTemplate.class)
                            .get();

            God hunter = template.getTraversalVertex()
                    .hasLabel(God.class)
                    .has("name", "Ullr")
                    .<God>next()
                    .orElseGet(() ->
                            template.insert(new God(null, "Ullr", "Hunting")));


            System.out.println("query : " + hunter);

            template.delete(hunter.getId());

            Optional<God> god = template.getTraversalVertex(hunter.getId()).next();

            System.out.println("Query: " + god);

        }

        System.exit(0);
    }
}