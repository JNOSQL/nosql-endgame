/*
 * Copyright (c) 2020 Werner Keil and others
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
package org.jnosql.demo.endgame.jakarta.mongodb;

import org.eclipse.jnosql.artemis.DatabaseQualifier;
import org.jnosql.demo.endgame.jakarta.mongodb.model.God;
import org.jnosql.demo.endgame.jakarta.mongodb.repository.GodRepository;

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
