/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.client_cli;

import fi.muni.pa165.dto.CustomerDto;
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
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 *
 * @author Pavol Loffay <p.loffay@gmail.com>
 */
public class CustomerClient {

    final String url;
    Client client;

    public CustomerClient(String url) {
        this.url = url;
        /*
         * S responseReaderom mi islo get
         */
        this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    }

    public List<CustomerDto> getAll() {
        List<CustomerDto> customers = null;
        try {
            client.target(this.url)
                    .path("customers")
                    .request(MediaType.APPLICATION_XML)
                    .get(new GenericType<List<CustomerDto>>() {
            });
        } catch (Exception e) {
            return null;
        }

        return customers;
    }

    public CustomerDto getById(String path) {
        CustomerDto customers = null;
        try {
            client.target(this.url)
                    .path(("customers/" + path).replaceAll("\\s+", ""))
                    .request(MediaType.APPLICATION_XML)
                    .get(CustomerDto.class);
        } catch (Exception e) {
            return null;
        }
        return customers;
    }

    public Long add(CustomerDto cust) {
        WebTarget webTarget = client.target(this.url);
        WebTarget resourceWebTarget = webTarget.path("customers");

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        Response response = invocationBuilder.post(Entity.entity(cust, MediaType.APPLICATION_JSON));

        String uri = response.getLocation().toString();
        Long id = new Long(uri.substring(uri.lastIndexOf("/") + 1, uri.length()));

        return id;
    }

    public void update(CustomerDto cust) {
        WebTarget webTarget = client.target(this.url);
        WebTarget resourceWebTarget = webTarget.path("customers");

        Invocation.Builder invocationBuilder =
                resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");

        Response response = invocationBuilder.put(Entity.entity(cust, MediaType.APPLICATION_JSON));
        System.out.println(response.getStatus());
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

    public static class ResponseReader
            implements MessageBodyReader<CustomerDto> {

        @Override
        public boolean isReadable(Class<?> type, Type genericType,
                Annotation[] annotations, MediaType mediaType) {
            return type == CustomerDto.class;
        }

        @Override
        public CustomerDto readFrom(Class<CustomerDto> type,
                Type genericType,
                Annotation[] annotations, MediaType mediaType,
                MultivaluedMap<String, String> httpHeaders,
                InputStream entityStream)
                throws IOException, WebApplicationException {

            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(CustomerDto.class);
                CustomerDto custDto = (CustomerDto) jaxbContext.createUnmarshaller()
                        .unmarshal(entityStream);
                return custDto;
            } catch (JAXBException jaxbException) {
                throw new ProcessingException("Error deserializing a CustomerDto.",
                        jaxbException);
            }
        }
    }
}
