package org.jnosql.demo.endgame.spring.neo4j.domain;

import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;

@Node("Person")
public class PersonEntity {

    @Id
    private final String name;

    private final Integer born;

    public PersonEntity(Integer born, String name) {
        this.born = born;
        this.name = name;
    }

    public Integer getBorn() {
        return born;
    }

    public String getName() {
        return name;
    }

}
