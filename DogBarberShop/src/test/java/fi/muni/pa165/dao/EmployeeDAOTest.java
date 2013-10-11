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
import javax.persistence.TypedQuery;

/**
 *
 * @author pavol
 */
public class EmployeeDAOTest extends TestCase {
    
    private EntityManagerFactory emFactory;
    
    public EmployeeDAOTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        emFactory = Persistence.createEntityManagerFactory("testPU");
        createTestData();
    } 
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCreateEmployee() {
        EntityManager em = emFactory.createEntityManager();
        
        EmployeeDAO dao = new EmployeeDAO(em);
        Employee emp = new Employee("Janko", "Krasny", "Zabiedovo 114/5", 
                "0905394355", "10000");
        dao.createEmployee(emp);
        
        assertNotNull(emp.getId());
        assertEquals(emp, dao.getEmployeeById(emp.getId()));
        
        em.close();
        assertNotNull(emp.getServices());
    }
    
    
    public void testUpdateEmployee() {
        EntityManager em = emFactory.createEntityManager();
        
        EmployeeDAO dao = new EmployeeDAO(em);
        
        /*
         * Vytiahnem Pavol z databaze, zmenim meno pomocu DAO
         * vytiahnem zmenene meno z databaze pomocou JPLQ
         * musia byt zhodne
         */
        Employee person = dao.getEmployeeByName("Pavol").get(0);
        person.setName("ZmeneneMeno");
        dao.updateEmployee(person);
        
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE"
                + " e.name='ZmeneneMeno'", Employee.class);
        
        assertEquals(person, q.getSingleResult());
        assertEquals(person, dao.getEmployeeById(person.getId()));
        
    }

    public void testDeleteEmployee() {
        EntityManager em = emFactory.createEntityManager();
        
        /*
         * V DB najdem Pavol vymazem ho a potom podla 
         * query otestujem ci je v DB jeho id
         */
        EmployeeDAO dao = new EmployeeDAO(em);  
        Employee person = dao.getEmployeeByName("Pavol").get(0);
        Long id = person.getId();
        
        dao.deleteEmployee(person);
        
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE"
                + " e.id = :id", Employee.class);
        q.setParameter("id", id);
        
        assertTrue(q.getResultList().isEmpty());   
        assertTrue(dao.getEmployeeByName("Pavol").isEmpty());
    }
   
    
    public void testGetEmployeeById() {  
        EntityManager em = emFactory.createEntityManager();
        
        EmployeeDAO dao = new EmployeeDAO(em);     
        
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE"
                + " e.name = 'Pavol'", Employee.class);
        
        Employee pavol = q.getResultList().get(0);
        Employee person = dao.getEmployeeById(pavol.getId());
        
        assertEquals(person, pavol);
    }
  
    public void testGetEmployeeByName() {
        EntityManager em = emFactory.createEntityManager();
        
        EmployeeDAO dao = new EmployeeDAO(em);     
        Employee person = dao.getEmployeeByName("Pavol").get(0);
        
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE"
                + " e.name = 'Pavol'", Employee.class);
        
        assertEquals(person, q.getResultList().get(0));
    }
    
    public void testGetEmployeeBySurname() {
        EntityManager em = emFactory.createEntityManager();
        
        EmployeeDAO dao = new EmployeeDAO(em);     
        Employee person = dao.getEmployeeBySurname("Loffay").get(0);
        
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e WHERE"
                + " e.surname = 'Loffay'", Employee.class);
        
        assertEquals(person, q.getResultList().get(0));
    }
    
    public void testGetAllEmployee() {
        EntityManager em = emFactory.createEntityManager();
        
        EmployeeDAO dao = new EmployeeDAO(em); 
        
        TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e",
                Employee.class);
        
        assertEquals(q.getResultList(), dao.getAllEmployee());
    }
    
    private void createTestData() {
        Employee pavol = new Employee("Pavol", "Loffay", 
                "Trstena Zapad 1144", "0905294355", "15000");
        
        Employee jano = new Employee ("Jano", "Drzak",
                "Tvrdosin Mieru 1562", "0904855", "15000");
        
        Employee fero = new Employee("Fero", "Vrabel", 
                "Namestovo 15", "090526444", "15674");
        
        Employee zuzka = new Employee("Zuzana", "Ostrihana", 
                "Bratislava 15", "09054444", "19000");
        
        Employee lucia = new Employee("Lucia", "Duroskova", 
                "Podbiel 189", "03052644", "18674");
        
        EntityManager em = emFactory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(jano);
        em.persist(pavol);
        em.persist(fero);
        em.persist(zuzka);
        em.persist(lucia);
        em.getTransaction().commit();
        
        em.close();
    }
}

