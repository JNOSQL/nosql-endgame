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

import jakarta.nosql.mapping.PreparedStatement;
import jakarta.nosql.mapping.document.DocumentTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.jnosql.demo.endgame.jakarta.mongodb.model.God;

import java.util.Optional;

public class GodsRepoAppWithPreparedStatement {

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {

            God hunter = new God(1L, "Ullr", "Hunting");

            DocumentTemplate template =  container.select(DocumentTemplate.class).get();

            template.insert(hunter);

            Optional<God> god = template.singleResult("select * from God where _id = 1");
            System.out.println("Plain query text : " + god);

            PreparedStatement prepare = template.prepare("select * from God where _id = @id");
            prepare.bind("id", 1L);

            System.out.println("Query by prepare query" + prepare.getSingleResult());


            template.query("delete from God where _id = 1");

            System.out.println("query : " + template.find(God.class, 1L));
        }

        System.exit(0);
    }
}
