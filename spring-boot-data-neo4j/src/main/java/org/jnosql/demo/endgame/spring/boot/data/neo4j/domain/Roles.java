package org.jnosql.demo.endgame.spring.boot.data.neo4j.domain;

import org.neo4j.springframework.data.core.schema.RelationshipProperties;

import java.util.List;

@RelationshipProperties
public class Roles {

    private final List<String> roles;

    public Roles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getRoles() {
        return roles;
    }
}