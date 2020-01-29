package org.example.exercise3;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/greeting")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    public List<String> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") Long id) {
        String entity = service.get(id.intValue());
        if (entity == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();
    }

    @POST
    public Response create(String greeting) {
        service.add(greeting);
        return Response.status(Response.Status.CREATED).entity(greeting).build();
    }

    @PUT
    @Path("/{id}")
    public Response put(@PathParam("id") Long id, String greeting) {
        service.update(id.intValue(), greeting);
        return Response.ok(greeting).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        String entity = service.get(id.intValue());
        if (entity == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        service.delete(id.intValue());
        return Response.noContent().build();

    }

}
