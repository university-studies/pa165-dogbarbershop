package fi.muni.pa165.dao.impl;

import fi.muni.pa165.dao.impl.DogDaoImpl;
import fi.muni.pa165.dao.impl.CustomerDaoImpl;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;
import org.joda.time.LocalDate;

/**
 *
 *  @author Oliver Pentek
 */
public class DogDaoImplTest extends TestCase {
    
    EntityManagerFactory emf;
    
    public DogDaoImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
         this.emf = Persistence.createEntityManagerFactory("testPU");
        
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    private Dog newDog(String name, String breed, LocalDate birth) {
        Dog dog = new Dog();
        dog.setName(name);
        dog.setBreed(breed);
        dog.setBirth(birth);
        return dog;
    }
    
    public void testAddDog() {
        EntityManager em = emf.createEntityManager();
        DogDaoImpl dao = new DogDaoImpl(em);
        Dog dog = new Dog();
        em.getTransaction().begin();
        dao.addDog(dog);
        em.getTransaction().commit();
        em.clear();
        
        Long id = dog.getId();
        assertNotNull(id);
        Dog dog2 = dao.getDog(id);
        assertEquals(dog, dog2);
    }
    
    public void testGetDog() {
        EntityManager em = emf.createEntityManager();
        DogDaoImpl dao = new DogDaoImpl(em);
        Dog dog = newDog("prvy", "vlk", new LocalDate(1998, 05, 22));
        Dog dog2 = newDog("druhy", "vlciak", new LocalDate(1995, 01, 21));
        Dog dog3 = newDog("treti", "vlcisko", new LocalDate(1993, 8, 26));
        em.getTransaction().begin();
        dao.addDog(dog);
        dao.addDog(dog2);
        dao.addDog(dog3);
        em.getTransaction().commit();
        em.clear();
        
        Long id = dog.getId();
        Long id2 = dog2.getId();
        Long id3 = dog3.getId();
        assertNotNull(id);
        assertNotNull(id2);
        assertNotNull(id3);
        assertEquals(dog, dao.getDog(id));
        assertEquals(dog2, dao.getDog(id2));
        assertEquals(dog3, dao.getDog(id3));
    }
    
   public void testUpdateDog() {
       Dog dog1 = newDog("prvy", "vlk", new LocalDate(1998, 05, 22));
        EntityManager em = emf.createEntityManager();
        DogDaoImpl dao = new DogDaoImpl(em);
        em.getTransaction().begin();
        dao.addDog(dog1);
        em.getTransaction().commit();
        em.clear();
        
        dog1.setName("prviak");
        em.getTransaction().begin();
        dao.updateDog(dog1);
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        Dog dog11 = dao.getDog(dog1.getId());
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(dog1, dog11);
        
        dog1.setBreed("civava");
        em.getTransaction().begin();
        dao.updateDog(dog1);
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        dog11 = dao.getDog(dog1.getId());
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(dog1, dog11);
        
        dog1.setBirth(new LocalDate(1991, 9, 23));
        em.getTransaction().begin();
        dao.updateDog(dog1);
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        dog11 = dao.getDog(dog1.getId());
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(dog1, dog11);
   }
   
   public void testRemoveDog() {
       EntityManager em = emf.createEntityManager();
        DogDaoImpl dao = new DogDaoImpl(em);
        Dog dog = newDog("prvy", "vlk", new LocalDate(1998, 05, 22));
        Dog dog2 = newDog("druhy", "vlciak", new LocalDate(1995, 01, 21));
        Dog dog3 = newDog("treti", "vlcisko", new LocalDate(1993, 8, 26));
        em.getTransaction().begin();
        dao.addDog(dog);
        dao.addDog(dog2);
        dao.addDog(dog3);
        em.getTransaction().commit();
        
        Long id = dog.getId();
        Long id2 = dog2.getId();
        Long id3 = dog3.getId();
        
        em.getTransaction().begin();
        em.remove(dog2);
        em.getTransaction().commit();
        em.clear();
        
        assertNull(dao.getDog(id2));
        assertNotNull(dao.getDog(id));
        assertNotNull(dao.getDog(id3));
   }
   
   public void testGetAllDogs() {
       EntityManager em = emf.createEntityManager();
        DogDaoImpl dao = new DogDaoImpl(em);
        Dog dog = newDog("prvy", "vlk", new LocalDate(1998, 05, 22));
        Dog dog2 = newDog("druhy", "vlciak", new LocalDate(1995, 01, 21));
        Dog dog3 = newDog("treti", "vlcisko", new LocalDate(1993, 8, 26));
        em.getTransaction().begin();
        dao.addDog(dog);
        dao.addDog(dog2);
        dao.addDog(dog3);
        em.getTransaction().commit();
        em.clear();
        
        List all = dao.getAllDogs();
        assertTrue(all.contains(dog));
        assertTrue(all.contains(dog2));
        assertTrue(all.contains(dog3));
   }
   
   public void testGetDogsByOwner() {
       EntityManager em = emf.createEntityManager();
       CustomerDaoImpl custDao = new CustomerDaoImpl();
       custDao.setEntityManager(em);
       Customer customer1 = new Customer("Tomas", "Hehehe", "Purkynova 40", "000999111");
       Customer customer2 = new Customer("Szilard", "Nemeth", "Rozalkova 40", "9999999999");
       Customer customer3 = new Customer("Milan", "Cajda", "Osminova 40", "888888888");
       em.getTransaction().begin();
       custDao.createCustomer(customer1);
       custDao.createCustomer(customer2);
       custDao.createCustomer(customer3);
       em.getTransaction().commit();
       em.clear();
        
        Dog dog = new Dog("prvy", "vlk", new LocalDate(1998, 05, 22), customer1);
        Dog dog2 = new Dog("druhy", "vlciak", new LocalDate(1995, 01, 21), customer2);
        Dog dog3 = new Dog("treti", "vlcisko", new LocalDate(1993, 8, 26), customer2);
        DogDaoImpl dao = new DogDaoImpl(em);
        em.getTransaction().begin();
        dao.addDog(dog);
        dao.addDog(dog2);
        dao.addDog(dog3);
        em.getTransaction().commit();
        em.clear();
        
        assertTrue(dao.getDogsByOwner(customer1).contains(dog));
        assertTrue(dao.getDogsByOwner(customer2).contains(dog2) && dao.getDogsByOwner(customer2).contains(dog3));
        assertTrue(dao.getDogsByOwner(customer3).isEmpty());
   }
   
   private void assertDeepEquals(Dog dog1, Dog dog2) {
       assertEquals(dog1.getId(), dog2.getId());
       assertEquals(dog1.getName(), dog2.getName());
       assertEquals(dog1.getBreed(), dog2.getBreed());
       assertEquals(dog1.getBirth(), dog2.getBirth());
   }
}
