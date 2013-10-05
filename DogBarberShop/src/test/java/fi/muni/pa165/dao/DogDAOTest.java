/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Dog;
import javax.persistence.Temporal;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 *
 * 
 */
public class DogDAOTest extends TestCase {
    
    DogDAO dao = new DogDAO();
    
    public DogDAOTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
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
        Dog dog2 = dao.getDog(id);
        assertEquals(dog, dog2);
    }
}
