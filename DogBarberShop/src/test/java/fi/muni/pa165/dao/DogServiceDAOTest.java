/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;

/**
 *
 * @author martin
 */
public class DogServiceDAOTest extends TestCase{
    
    DogServiceDAO dao;
    
    private EntityManagerFactory emFactory;

    private EntityManager em;
    
    private static Logger logger = Logger.getLogger(CustomerDAOTest.class.getName());
    
    public DogServiceDAOTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        logger.info("Building JPA EntityManager for unit tests");
        
        emFactory = Persistence.createEntityManagerFactory("testPU");
        em = emFactory.createEntityManager();
        
        this.dao = new DogServiceDAO();
        this.dao.setEntityManager(em);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        logger.info("Stopping in-memory database.");
    }
    
    public void testGetDogServiceById(){
    
    }
    
    public void testGetDogServiceByDog(){
    
    }
    
    public void testGetDogServiceByService(){
    
    }
    
    public void testGetDogServiceByDate(){
    
    }
    
    public void testGetDogServiceByEmployee(){
    
    }
}
