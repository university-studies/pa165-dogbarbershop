/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Service;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import org.joda.time.Duration;

/**
 *
 *
 */
public class ServiceDAOTest extends TestCase {

  ServiceDAO servDao;
  EntityManager em;

  public ServiceDAOTest(String testName) {
    super(testName);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    Map m = new HashMap();
    // in-memory DB
    m.put("hibernate.connection.url", "jdbc:derby:memory:ServiceDAOTestDB;create=true");
    //m.put("javax.persistence.jdbc.driver", "org.apache.derby.jdbc.EmbeddedDriver");
    // use testPU instead of PU
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU", m);
    this.em = emf.createEntityManager();
    this.servDao = new ServiceDAO(em);
  }

  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
  }

  private Service getService(String name, Long price, Duration dur) {
    Service s = new Service();
    s.setName(name);
    s.setPrice(price);
    s.setDuration(dur);
    return s;
  }

  /**
   * Test of getServiceById method, of class ServiceDAO.
   */
  public void testGetServiceById() {
    System.out.println("getServiceById");
    Service s = getService("abc", new Long(50),
            // 5 days
            new Duration(5 * 24 * 60 * 60 * 1000));

    em.getTransaction().begin();
    em.persist(s);
    em.getTransaction().commit();

    Long id = s.getId();
    assertNotNull(id);
    assertNotNull(servDao.getServiceById(id));
    assertEquals(s, servDao.getServiceById(id));
  }

  /**
   * Test of getServiceByName method, of class ServiceDAO.
   */
  public void testGetServiceByName() {
    System.out.println("getServiceByName");
    Service s = getService("low-cost-one", new Long(99),
            // 1 day
            new Duration(24 * 60 * 60 * 1000));

    em.getTransaction().begin();
    em.persist(s);
    em.getTransaction().commit();

    assertNotNull(servDao.getServiceByName("low-cost-one").get(0));
    assertEquals(s, servDao.getServiceByName("low-cost-one").get(0));
  }

  /**
   * Test of getServiceByPrice method, of class ServiceDAO.
   */
  public void testGetServiceByPrice() {
    System.out.println("getServiceByPrice");
    Service s = getService("expensive-one", new Long(8475),
            // 7 hours
            new Duration(7 * 60 * 60 * 1000));

    em.getTransaction().begin();
    em.persist(s);
    em.getTransaction().commit();

    assertNotNull(servDao.getServiceByPrice(new Long(8475)).get(0));
    assertEquals(s, servDao.getServiceByPrice(new Long(8475)).get(0));
  }

  /**
   * Test of getServiceByDuration method, of class ServiceDAO.
   */
  public void testGetServiceByDuration() {
    System.out.println("getServiceByDuration");
    Service s = getService("useless-one", new Long(135),
            // 5 minutes
            new Duration(5 * 60 * 1000));

    em.getTransaction().begin();
    em.persist(s);
    em.getTransaction().commit();

    assertNotNull(servDao.getServiceByDuration(new Duration(5 * 60 * 1000)).get(0));
    assertEquals(s, servDao.getServiceByDuration(new Duration(5 * 60 * 1000)).get(0));
  }
}
