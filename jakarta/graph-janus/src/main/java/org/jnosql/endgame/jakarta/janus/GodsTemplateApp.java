/*
 * Copyright (c) 2017 Otávio Santana and others
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
 * Otavio Santana
 * Werner Keil
 */
package org.jnosql.endgame.jakarta.janus;

import org.eclipse.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.endgame.jakarta.janus.model.God;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Optional;

import static org.janusgraph.core.attribute.Geo.geoWithin;
import static org.janusgraph.core.attribute.Geoshape.circle;

public class GodsTemplateApp {

    private GodsTemplateApp() {
    }

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            GraphTemplate template = container.select(GraphTemplate.class).get();
            Optional<God> farmer = template.getTraversalVertex().has("name", "saturn").next();
            System.out.println(farmer);
            template.getTraversalEdge().has("place", geoWithin(circle(37.97, 23.72, 50)))
                    .stream().forEach(System.out::println);

            template.getTraversalVertex().getResult().forEach(System.out::println);
        }
    }
}
