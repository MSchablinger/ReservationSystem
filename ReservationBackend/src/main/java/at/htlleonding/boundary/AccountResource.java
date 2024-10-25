package at.htlleonding.boundary;

import at.htlleonding.model.Account;
import at.htlleonding.repository.AccountRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@Path("api/account")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountRepository accountRepository;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("health")
    @Produces(MediaType.TEXT_PLAIN)
    public String healthCheck() {
        return "AccountResource is alive!";
    }

    @POST
    @Transactional
    public Response createAccount(Account account) {
        accountRepository.persist(account);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(Long.toString(account.getId()))
                .build();
        return Response.created(uri).entity(account).build();
    }

    @GET
    @Path("{id}")
    public Account getAccountById(@PathParam("id") Long id) {
        return accountRepository.findById(id);
    }

    @PUT
    @Transactional
    public Response updateAccount(Account account) {
        accountRepository.update(account);
        return Response.ok().entity(account).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteAccount(@PathParam("id") Long id) {
        accountRepository.deleteById(id);
        return Response.noContent().build();
    }

}
