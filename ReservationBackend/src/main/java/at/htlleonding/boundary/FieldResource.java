package at.htlleonding.boundary;

import at.htlleonding.model.Field;
import at.htlleonding.repository.FieldRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("api/fields")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FieldResource {

    @Inject
    FieldRepository fieldRepository;
    @Inject
    UriInfo uriInfo;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "FieldResource is alive!";
    }

    @POST
    @Transactional
    public Response createField(Field field) {
        fieldRepository.persist(field);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(field.getId()))
                .build();
        return Response.created(uri).entity(field).build();
    }

    @GET
    @Path("{id}")
    public Field getFieldById(@PathParam("id") Long id) {
        return fieldRepository.findById(id);
    }

    @PUT
    @Transactional
    public Response updateField(Field field) {
        fieldRepository.update(field);
        return Response.ok().entity(field).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteField(@PathParam("id") Long id) {
        fieldRepository.deleteById(id);
        return Response.noContent().build();
    }

}
