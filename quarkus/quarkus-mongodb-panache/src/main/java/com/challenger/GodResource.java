package com.challenger;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.bson.types.ObjectId;

@Path("/gods")
public class GodResource {

    private GodRepository godRepository;

    @Inject
    public GodResource(GodRepository godRepository) {
        this.godRepository = godRepository;
    }

    @POST
    public Response create(God god) {
        godRepository.persist(god);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") String id, God god) {
        god.id = new ObjectId(id);
        godRepository.update(god);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") String id) {
        God god = godRepository.findById(new ObjectId(id));
        godRepository.delete(god);
    }

    @GET
    @Path("/{id}")
    public God get(@PathParam("id") String id) {
        return godRepository.findById(new ObjectId(id));
    }

    @GET
    public List<God> list() {
        return godRepository.listAll();
    }

    @GET
    @Path("/orderedByName")
    public List<God> listAllOrderedByName() {
        return godRepository.findOrderedByName();
    }

    @GET
    @Path("/search/{name}")
    public God search(@PathParam("name") String name) {
        return godRepository.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return godRepository.count();
    }

}