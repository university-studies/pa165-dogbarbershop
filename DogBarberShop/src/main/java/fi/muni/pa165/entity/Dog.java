package fi.muni.pa165.entity;

import java.io.Serializable;
import javax.annotation.Nonnull;
import org.apache.commons.lang3.Validate;
import org.joda.time.DateTime;

/**
 *
 * @author Oliver Pentek
 */
public class Dog implements Serializable {
    private Long id;
    private String name;
    private String breed;
    private DateTime birth;

    public Dog(@Nonnull String name, @Nonnull String breed, @Nonnull DateTime birth) {
        Validate.isTrue(name != null, "Name should not be null");
        Validate.isTrue(breed != null, "Breed should not be null");
        Validate.isTrue(birth != null, "Birth should not be null");
        
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
