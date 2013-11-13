package fi.muni.pa165.dto;

import java.util.Objects;
import org.joda.time.LocalDate;

/**
 *
 * @author Oliver Pentek
 */
public final class DogServiceDto {

    private Long id;    
    private DogDto dog;    
    private ServiceDto service;    
    private LocalDate serviceDate;
    private Long servedBy;

    public DogServiceDto() {
    }

    public DogServiceDto(Long id, DogDto dog, ServiceDto service, LocalDate serviceDate, Long servedBy) {
        this.id = id;
        this.dog = dog;
        this.service = service;
        this.serviceDate = serviceDate;
        this.servedBy = servedBy;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DogDto getDog() {
        return dog;
    }

    public void setDog(DogDto dog) {
        this.dog = dog;
    }

    public ServiceDto getService() {
        return service;
    }

    public void setService(ServiceDto service) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DogServiceDto other = (DogServiceDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
