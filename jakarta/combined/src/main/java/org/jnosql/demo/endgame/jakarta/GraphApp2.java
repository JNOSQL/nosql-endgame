package org.jnosql.demo.endgame.jakarta;

import org.eclipse.jnosql.artemis.graph.GraphTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public class GraphApp2 {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {


            GraphTemplate template =
                    container.select(GraphTemplate.class)
                            .get();

            God ullr = template.getTraversalVertex()
                    .hasLabel(God.class)
                    .has("name", "Ullr")
                    .<God>next()
                    .orElseGet(() ->
                            template.insert(new God(null, "Ullr", "Hunting")));

            God magni = template.getTraversalVertex()
                    .hasLabel(God.class)
                    .has("name", "Magni")
                    .<God>next()
                    .orElseGet(() ->
                            template.insert(new God(null, "Magni", "Strength")));

            God thor = template.getTraversalVertex()
                    .hasLabel(God.class)
                    .has("name", "Thor")
                    .<God>next()
                    .orElseGet(() ->
                            template.insert(new God(null, "Thor", "Thunder")));


            template.edge(ullr, "stepbrother", magni);
            template.edge(magni, "stepbrother", ullr);

            template.edge(thor, "father", magni);
            template.edge(thor, "stepfather", ullr);


            template.delete(ullr.getId());
            template.delete(thor.getId());
            template.delete(magni.getId());


        }

        System.exit(0);
    }
}