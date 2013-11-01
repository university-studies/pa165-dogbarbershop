package fi.muni.pa165.dto;

import fi.muni.pa165.entity.Customer;
import org.joda.time.LocalDate;

/**
 *  @TODO doplnit vsade validaciu aby sme nemali v DTO null hodnoty
 * 
 * @author Oliver Pentek
 */
public class DogDto {
    private Long id;
    private String name;
    private String breed;
    private LocalDate birthDate;
    private Customer owner;

    public DogDto(Long id, String name, String breed, LocalDate birthDate, Customer owner) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.birthDate = birthDate;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    
}
