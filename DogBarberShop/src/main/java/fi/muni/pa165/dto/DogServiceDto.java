package fi.muni.pa165.dto;

import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.Service;
import org.joda.time.LocalDate;

/**
 *
 * @author Oliver Pentek
 */
public final class DogServiceDto {

    private Long id;    
    private Dog dog;    
    private Service service;    
    private LocalDate serviceDate;
    private Long servedBy;

    public DogServiceDto(Long id, Dog dog, Service service, LocalDate serviceDate) {
        this.id = id;
        this.dog = dog;
        this.service = service;
        this.serviceDate = serviceDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Long getServedBy() {
        return servedBy;
    }

    public void setServedBy(Long servedBy) {
        this.servedBy = servedBy;
    }
    
}
