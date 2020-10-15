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
package org.jnosql.demo.endgame.jakarta.mongodb.model;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

@Entity
public class God {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String power;

    public God(Long id, String name, String power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public God() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "God{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", power=" + power +
                '}';
    }
}
