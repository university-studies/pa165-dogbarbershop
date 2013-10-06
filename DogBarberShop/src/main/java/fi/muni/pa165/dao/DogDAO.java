package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Dog;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Oliver Pentek
 */
public class DogDAO implements IDogDAO{
    EntityManager em;

    public DogDAO() {
        super();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
    }

    public DogDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public Dog createDog(Dog dog) {
        em.persist(dog);
        return dog;
    }

    @Override
    public Dog getDog(Long id) {
        return em.find(Dog.class, id);
    }

    @Override
    public Dog updateDog(Dog dog) {
       return this.em.merge(dog);
    }

    @Override
    public void deleteDog(Dog dog) {
        dog = this.em.merge(dog);
        em.remove(dog);
    }

    @Override
    public List<Dog> getAllDogs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
