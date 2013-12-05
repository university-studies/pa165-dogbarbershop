package fi.muni.pa165.entity;

import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.apache.commons.lang3.Validate;
import org.joda.time.LocalDate;

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
    private LocalDate birth;
    @ManyToOne
    private Customer owner;

    public Dog() {
    }

    public Dog(String name, String breed, LocalDate birth, @Nonnull Customer owner) {
        this(null, name, breed, birth, owner);
    }
    
    public Dog(Long id, String name, String breed, LocalDate birth, @Nonnull Customer owner) {
        Validate.isTrue(owner != null, "Owner should not be null");
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.birth = birth;
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

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
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

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
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

    @Override
    public String toString() {
        return "Dog{" + "id=" + id + ", name=" + name + ", breed=" + breed + ", birth=" + birth + ", owner=" + owner + '}';
    }
    
}
