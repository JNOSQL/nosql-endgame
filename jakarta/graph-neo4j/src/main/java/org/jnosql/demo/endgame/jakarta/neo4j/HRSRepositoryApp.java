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


import org.eclipse.jnosql.artemis.DatabaseQualifier;
import org.jnosql.demo.endgame.jakarta.neo4j.model.Person;
import org.jnosql.demo.endgame.jakarta.neo4j.repository.PersonRepository;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

public final class HRSRepositoryApp {

    private HRSRepositoryApp() {
    }

    @SuppressWarnings("unused")
	public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            PersonRepository repository = container.select(PersonRepository.class, DatabaseQualifier.ofGraph()).get();

            Person bruce = repository.save(Person.builder().withAge(48).withName("Bruce")
                    .withOccupation("Developer").withSalary(3_000D).build());

            Person natalia = repository.save(Person.builder().withAge(32).withName("Natalia")
                    .withOccupation("Developer").withSalary(5_000D).build());

            Person pepper = repository.save(Person.builder().withAge(40).withName("Pepper")
                    .withOccupation("Design").withSalary(1_000D).build());

            Person tony = repository.save(Person.builder().withAge(50).withName("Tony")
                    .withOccupation("Developer").withSalary(4_500D).build());


            System.out.println("findByOccupationAndSalaryGreaterThan");
            repository.findByOccupationAndSalaryGreaterThan("Developer", 3_000D)
                    .forEach(System.out::println);
            System.out.println("findByAgeBetween");
            repository.findByAgeBetween(30, 50)
                    .forEach(System.out::println);
        }
    }
}
