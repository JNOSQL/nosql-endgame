package org.jnosql.demo.quarkus.mongo.reactive;

import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jnosql.demo.quarkus.mongo.reactive.function.PersonDTO;
import org.jnosql.demo.quarkus.mongo.reactive.model.Person;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService personService;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Uni<List<PersonDTO>> streamPersons() {
        return personService.streamPersons();
    }

    @GET
    public Uni<List<PersonDTO>> get() {
        return personService.getAll();
    }

    @GET
    @Path("{id}")
    public Uni<Response> getSingle(@PathParam("id") final String id) {
        return personService.getSingle(id);
    }

    @POST
    public Uni<Response> create(final Person person) {
        return personService.create(person);
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(@PathParam("id") final String id, final Person person) {
        return personService.update(id, person);
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@PathParam("id") String id) {
        return personService.delete(id);
    }
}
