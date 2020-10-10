package org.jnosql.demo.endgame.spring.boot.data.neo4j.domain;

import static org.neo4j.springframework.data.core.schema.Relationship.Direction.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Property;
import org.neo4j.springframework.data.core.schema.Relationship;

@Node("Movie")
public class MovieEntity {

    @Id
    private final String title;

    @Property("tagline")
    private final String description;

    @Relationship(type = "ACTED_IN", direction = INCOMING)
    private Map<PersonEntity, Roles> actorsAndRoles = new HashMap<>();

    @Relationship(type = "DIRECTED", direction = INCOMING)
    private List<PersonEntity> directors = new ArrayList<>();

    public MovieEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Map<PersonEntity, Roles> getActorsAndRoles() {
        return actorsAndRoles;
    }

    public List<PersonEntity> getDirectors() {
        return directors;
    }
}
