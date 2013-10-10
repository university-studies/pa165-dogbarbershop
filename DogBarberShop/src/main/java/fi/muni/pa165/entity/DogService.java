/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author martin
 */

@Entity
@Table(name = "DOG_SERVICE")
public class DogService implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @ManyToOne
    private Dog dog;
    
    @ManyToOne
    private Service service;
    
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Date serviceDate;
    
    // uchovacame aj ID zamestnanca, ktory danu sluzbu vykonal, 
    // ale nie je sucastou ziadnej vazby - chceme sa vyhnut ternarnej vazbe
    private Long servedBy;
    
    public Long getId() {
        return id;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Dog getDog() {
        return dog;
    }

    public Service getService() {
        return service;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public void setServedBy(Long servedBy) {
        this.servedBy = servedBy;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public Long getServedBy() {
        return servedBy;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DogService other = (DogService) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
	int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
}
