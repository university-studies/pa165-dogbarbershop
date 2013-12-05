package fi.muni.pa165.idao;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.entity.Dog;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface DogDao extends Serializable {
    void addDog(Dog dog);
    
    Dog getDog(Long id);
    
    void updateDog(Dog dog);
    
    void removeDog(Dog dog);
    
    List<Dog> getAllDogs();
    
    List<Dog> getDogsByOwner(CustomerDto owner);
    
}
