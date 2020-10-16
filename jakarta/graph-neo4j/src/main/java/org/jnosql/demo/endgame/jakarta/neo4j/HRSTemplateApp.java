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

package org.jnosql.demo.endgame.jakarta.neo4j;

import org.eclipse.jnosql.artemis.graph.GraphTemplate;
import org.jnosql.demo.endgame.jakarta.neo4j.model.Person;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.tinkerpop.gremlin.process.traversal.P.between;
import static org.apache.tinkerpop.gremlin.process.traversal.P.gte;
import static org.jnosql.demo.endgame.jakarta.neo4j.model.Person.builder;

public final class HRSTemplateApp {

    private HRSTemplateApp() {
    }

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            GraphTemplate template = container.select(GraphTemplate.class).get();

            Person bruce = template.getTraversalVertex()
                    .hasLabel(Person.class)
                    .has("name", "Bruce")
                    .<Person>next()
                    .orElseGet(() ->
            				template.insert(builder().withAge(48).withName("Bruce")
                    .withOccupation("Developer").withSalary(3_000D).build()));

            Person natalia = template.getTraversalVertex()
                    .hasLabel(Person.class)
                    .has("name", "Natalia")
                    .<Person>next()
                    .orElseGet(() -> template.insert(builder().withAge(32).withName("Natalia")
                    .withOccupation("Developer").withSalary(5_000D).build()));

            Person pepper = template.getTraversalVertex()
                    .hasLabel(Person.class)
                    .has("name", "Pepper")
                    .<Person>next()
                    .orElseGet(() -> template.insert(builder().withAge(40).withName("Pepper")
                    .withOccupation("Design").withSalary(1_000D).build()));

            Person tony = template.getTraversalVertex()
                    .hasLabel(Person.class)
                    .has("name", "Tony")
                    .<Person>next()
                    .orElseGet(() -> template.insert(builder().withAge(50).withName("Tony")
                    .withOccupation("Developer").withSalary(4_500D).build()));
            //template.

            template.edge(tony, "knows", pepper).add("feel", "love");
            template.edge(tony, "knows", natalia);

            template.edge(natalia, "knows", pepper);
            template.edge(bruce, "knows", pepper);

            List<Person> developers = template.getTraversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(30, 55))
                    .has("occupation", "Developer")
                    .<Person>getResult().collect(toList());

            List<Person> peopleWhoDeveloperKnows = template.getTraversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(30, 45))
                    .has("occupation", "Developer")
                    .out("knows")
                    .<Person>getResult().collect(toList());

            List<Person> both = template.getTraversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(40, 55))
                    .has("occupation", "Developer")
                    .outE("knows")
                    .bothV()
                    .<Person>getResult()
                    .distinct()
                    .collect(toList());

            List<Person> couple = template.getTraversalVertex()
                    .has("salary", gte(3_000D))
                    .has("age", between(40, 55))
                    .has("occupation", "Developer")
                    .outE("knows")
                    .has("feel", "love")
                    .bothV()
                    .<Person>getResult()
                    .distinct()
                    .collect(toList());

            System.out.println("Developers has salary greater than 3000 and age between 30 and 55: " + developers);
            System.out.println("Person who the Developers target know: " + peopleWhoDeveloperKnows);
            System.out.println("The person and the developers target: " + both);
            System.out.println("Developers for Valentine's day: " + couple);     
        }
    }
}
