package fi.muni.pa165.dao.impl;

import fi.muni.pa165.dto.CustomerDto;
import fi.muni.pa165.idao.DogDao;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.DogService;
import fi.muni.pa165.idao.DogServiceDao;
import fi.muni.pa165.utils.CustomerConverter;
import java.util.List;
import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oliver Pentek
 */
@Repository
public class DogDaoImpl implements DogDao{
    
    @PersistenceContext
     private EntityManager em;

    public EntityManager getEm() {
        return em;
    }
    
    public DogDaoImpl() {

    }

    public DogDaoImpl(@Nonnull final EntityManager em) {
        Validate.isTrue(em != null, "Entity manager should not be null");
        this.em = em;
    }

    @Override
    public void addDog(@Nonnull final Dog dog) {
        Validate.isTrue(dog != null, "dog cannot be null!");
        Validate.isTrue(dog.getId() == null, "dog's ID must be null!");
        em.persist(dog);
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
    }

    @Override
    public void removeDog(@Nonnull Dog dog) {
        Validate.isTrue(dog != null, "dog cannot be null!");
        Validate.isTrue(dog.getId() != null, "dog's ID cannot be null!");
        dog = this.em.merge(dog);
        final DogServiceDao dogServiceDao = new DogServiceDaoImpl();
        dogServiceDao.setEntityManager(em);
        final List<DogService> services = dogServiceDao.getDogServiceByDog(dog);
        for (final DogService service : services) {
            dogServiceDao.deleteDogService(service);
        }
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
