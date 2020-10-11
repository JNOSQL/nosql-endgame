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

package org.jnosql.demo.endgame.jakarta.mongodb;


import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;

import java.util.List;


@Entity
public class Person {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private List<String> phones;

    @Column
    private Address address;

    @Column
    private Job job;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public List<String> getPhones() {
        return phones;
    }


    public Person() {
    }

    Person(long id, String name, List<String> phones, Address address, Job job) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.address = address;
        this.job = job;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phones=").append(phones);
        sb.append(", address=").append(address);
        sb.append(", job=").append(job);
        sb.append('}');
        return sb.toString();
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }
}
