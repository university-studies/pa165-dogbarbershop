package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Dog;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Oliver Pentek
 */
public class DogDAO implements IDogDAO{
    @PersistenceContext(unitName = "PU")
    EntityManager em;

    public Dog creatDog(Dog dog) {
        em.persist(dog);
        return dog;
    }

    public Dog getDog(Long id) {
        return em.find(Dog.class, id);
    }

    public Dog updateDog(Dog dog) {
       return this.em.merge(dog);
    }

    public void deleteDog(Dog dog) {
        dog = this.em.merge(dog);
        em.remove(dog);
    }

    public List<Dog> getAllDogs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
