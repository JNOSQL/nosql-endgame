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

package org.jnosql.demo.endgame.jakarta.hazelcast;


import org.eclipse.jnosql.artemis.DatabaseQualifier;
import org.jnosql.demo.endgame.jakarta.hazelcast.model.User;
import org.jnosql.demo.endgame.jakarta.hazelcast.repository.UserRepository;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.util.Arrays;
import java.util.Optional;

public class UserRepoApp {

    private static final User USER = User.builder().
            withPhones(Arrays.asList("234", "432"))
            .withEmail("user@na.me")
            .withUsername("username")
            .withName("Name")
            .build();

    public static void main(String[] args) {

        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

            UserRepository repository = container.select(UserRepository.class, DatabaseQualifier.ofKeyValue()).get();
            repository.save(USER);
            Optional<User> user = repository.findById("username");
            System.out.println("User found: " + user);
            System.out.println("Entity found: " + repository.existsById("username"));
            
            System.exit(0);
        }
    }

    private UserRepoApp() {
    }
}
