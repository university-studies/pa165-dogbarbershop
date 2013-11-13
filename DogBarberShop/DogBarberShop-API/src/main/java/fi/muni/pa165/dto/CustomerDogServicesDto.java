    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dto;

import java.sql.Date;
import org.joda.time.LocalDate;

/**
 *
 * @author martin
 */
public class CustomerDogServicesDto {
    
    private String dogName;
    
    private String serviceName;
    
    private LocalDate serviceDate;
    
    public CustomerDogServicesDto(String dogName, String serviceName, 
            LocalDate serviceDate){
        this.dogName = dogName;
        this.serviceName = serviceName;
        this.serviceDate = serviceDate;
    }

    public String getDogName() {
        return dogName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

}
