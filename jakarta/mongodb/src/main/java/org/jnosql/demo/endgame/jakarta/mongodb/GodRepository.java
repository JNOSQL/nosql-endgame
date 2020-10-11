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

import jakarta.nosql.mapping.Param;
import jakarta.nosql.mapping.Query;
import jakarta.nosql.mapping.Repository;

import java.util.List;

public interface GodRepository extends Repository<God, Long> {

    @Query("select * from God where name = @name")
    List<God> query(@Param("name") String name);

    List<God> findByName(String name);
}
