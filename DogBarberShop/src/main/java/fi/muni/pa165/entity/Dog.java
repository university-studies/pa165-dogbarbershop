package fi.muni.pa165.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.joda.time.DateTime;

/**
 *
 * @author Oliver Pentek
 */
@Entity
public class Dog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String breed;
    private DateTime birth;
    @ManyToOne
    private Customer owner;
    @ManyToMany
    private List<Service> services;

    public Dog() {
    }

    public Dog(String name, String breed, DateTime birth) {
        this.name = name;
        this.breed = breed;
        this.birth = birth;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public DateTime getBirth() {
        return birth;
    }

    public void setBirth(DateTime birth) {
        this.birth = birth;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Dog other = (Dog) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
