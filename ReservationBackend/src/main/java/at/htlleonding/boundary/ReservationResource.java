package at.htlleonding.boundary;

import at.htlleonding.model.Account;
import at.htlleonding.model.Reservation;
import at.htlleonding.repository.ReservationRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("api/reservations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReservationResource {

    @Inject
    ReservationRepository reservationRepository;
    @Inject
    UriInfo uriInfo;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "ReservationResource is alive!";
    }

    @POST
    @Transactional
    public Response createCustomer(Reservation reservation) {
        reservationRepository.persist(reservation);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(reservation.getId()))
                .build();
        return Response.created(uri).entity(reservation).build();
    }

    @GET
    @Path("{id}")
    public Reservation getCustomerById(@PathParam("id") Long id) {
        return reservationRepository.findById(id);
    }

    @PUT
    @Transactional
    public Response updateCustomer(Reservation reservation) {
        reservationRepository.update(reservation);
        return Response.ok().entity(reservation).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        reservationRepository.deleteById(id);
        return Response.noContent().build();
    }

}
