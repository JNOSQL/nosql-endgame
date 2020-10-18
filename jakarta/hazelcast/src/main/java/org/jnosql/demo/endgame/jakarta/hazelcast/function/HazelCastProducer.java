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
 */

package org.jnosql.demo.endgame.jakarta.hazelcast.function;


import jakarta.nosql.keyvalue.BucketManager;
import org.eclipse.jnosql.diana.hazelcast.keyvalue.HazelcastBucketManagerFactory;
import org.eclipse.jnosql.diana.hazelcast.keyvalue.HazelcastKeyValueConfiguration;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class HazelCastProducer {

    private static final String BUCKET = "developers";

    private HazelcastKeyValueConfiguration configuration;

    private HazelcastBucketManagerFactory managerFactory;

    @PostConstruct
    public void init() {
        configuration = new HazelcastKeyValueConfiguration();
        managerFactory = configuration.get();
    }


    @Produces
    public BucketManager getManager() {
        return managerFactory.getBucketManager(BUCKET);

    }

}
