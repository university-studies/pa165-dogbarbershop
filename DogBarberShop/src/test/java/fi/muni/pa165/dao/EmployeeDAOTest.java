/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Employee;
import junit.framework.TestCase;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pavol
 */
public class EmployeeDAOTest extends TestCase {
    
    EmployeeDAO dao;
    private EntityManagerFactory emFactory;
    private EntityManager em;
    
    public EmployeeDAOTest(String testName) {
        super(testName);
    }
        
    
    private void createTestData() {
        Employee pavol = new Employee("Pavol", "Loffay", 
                "Trstena Zapad 1144", "0905294355", "15000");
        
        Employee jano = new Employee ("Jano", "Drzak",
                "Tvrdosin Mieru 1562", "0904855", "15000");
        
        em.getTransaction().begin();
        em.persist(jano);
        em.persist(pavol);
        em.getTransaction().commit();
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        emFactory = Persistence.createEntityManagerFactory("testPU");
        em = emFactory.createEntityManager();
        
        this.dao = new EmployeeDAO();
        this.dao.setEntityManager(em);
        
        createTestData();
    } 
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testGetEmployeeById() {
        Employee employee = new Employee("Pavol", "Loffay", 
                "Trstena Zapad 1144", "0905294355", "15000");
        
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        
        Long id = employee.getId();
        assertNotNull(id);
        
        assertNotNull(dao.getEmployeeById(id));
        assertEquals(employee, dao.getEmployeeById(id));
    }
    
    public void testGetEmployeeByName() {
        Employee employee = new Employee("Honza", "Maly", 
                "Trstena Zapad 1144", "0905294355", "15000");
        
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        
        Long id = employee.getId();
        assertNotNull(id);
        
        assertNotNull(dao.getEmployeeByName("Honza"));
        assertEquals(employee, dao.getEmployeeByName("Honza").get(0));
        //assertNotSame(employee, dao.getEmployeeByName("Pavol").get(0));
        
        System.out.println("Vypis: ");
        System.out.println(dao.getEmployeeByName("Pavol").toString());
    }
    
    public void testGetEmployeeBySurname() {
        
        
    }
    
    public void tesUpdateEmployee() {
        
    }
    
    public void testDeleteEmployee() {
        
    }
}

