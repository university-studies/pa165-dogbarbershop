package fi.muni.pa165.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *  @TODO doplnit vsade validaciu aby sme nemali v DTO null hodnoty
 * 
 * @author Oliver Pentek
 */
public class CustomerDto implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String address;

    public CustomerDto(Long id, String name, String surname, String address, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }
    
    public CustomerDto(String name, String surname, String address, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }
    
    public CustomerDto(){
    
    }

    @Override
    public String toString() {
      return name + " " + surname;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final CustomerDto other = (CustomerDto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
