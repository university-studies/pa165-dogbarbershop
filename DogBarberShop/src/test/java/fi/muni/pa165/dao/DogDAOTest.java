/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Dog;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;
import org.joda.time.LocalDate;

/**
 *
 * 
 */
public class DogDAOTest extends TestCase {
    
    EntityManagerFactory emf;
    
    public DogDAOTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
//        Map prop = new HashMap();
//        prop.put("hibernate.connection.url", "jdbc:derby:memory:dogDao-test;create=true");
//        prop.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.EmbeddedDriver");
         this.emf = Persistence.createEntityManagerFactory("testPU");
        
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCreateDog() {
        EntityManager em = emf.createEntityManager();
        DogDAO dao = new DogDAO(em);
        Dog dog = new Dog();
        em.getTransaction().begin();
        dao.createDog(dog);
        em.getTransaction().commit();
        em.clear();
        
        Long id = dog.getId();
        assertNotNull(id);
        Dog dog2 = dao.getDog(id);
        assertEquals(dog, dog2);
    }
    
    public void testGetDog() {
        EntityManager em = emf.createEntityManager();
        DogDAO dao = new DogDAO(em);
        Dog dog = new Dog("prvy", "vlk", new LocalDate(1998, 05, 22));
        Dog dog2 = new Dog("druhy", "vlciak", new LocalDate(1995, 01, 21));
        Dog dog3 = new Dog("treti", "vlcisko", new LocalDate(1993, 8, 26));
        em.getTransaction().begin();
        dao.createDog(dog);
        dao.createDog(dog2);
        dao.createDog(dog3);
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
       Dog dog1 = new Dog("prvy", "vlk", new LocalDate(1998, 05, 22));

        EntityManager em = emf.createEntityManager();
        DogDAO dao = new DogDAO(em);
        em.getTransaction().begin();
        dao.createDog(dog1);
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
   
   private void assertDeepEquals(Dog dog1, Dog dog2) {
       assertEquals(dog1.getId(), dog2.getId());
       assertEquals(dog1.getName(), dog2.getName());
       assertEquals(dog1.getBreed(), dog2.getBreed());
       assertEquals(dog1.getBirth(), dog2.getBirth());
   }
}
