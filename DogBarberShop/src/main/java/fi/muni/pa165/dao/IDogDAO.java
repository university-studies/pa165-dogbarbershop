package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Dog;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Oliver Pentek
 */
public interface IDogDAO extends Serializable {
    public Dog creatDog(Dog dog);
    
    public Dog getDog(Long id);
    
    public Dog updateDog(Dog dog);
    
    public void deleteDog(Dog dog);
    
    public List<Dog> getAllDogs();
    
}
