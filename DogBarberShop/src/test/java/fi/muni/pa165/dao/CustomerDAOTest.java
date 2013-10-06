/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Customer;
import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;

/**
 *
 * @author martin
 */
public class CustomerDAOTest extends TestCase{
    
    CustomerDAO dao;
    
    private EntityManagerFactory emFactory;

    private EntityManager em;
    
    private static Logger logger = Logger.getLogger(CustomerDAOTest.class.getName());
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        try {
            logger.info("Starting in-memory database for unit tests");
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;create=true").close();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during database startup.");
        }
        try {
            logger.info("Building JPA EntityManager for unit tests");
            emFactory = Persistence.createEntityManagerFactory("testPU");
            em = emFactory.createEntityManager();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Exception during JPA EntityManager instanciation.");
        }
        
        this.dao = new CustomerDAO();
        this.dao.setEntityManager(em);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        
        logger.info("Shuting down Hibernate JPA layer.");
        if (em != null) {
            em.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
        logger.info("Stopping in-memory database.");
        try {
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;shutdown=true").close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static Customer newCustomer(String name, String surname, 
            String address, String phone) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setSurname(surname);
        customer.setAddress(address);
        customer.setPhone(phone);
        return customer;
    }
    
    public void testGetCustomerById() {
        Customer customer = newCustomer("Martin", "Sakac", "Purkynova 4", "774670609");
        
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        
        Long id = customer.getId();
        assertNotNull(id);
        assertNotNull(dao.getCustomerById(id));
        assertEquals(customer, dao.getCustomerById(id));
    }
    
    public void testGetCustomerByName() {
        Customer customer = newCustomer("Peter", "Blabla", "Purkynova 10", "774000000");
    
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        
        Long id = customer.getId();
        assertNotNull(id);
        
        assertNotNull(dao.getCustomerByName("Peter"));
        assertEquals(customer, dao.getCustomerByName("Peter").get(0));
    }
    
    public void testGetCustomerBySurname() {
        Customer customer = newCustomer("Jan", "Haha", "Purkynova 20", "666666000");
    
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        
        Long id = customer.getId();
        assertNotNull(id);
        assertNotNull(dao.getCustomerBySurname("Haha"));
        assertEquals(customer, dao.getCustomerBySurname("Haha").get(0));
    }
    
    public void testGetCustomerByAddress() {
        Customer customer = newCustomer("Matej", "Prd", "Purkynova 30", "111222333");
    
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        
        Long id = customer.getId();
        assertNotNull(id);
        assertNotNull(dao.getCustomerByAddress("Purkynova 30"));
        assertEquals(customer, dao.getCustomerByAddress("Purkynova 30").get(0));
    }
    
    public void testGetCustomerByPhone() {
        Customer customer = newCustomer("Tomas", "Hehehe", "Purkynova 40", "000999111");
    
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        
        Long id = customer.getId();
        assertNotNull(id);
        assertNotNull(dao.getCustomerByPhone("000999111"));
        assertEquals(customer, dao.getCustomerByPhone("000999111").get(0));
    }
    
}
