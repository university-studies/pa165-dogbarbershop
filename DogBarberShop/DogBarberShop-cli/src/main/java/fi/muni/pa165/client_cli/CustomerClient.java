/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.client_cli;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.dto.DogDto;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
//import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public class CustomerClient {

    private final String url;
    private Client client;

    public CustomerClient(String url) {
        this.url = url;
        this.client = ClientBuilder.newBuilder().register(MoxyJsonFeature.class)
                .build();
    }

    public List<CustomerDto> getAll() {
        WebTarget webTarget = client.target(this.url).path("customers");

        Invocation.Builder invocationBuilder =
                webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();
        
        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }

        return response.readEntity(new GenericType<List<CustomerDto>>() {
        });

    }

    public CustomerDto getById(String id) {
        WebTarget webTarget = client.target(this.url).path("customers").path(id);

        Invocation.Builder invocationBuilder =
                webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.get();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }
  
        return response.readEntity(new GenericType<CustomerDto>() {
        });
       
    }

    public Long add(CustomerDto cust) {
        WebTarget webTarget = client.target(this.url);
        WebTarget resourceWebTarget = webTarget.path("customers");

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        Response response = invocationBuilder.post(Entity.entity(cust, MediaType.APPLICATION_JSON));

        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            return null;
        }
        
        String uri = response.getLocation().toString();
        Long id = new Long(uri.substring(uri.lastIndexOf("/") + 1, uri.length()));

        return id;
    }

    /*
     * Return id
     */
    public Long update(CustomerDto cust) {
        WebTarget webTarget = client.target(this.url);
        WebTarget resourceWebTarget = webTarget.path("customers");

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        Response response = invocationBuilder.put(Entity.entity(cust, MediaType.APPLICATION_JSON));
        
        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            return null; 
        }
        
        String uri = response.getLocation().toString();
        Long id = new Long(uri.substring(uri.lastIndexOf("/") + 1, uri.length()));
        
        return id;
    }

    public void delete(String id) {
        WebTarget webTarget = client.target(this.url);
        WebTarget resourceWebTarget = webTarget.path("customers")
                .path(id);

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        Response response = invocationBuilder.delete();
    }
}
