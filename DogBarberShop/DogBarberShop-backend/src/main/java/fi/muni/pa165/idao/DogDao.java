package fi.muni.pa165.idao;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface DogDao extends Serializable {
    public void addDog(Dog dog);
    
    public Dog getDog(Long id);
    
    public void updateDog(Dog dog);
    
    public void removeDog(Dog dog);
    
    public List<Dog> getAllDogs();
    
    public List<Dog> getDogsByOwner(CustomerDto owner);
    
}
