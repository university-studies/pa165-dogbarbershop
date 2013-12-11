package fi.muni.pa165.rest;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.web.DogBarberShopApplication;
import java.net.URI;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;

/**
 *
 * @author Oliver Pentek, Pavol Loffay
 */
@Path("/customers")
@Singleton
public final class CustomersResource {
    
    // FIXME samostatny inject autowired
    private final CustomerService service = DogBarberShopApplication.get().getCustomerService();
    
    private static final Logger log = Logger.getLogger(CustomersResource.class.getName());
    
    @Context
    private UriInfo context;
    
    //curl -i http://localhost:8084/pa165/webresources/customers
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<CustomerDto> getCustomers() {
        return service.getAllCustomers();
}
 
    // curl -i http://localhost:8084/pa165/webresources/customers/1
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCustomerResource(@PathParam("id") final Long id) {
        try {
            return Response.status(Response.Status.OK).entity(service.getCustomerById(id)).build();
        }catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
 
    //  curl -i http://localhost:8084/pa165/webresources/customers/count
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        return String.valueOf(service.getAllCustomers().size());
    }
    
    // curl -i -H "Content-Type: application/json" --data '{"address":"K Babe 13","name":"Kali","phone":"774145489","surname":"Pentek"}' http://localhost:8084/pa165/webresources/customers
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(final CustomerDto customer) {
        try {
            service.addCustomer(customer);
            log.info("Created customer " + customer.getId());
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.created(URI.create(context.getAbsolutePath() + "/"+ customer.getId())).build();
    }
    
    // curl -i -X PUT -H "Content-Type: application/json" --data '{"id":-21474836119,"address":"K Babe 13","name":"Kali","phone":"774145489","surname":"Theodorakaku"}' http://localhost:8084/pa165/webresources/customers
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJson(final CustomerDto customer) {
        try {
            service.updateCustomer(customer);
            log.info("Udpated customer " + customer.getId());
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.created(URI.create(context.getAbsolutePath() + "/"+ customer.getId())).build();
    }
    
    // curl -i -X DELETE http://localhost:8084/pa165/webresources/customers/delete/-21474836119
    @DELETE
    @Path("{id}")
    public Response deleteJson(@PathParam("id") final Long id) {
        service.deleteCustomer(new CustomerDto(id));
        log.info("Deleted customer " + id);
        return Response.ok().build();
    }
}
