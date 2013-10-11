package fi.muni.pa165.idao;

import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface IDogDAO extends Serializable {
    public Dog addDog(Dog dog);
    
    public Dog getDog(Long id);
    
    public Dog updateDog(Dog dog);
    
    public void removeDog(Dog dog);
    
    public List<Dog> getAllDogs();
    
    public List<Dog> getDogsByOwner(Customer owner);
    
}
