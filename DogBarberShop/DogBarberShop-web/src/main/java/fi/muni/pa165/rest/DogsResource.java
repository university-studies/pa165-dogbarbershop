/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.rest;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import fi.muni.pa165.service.CustomerService;
import fi.muni.pa165.service.DogService;
import fi.muni.pa165.web.DogBarberShopApplication;
import java.net.URI;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
 * @author martin, Pavol Loffay
 */
@Path("/dogs")
@Singleton
public class DogsResource {

    private final DogService service = DogBarberShopApplication.get().getDogService();
    private final CustomerService serviceCust = DogBarberShopApplication.get().getCustomerService();
    private static final Logger log = Logger.getLogger(CustomersResource.class.getName());
    @Context
    private UriInfo context;

    // curl -i http://localhost:8080/pa165/webresources/dogs
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<DogDto> getDogs() {
        return service.getAllDogs();
    }

    // curl -i http://localhost:8080/pa165/webresources/dogs/1
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDogByid(@PathParam("id") Long id) {
        try {
            return Response.status(Response.Status.OK).entity(service.getDogById(id)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // curl -i http://localhost:8080/pa165/webresources/dogs/count
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        return String.valueOf(service.getAllDogs().size());
    }

    // curl -i -H "Content-Type: application/json" --data '{"name":"havo","breed":"civava","owner":{"address":"Purkynova 93","id":-21474833320,"name":"Martin","phone":"420774670609","surname":"Sakac"}}' http://localhost:8080/pa165/webresources/dogs
    // curl -i -H "Content-Type: application/json" --data '{"name":"peso","breed":"doga","birthDate":{"year":1990,"monthOfYear":10,"dayOfMonth":12}}' http://localhost:8080/pa165/webresources/dogs
    // curl -i -H "Content-Type: application/json" --data '{"name":"havo","breed":"civava"}' http://localhost:8080/pa165/webresources/dogs
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJsonDog(@HeaderParam("Customer-id") String headerCustId, DogDto dog) {

        try {
            if (! headerCustId.matches("no-ID")) {
                CustomerDto cust = serviceCust.getCustomerById(new Long(headerCustId));
                dog.setOwner(cust);
            }

            service.addDog(dog);
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        log.info("Created dog " + dog.toString());
        
        return Response.created(URI.create(context.getAbsolutePath().toString())).header("dog-id", dog.getId()).build();
    }

    // curl -i -X PUT -H "Content-Type: application/json" --data '{"id":-21474833290,"name":"chlpacik","breed":"bernardin"}' http://localhost:8080/pa165/webresources/dogs
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJson(@HeaderParam("Customer-id") String headerCustId, DogDto dog) {
        try {
            CustomerDto cust = serviceCust.getCustomerById(new Long(headerCustId));
            dog.setOwner(cust);
            
            service.updateDog(dog);
            log.info("Udpated customer " + dog.getId());
        } catch (Exception ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.created(URI.create(context.getAbsolutePath() + "/" + dog.getId())).build();
    }

    // curl -i -X DELETE http://localhost:8080/pa165/webresources/dogs/delete/-21474833267
    @DELETE
    @Path("{id}")
    public Response deleteJson(@PathParam("id")
            final Long id) {
        service.deleteDog(new DogDto(id));
        log.info("Deleted dog " + id);
        return Response.ok().build();
    }
}
