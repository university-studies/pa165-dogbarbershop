package fi.muni.pa165.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.joda.time.DateTime;

/**
 *
 * @author Oliver Pentek
 */
@Entity
public class Dog implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "DOG_ID")
    private Long id;
    
    private String name;
    private String breed;
    private Date birth;
    
    @ManyToOne
    private Customer owner;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.dog", 
            cascade = CascadeType.ALL)
    private List<DogService> dogServices = new ArrayList<>();

    public Dog() {
        
    }

    public Dog(String name, String breed, Date birth) {
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

    public List<DogService> getDogServices() {
        return dogServices;
    }

    public String getName() {
        return name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public void setDogServices(List<DogService> dogServices) {
        this.dogServices = dogServices;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
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
