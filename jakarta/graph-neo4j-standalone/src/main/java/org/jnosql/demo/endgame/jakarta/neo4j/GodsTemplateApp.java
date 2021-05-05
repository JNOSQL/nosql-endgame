/*
 * Copyright (c) 2020, 2021 Werner Keil and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Werner Keil
 */
package org.jnosql.demo.endgame.jakarta.neo4j;

import org.eclipse.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.demo.endgame.jakarta.neo4j.model.God;

import java.util.Optional;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

/**
 * Demo application showing the use of the {@link GraphTemplate} on top of a Neo4j system.
 * @author Werner Keil
 */
public class GodsTemplateApp {

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

            God odin = template.getTraversalVertex()
                    .hasLabel(God.class)
                    .has("name", "Odin")
                    .<God>next()
                    .orElseGet(() ->
                            template.insert(new God(null, "Odin", "Creation")));
            

            template.edge(ullr, "stepbrother", magni);
            template.edge(magni, "stepbrother", ullr);

            template.edge(thor, "father", magni);
            template.edge(thor, "stepfather", ullr);
            template.edge(odin, "father", thor);
            
            Optional<God> god = template.getTraversalVertex(ullr.getId()).next();

            System.out.println("Query: " + god);
        }

        System.exit(0);
    }
}