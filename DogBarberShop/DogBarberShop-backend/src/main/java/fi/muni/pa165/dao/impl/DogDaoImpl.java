package fi.muni.pa165.dao.impl;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.utils.CustomerConverter;
import java.util.List;
import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.Validate;

/**
 *
 * @author Oliver Pentek
 */
public class DogDaoImpl implements DogDao{
    
    @PersistenceContext
    final private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public DogDaoImpl() {
        super();
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
    }

    public DogDaoImpl(@Nonnull final EntityManager em) {
        Validate.isTrue(em != null, "Entity manager should not be null");
        this.em = em;
    }

    @Override
    @Nonnull
    public void addDog(@Nonnull final Dog dog) {
        Validate.isTrue(dog != null, "dog cannot be null!");
        Validate.isTrue(dog.getId() == null, "dog's ID must be null!");
        em.persist(dog);
        //return dog;
    }

    @Override
    @Nonnull
    public Dog getDog(@Nonnull final Long id) {
        return em.find(Dog.class, id);
    }

    @Override
    @Nonnull
    public void updateDog(@Nonnull final Dog dog) {
        Validate.isTrue(dog != null, "dog cannot be null!");
        Validate.isTrue(dog.getId() != null, "dog's ID cannot be null!");
        this.em.merge(dog);
        //return this.em.merge(dog);
    }

    @Override
    public void removeDog(@Nonnull Dog dog) {
        Validate.isTrue(dog != null, "dog cannot be null!");
        Validate.isTrue(dog.getId() != null, "dog's ID cannot be null!");
        dog = this.em.merge(dog);
        em.remove(dog);
    }

    @Override
    @Nonnull
    public List<Dog> getAllDogs() {
        final CriteriaBuilder cb = this.em.getCriteriaBuilder();
        final CriteriaQuery cqry = cb.createQuery();
        final Root<Dog> root = cqry.from(Dog.class);
        cqry.select(root);
        final Query qry = em.createQuery(cqry);
        return  qry.getResultList();
        }

    /**
     * Vrati zoznam u nas registrovanych psov podla majitela.
     * @param owner Majitel, ktoreho psy chceme najst.
     * @return  Zoznam psov.
     */
    @Override
    @Nonnull
    public List<Dog> getDogsByOwner(@Nonnull final CustomerDto owner) {
        Validate.isTrue(owner != null, "Owner should not be null");
        Validate.isTrue(owner.getId() != null, "Owner's ID cannot be null!");
        final CriteriaBuilder cb = this.em.getCriteriaBuilder();
        final CriteriaQuery cqry = cb.createQuery();
        final Root<Dog> root = cqry.from(Dog.class);
        cqry.select(root);
        cqry.where(cb.equal(root.get("owner"), CustomerConverter.CustomerDtoToCustomer(owner)));
        final Query qry = em.createQuery(cqry);
        return  qry.getResultList();
    }
    
}
