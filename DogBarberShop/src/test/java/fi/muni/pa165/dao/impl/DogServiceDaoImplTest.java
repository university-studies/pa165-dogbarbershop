/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao.impl;

import fi.muni.pa165.dao.impl.DogServiceDaoImpl;
import fi.muni.pa165.entity.Customer;
import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.DogService;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.entity.Service;
import java.sql.Date;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import junit.framework.TestCase;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

/**
 *
 * @author martin
 */
public class DogServiceDaoImplTest extends TestCase{
    
    DogServiceDaoImpl dao;
    
    private EntityManagerFactory emFactory;

    private EntityManager em;
    
    private static Logger logger = Logger.getLogger(DogServiceDaoImplTest.class.getName());
    
    public DogServiceDaoImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        logger.info("Building JPA EntityManager for unit tests");
        
        emFactory = Persistence.createEntityManagerFactory("testPU");
        em = emFactory.createEntityManager();
        
        this.dao = new DogServiceDaoImpl();
        this.dao.setEntityManager(em);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        logger.info("Stopping in-memory database.");
    }
    
    private static DogService newDogService(Dog dog, Service service, Date serviceDate, Long employeeId){
        DogService dogService = new DogService();
        dogService.setDog(dog);
        dogService.setService(service);
        dogService.setServiceDate(serviceDate);
        dogService.setServedBy(employeeId);
        return dogService;
    }
    
    private static Dog newDog(String name, String breed, LocalDate date, Customer owner){
        Dog dog = new Dog();
        dog.setName(name);
        dog.setBreed(breed);
        dog.setBirth(date);
        dog.setOwner(owner);
        return dog;
    }
    
    private static Service newService(String name, Long price, Duration duration){
        Service service = new Service();
        service.setName(name);
        service.setPrice(price);
        service.setDuration(duration);
        return service;
    }
    
    public void testCreateDogService(){
        Customer cust = new Customer("Martin", "Sakac", "Purkynova 4", "111");
        Dog dog = newDog("Havo", "Doga", new LocalDate(), cust);
        Service service = newService("Strihanie", new Long(100), new Duration(1000000));
        em.getTransaction().begin();
        em.persist(cust);
        em.persist(dog);
        em.persist(service);
        em.getTransaction().commit();
        DogService dogService = newDogService
                (dog, service, Date.valueOf("2013-10-10"), new Long(1));
        em.getTransaction().begin();
        dao.createDogService(dogService);
        em.getTransaction().commit();
        assertNotNull(dogService.getId());
    }
    
    public void testDeleteDogService(){
        Customer cust = new Customer("Martin", "Sakac", "Purkynova 4", "111");
        Dog dog = newDog("Havo", "Doga", new LocalDate(), cust);
        Service service = newService("Strihanie", new Long(100), new Duration(1000000));
        em.getTransaction().begin();
        em.persist(cust);
        em.persist(dog);
        em.persist(service);
        em.getTransaction().commit();
        DogService dogService = newDogService
                (dog, service, Date.valueOf("2013-10-10"), new Long(1));
        em.getTransaction().begin();
        dao.createDogService(dogService);
        em.getTransaction().commit();
        
        assertNotNull(em.find(DogService.class, dogService.getId()));
        em.getTransaction().begin();
        em.remove(dogService);
        em.getTransaction().commit();
        assertNull(em.find(DogService.class, dogService.getId()));
    }
    
    public void testGetDogServiceById(){
        Customer cust = new Customer("Martin", "Sakac", "Purkynova 4", "111");
        Dog dog = newDog("Havo", "Doga", new LocalDate(), cust);
        Service service = newService("Strihanie", new Long(100), new Duration(1000000));
        em.getTransaction().begin();
        em.persist(cust);
        em.persist(dog);
        em.persist(service);
        em.getTransaction().commit();
        DogService dogService = newDogService
                (dog, service, Date.valueOf("2013-10-10"), new Long(1));
        em.getTransaction().begin();
        dao.createDogService(dogService);
        em.getTransaction().commit();
        assertNotNull(dogService.getId());
        
        Long id = dogService.getId();
        assertNotNull(id);
        assertNotNull(dao.getDogServiceById(id));
        assertEquals(dogService, dao.getDogServiceById(id));
    }
    
    public void testGetDogServiceByDog(){
        Customer cust = new Customer("Martin", "Sakac", "Purkynova 4", "111");
        Dog dog = newDog("Havo", "Doga", new LocalDate(), cust);
        Service service = newService("Strihanie", new Long(100), new Duration(1000000));
        em.getTransaction().begin();
        em.persist(cust);
        em.persist(dog);
        em.persist(service);
        em.getTransaction().commit();
        DogService dogService = newDogService
                (dog, service, Date.valueOf("2013-10-10"), new Long(1));
        em.getTransaction().begin();
        dao.createDogService(dogService);
        em.getTransaction().commit();
        assertNotNull(dogService.getId());
        
        Long id = dogService.getId();
        assertNotNull(id);
        assertNotNull(dao.getDogServiceByDog(dog));
        assertEquals(dogService, dao.getDogServiceByDog(dog).get(0));
    }
    
    public void testGetDogServiceByService(){
        Customer cust = new Customer("Martin", "Sakac", "Purkynova 4", "111");
        Dog dog = newDog("Havo", "Doga", new LocalDate(), cust);
        Service service = newService("Strihanie", new Long(100), new Duration(1000000));
        em.getTransaction().begin();
        em.persist(cust);
        em.persist(dog);
        em.persist(service);
        em.getTransaction().commit();
        DogService dogService = newDogService
                (dog, service, Date.valueOf("2013-10-10"), new Long(1));
        em.getTransaction().begin();
        dao.createDogService(dogService);
        em.getTransaction().commit();
        assertNotNull(dogService.getId());
        
        Long id = dogService.getId();
        assertNotNull(id);
        assertNotNull(dao.getDogServiceByService(service));
        assertEquals(dogService, dao.getDogServiceByService(service).get(0));
    }
    
    public void testGetDogServiceByDate(){
        Customer cust = new Customer("Martin", "Sakac", "Purkynova 4", "111");
        Dog dog = newDog("Havo", "Doga", new LocalDate(), cust);
        Service service = newService("Strihanie", new Long(100), new Duration(1000000));
        em.getTransaction().begin();
        em.persist(cust);
        em.persist(dog);
        em.persist(service);
        em.getTransaction().commit();
        DogService dogService = newDogService
                (dog, service, Date.valueOf("2013-10-10"), new Long(1));
        em.getTransaction().begin();
        dao.createDogService(dogService);
        em.getTransaction().commit();
        assertNotNull(dogService.getId());
        
        Long id = dogService.getId();
        assertNotNull(id);
        assertNotNull(dao.getDogServiceById(id));
        assertEquals(dogService, dao.getDogServiceById(id));
    }
    
    public void testGetDogServiceByEmployee(){
        Customer cust = new Customer("Martin", "Sakac", "Purkynova 4", "111");
        Dog dog = newDog("Havo", "Doga", new LocalDate(), cust);
        Service service = newService("Strihanie", new Long(100), new Duration(1000000));
        em.getTransaction().begin();
        em.persist(cust);
        em.persist(dog);
        em.persist(service);
        em.getTransaction().commit();
        DogService dogService = newDogService
                (dog, service, Date.valueOf("2013-10-10"), new Long(1));
        em.getTransaction().begin();
        dao.createDogService(dogService);
        em.getTransaction().commit();
        assertNotNull(dogService.getId());
        
        Long id = dogService.getId();
        assertNotNull(id);
        Employee empl = new Employee();
        empl.setId(new Long(1));
        assertNotNull(dao.getDogServiceByEmployee(empl));
        assertEquals(dogService, dao.getDogServiceByEmployee(empl).get(0));
    }
    
    public void testGetAllDogServices(){
        Customer cust = new Customer("Martin", "Sakac", "Purkynova 4", "111");
        Dog dog = newDog("Havo", "Doga", new LocalDate(), cust);
        Service service = newService("Strihanie", new Long(100), new Duration(1000000));
        em.getTransaction().begin();
        em.persist(cust);
        em.persist(dog);
        em.persist(service);
        em.getTransaction().commit();
        DogService dogService = newDogService
                (dog, service, Date.valueOf("2013-10-10"), new Long(1));
        em.getTransaction().begin();
        dao.createDogService(dogService);
        em.getTransaction().commit();
        assertNotNull(dogService.getId());
        assertEquals(dao.getAllDogServices().size(),1);
    }
}
