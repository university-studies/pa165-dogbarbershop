package fi.muni.pa165.rest;

import fi.muni.pa165.dto.CustomerDto;
import java.util.Objects;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oliver Pentek
 */
@XmlRootElement
public class CustomerResource {
    private CustomerDto customer;

    public CustomerResource() {
    }

    public CustomerResource(CustomerDto customer) {
        this.customer = customer;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
    
//    public String getAddress() {
//        return this.getCustomer().getAddress();
//    }

//    public Long getId() {
//        return this.getCustomer().getId();
//    }

    public void setId(Long id) {
        this.getCustomer().setId(id);
    }

//    public String getName() {
//        return this.getCustomer().getName();
//    }

    public void setName(String name) {
        this.getCustomer().setName(name);
    }

//    public String getSurname() {
//        return this.getCustomer().getSurname();
//    }

    public void setSurname(String surname) {
        this.getCustomer().setSurname(surname);
    }

//    public String getPhone() {
//        return this.getCustomer().getPhone();
//    }

    public void setPhone(String phone) {
        this.getCustomer().setPhone(phone);
    }
    
    public void setAddress(String address) {
        this.getCustomer().setAddress(address);
    }

    @Override
    public String toString() {
        return this.getCustomer().getId() + " " + this.getCustomer().getName() + " " + this.getCustomer().getSurname() + " " + 
                this.getCustomer().getPhone()+ " " + this.getCustomer().getAddress();
    }

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getPlain() {
//        return this.toString();
//    }
    
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getJson() {
//        StringBuilder bobTheBuilder = new StringBuilder();
//        bobTheBuilder.append("{\"customer\": {\"id\": \"");
//        bobTheBuilder.append(this.getCustomer().getId());
//        bobTheBuilder.append("\", \"name\": \"");
//        bobTheBuilder.append(this.getCustomer().getName());
//        bobTheBuilder.append("\", \"surname\": \"");
//        bobTheBuilder.append(this.getCustomer().getSurname());
//        bobTheBuilder.append("\", \"phone\": \"");
//        bobTheBuilder.append(this.getCustomer().getPhone());
//        bobTheBuilder.append("\", \"address\": \"");
//        bobTheBuilder.append(this.getCustomer().getAddress());
//        bobTheBuilder.append("\"}}");
//        return bobTheBuilder.toString();
//    }
    
}
