/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author martin
 */
@Embeddable
public class DogServiceId implements Serializable {
    
    @ManyToOne
    private Dog dog;
    
    @ManyToOne
    private Service service;

    public Dog getDog() {
        return dog;
    }

    public Service getService() {
        return service;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public void setService(Service service) {
        this.service = service;
    }
    

    @Override
    public int hashCode() {
        int result;
        result = (dog != null ? dog.hashCode() : 0);
        result = 31 * result + (service != null ? service.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
 
        DogServiceId that = (DogServiceId) o;
 
        if (dog != null ? !dog.equals(that.dog) : that.dog != null) return false;
        if (service != null ? !service.equals(that.service) : that.service != null)
            return false;
 
        return true;
    }
    
}
