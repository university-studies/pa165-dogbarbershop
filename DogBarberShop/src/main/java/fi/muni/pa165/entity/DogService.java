/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author martin
 */

@Entity
@Table(name = "DOG_SERVICE")
@AssociationOverrides({
	@AssociationOverride(name = "pk.dog", 
		joinColumns = @JoinColumn(name = "DOG_ID")),
	@AssociationOverride(name = "pk.service", 
		joinColumns = @JoinColumn(name = "SERVICE_ID")) })
public class DogService implements Serializable {
    

    @EmbeddedId
    private DogServiceId pk = new DogServiceId();
    
    //@Temporal(javax.persistence.TemporalType.DATE)
    private Date serviceDate;
    
    //uchovacame aj ID zamestnanca, ktory danu sluzbu vykonal, ale nie je sucastou ziadnej vazby
    private Long servedBy;
    
    //@Transient
    public Dog getDog(){
        return getPk().getDog();
    }
    
    public void setDog(Dog dog) {
        getPk().setDog(dog);
    }

    //@Transient
    public Service getService(){
        return getPk().getService();
    }
    
    public void setService(Service service) {
        getPk().setService(service);
    }
    
    public DogServiceId getPk() {
        return pk;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setPk(DogServiceId pk) {
        this.pk = pk;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public void setServedBy(Long servedBy) {
        this.servedBy = servedBy;
    }

    public Long getServedBy() {
        return servedBy;
    }


    @Override
    public boolean equals(Object o) {
	if (this == o)
		return true;
	if (o == null || getClass() != o.getClass())
		return false;
 
	DogService that = (DogService) o;
 
	if (getPk() != null ? !getPk().equals(that.getPk())
			: that.getPk() != null)
		return false;
 
	return true;
    }

    @Override
    public int hashCode() {
	return (getPk() != null ? getPk().hashCode() : 0);
    }
    
}
