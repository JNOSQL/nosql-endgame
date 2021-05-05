/*
 * Copyright (c) 2017, 2021 Otávio Santana and others
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

package org.jnosql.demo.endgame.jakarta.hazelcast;


import jakarta.nosql.mapping.keyvalue.KeyValueTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.jnosql.demo.endgame.jakarta.hazelcast.model.User;

import java.util.Arrays;
import java.util.Optional;

/**
 * Demo application showing the use of the {@link KeyValueTemplate} on top of a Hazelcast system.
 * @author Otavio Santana
 * @author Werner Keil
 */
public class UserTemplateApp {

    private static final User USER = User.builder().
            withPhones(Arrays.asList("234", "432"))
            .withUsername("username")
            .withName("Name")
            .build();

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            KeyValueTemplate template = container.select(KeyValueTemplate.class).get();
            User userSaved = template.put(USER);
            System.out.println("User saved: " + userSaved);
            Optional<User> user = template.get("username", User.class);
            System.out.println("Entity found: " + user);

            System.exit(0);
        }
        
        
    }

    /** Just to keep it a singleton */
    private UserTemplateApp() {
    }
}
