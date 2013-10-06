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
import junit.framework.TestCase;

/**
 *
 * 
 */
public class DogDAOTest extends TestCase {
    
    DogDAO dao;
    
    public DogDAOTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Map prop = new HashMap();
        prop.put("hibernate.connection.url", "jdbc:derby:memory:dogDao-test;create=true");
        prop.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.EmbeddedDriver");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU", prop);
        EntityManager em = emf.createEntityManager();
        this.dao = new DogDAO(em);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    
    public void testCreateDog() {
        Dog dog = new Dog();
        dao.createDog(dog);
        Long id = dog.getId();
        assertNotNull(id);
        Dog dog2 = dao.getDog(id);
        assertEquals(dog, dog2);
    }
}
