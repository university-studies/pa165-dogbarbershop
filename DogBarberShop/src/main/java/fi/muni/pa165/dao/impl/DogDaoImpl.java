package fi.muni.pa165.dao.impl;

import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
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
    public Dog addDog(@Nonnull final Dog dog) {
        em.persist(dog);
        return dog;
    }

    @Override
    @Nonnull
    public Dog getDog(@Nonnull final Long id) {
        return em.find(Dog.class, id);
    }

    @Override
    @Nonnull
    public Dog updateDog(@Nonnull final Dog dog) {
       return this.em.merge(dog);
    }

    @Override
    public void removeDog(@Nonnull Dog dog) {
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
    public List<Dog> getDogsByOwner(@Nonnull final Customer owner) {
        Validate.isTrue(owner != null, "Owner should not be null");
        final CriteriaBuilder cb = this.em.getCriteriaBuilder();
        final CriteriaQuery cqry = cb.createQuery();
        final Root<Dog> root = cqry.from(Dog.class);
        cqry.select(root);
        cqry.where(cb.equal(root.get("owner"), owner));
        final Query qry = em.createQuery(cqry);
        return  qry.getResultList();
    }
    
}
