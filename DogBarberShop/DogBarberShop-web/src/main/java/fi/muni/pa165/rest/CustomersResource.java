package fi.muni.pa165.rest;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.web.DogBarberShopApplication;
import java.net.URI;
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
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Oliver Pentek
 */
@Path("/customers")
@Singleton
public class CustomersResource {
    
    // FIXME samostatny inject autowired
    private CustomerService service = DogBarberShopApplication.get().getCustomerService();
    
    @Context
    private UriInfo context;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlain() {
        StringBuilder returnString = new StringBuilder();
 
        for (CustomerDto customer : service.getAllCustomers()) {
            
            returnString.append(new CustomerResource(customer));
            returnString.append(" ");
        }
 
        return returnString.toString();
    }
 
    @Path("json/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto getCustomerResource(@PathParam("id") Long id) {
        return service.getCustomerById(id);
    }
 
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        return String.valueOf(service.getAllCustomers().size());
    }
    
    @GET
    @Path("xml/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public CustomerDto get(@PathParam("id") Long id) {
        return service.getCustomerById(id);
    }
    
    // napr. curl -i -H 'Accept: application/json' --data '{"customer": {"id": "1", "name": "Oliver", "surname": "Pentek", "phone": "774145489", "address": "K Babe 13"}}' http://localhost:8084/pa165/webresources/customers
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(CustomerDto customerResource) {
        service.addCustomer(customerResource);
        System.out.println("Created customer " + customerResource.getId());
        return Response.created(URI.create(context.getAbsolutePath() + "/"+ customerResource.getId())).build();
    }
//    
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateJson(CustomerResource customerResource) {
// //       CustomerResource current = customerDB.get(Integer.valueOf(customerResource.getId()));
//        CustomerResource current = customerResource;
////        current.setInvention(customerResource.getInvention());
////        current.setName(customerResource.getName());
////        current.setOccupation(customerResource.getOccupation());
////        current.setSurname(customerResource.getSurname());
//        customerDB.put(Integer.parseInt(customerResource.getId()), current);
//        System.out.println("Udpated customer " + customerResource.getId());
//        return Response.created(URI.create(context.getAbsolutePath() + "/"+ customerResource.getId())).build();
//    }
//    
//    @DELETE
//    @Path("del/{id}")
//    public Response deleteJson(@PathParam("id") Integer id) {
//        customerDB.remove(id);
//        System.out.println("Deleted customer " + id);
//        return Response.ok().build();
//    }
}
