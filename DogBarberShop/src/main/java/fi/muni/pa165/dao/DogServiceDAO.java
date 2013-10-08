/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.dao;

import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.DogService;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.entity.Service;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author martin
 */
public class DogServiceDAO implements IDogServiceDAO{
    
    private EntityManager em;
    
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public DogService getDogServiceById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DogService> getDogServiceByDog(Dog dog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DogService> getDogServiceByService(Service service) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DogService> getDogServiceByDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DogService> getDogServiceByEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
