package org.jnosql.demo.quarkus.mongo.reactive.function;

import org.jnosql.demo.quarkus.mongo.reactive.QuarkusMapperConfig;
import org.jnosql.demo.quarkus.mongo.reactive.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = QuarkusMapperConfig.class)
public interface PersonMapper {
    PersonDTO toDto(Person person);

    List<PersonDTO> toDtoList(List<Person> persons);
}
