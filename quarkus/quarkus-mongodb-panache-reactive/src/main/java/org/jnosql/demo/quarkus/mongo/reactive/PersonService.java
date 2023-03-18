package org.jnosql.demo.quarkus.mongo.reactive;

import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.jnosql.demo.quarkus.mongo.reactive.function.PersonDTO;
import org.jnosql.demo.quarkus.mongo.reactive.function.PersonMapper;
import org.jnosql.demo.quarkus.mongo.reactive.model.Person;
import org.jnosql.demo.quarkus.mongo.reactive.repository.PersonRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@ApplicationScoped
public class PersonService {

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Inject
    public PersonService(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    public Uni<List<PersonDTO>> streamPersons() {
        return personRepository.streamAll().collect().asList().map(personMapper::toDtoList);
    }

    public Uni<List<PersonDTO>> getAll() {
        return personRepository.findAll().list().map(personMapper::toDtoList);
    }

    public Uni<Response> getSingle(final String id) {
        return personRepository.findById(new ObjectId(id))
                .onItem().transform(person -> person != null && person.id != null ? Response.ok(personMapper.toDto(person)) : Response.status(NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    public Uni<Response> create(final Person person) {
        return personRepository.persist(person)
                .onItem().transform(id -> URI.create("/persons/" + id))
                .onItem().transform(uri -> Response.created(uri).entity(personMapper.toDto(person)).build());
    }

    public Uni<Response> update(final String id, final Person person) {
        person.id = new ObjectId(id);

        return personRepository.update(person)
                .onItem().transform(item -> person.id != null ? Response.ok(personMapper.toDto(person)) : Response.status(NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    public Uni<Response> delete(String id) {
        return personRepository.deleteById(new ObjectId(id))
                .onItem().transform(status -> Response.status(Response.Status.OK).build());
    }
}
