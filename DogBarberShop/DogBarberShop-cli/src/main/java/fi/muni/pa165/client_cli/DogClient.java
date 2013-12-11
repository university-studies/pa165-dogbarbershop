/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.client_cli;

import fi.muni.pa165.dto.DogDto;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.joda.time.LocalDate;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public class DogClient {

    private final String url;
    private Client client;

    public DogClient(String url) {
        this.url = url;
        this.client = ClientBuilder.newBuilder().register(MoxyJsonFeature.class)
            .build();
    }

    public DogDto getById(String id) {
        WebTarget webTarget = client.target(this.url).path("dogs").path(id);

        Invocation.Builder invocationBuilder =
                webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();     

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }
        
        return  response.readEntity(new GenericType<DogDto>() {});
    }

    public List<DogDto> getAll() {        
        WebTarget webTarget = client.target(this.url).path("dogs");

        Invocation.Builder invocationBuilder =
                webTarget.request(MediaType.APPLICATION_XML);

        Response response = invocationBuilder.get();     
        
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }
        
        return  response.readEntity(new GenericType<List<DogDto>>() {});
    }

    public Long add(DogDto dog, String customerId) {
        WebTarget webTarget = client.target(this.url);
        WebTarget resourceWebTarget = webTarget.path("dogs");

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");
        invocationBuilder.header("Customer-id", customerId);
        
        Response response = invocationBuilder.post(Entity.entity(dog, MediaType.APPLICATION_JSON));
        
        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            return null;
        }

        return new Long(response.getHeaderString("dog-id"));
    }

    public Long update(DogDto dog, String customerId) {
        WebTarget webTarget = client.target(this.url);
        WebTarget resourceWebTarget = webTarget.path("dogs");

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");
        invocationBuilder.header("Customer-id", customerId);
        
        Response response = invocationBuilder.put(Entity.entity(dog, MediaType.APPLICATION_JSON));
        
        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            return null;
        }
    
        String uri = response.getLocation().toString();
        Long id = new Long(uri.substring(uri.lastIndexOf("/") + 1, uri.length()));
                
        return  id; 
    }

    public void delete(String id) {
        WebTarget webTarget = client.target(this.url);
        WebTarget resourceWebTarget = webTarget.path("dogs")
                .path(id);

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        Response response = invocationBuilder.delete();
    }
}
