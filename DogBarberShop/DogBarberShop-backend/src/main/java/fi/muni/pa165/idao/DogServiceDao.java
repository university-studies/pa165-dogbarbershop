/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.idao;

import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.DogService;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.entity.Service;
import java.util.List;
import javax.persistence.EntityManager;
import org.joda.time.LocalDate;

/**
 *
 * @author martin
 */
public interface DogServiceDao {
    /*
     * 
     */
     void setEntityManager(EntityManager em);
    /*
     * 
     */
    public DogService createDogService(DogService dogService);
    
    /*
     * 
     */
    public void deleteDogService(DogService dogService);
    
    /*
     * return all DogServices from DB
     */
    public List<DogService> getAllDogServices();
    
    /*
     * 
     */
    public DogService getDogServiceById(Long id);
    
    /*
     * 
     */
    public List<DogService> getDogServiceByDog(Dog dog);
    
    /*
     * 
     */
    public List<DogService> getDogServiceByService(Service service);
    
    /*
     * 
     */
    public List<DogService> getDogServiceByDate(LocalDate date);
    
    /*
     * 
     */
    public List<DogService> getDogServiceByEmployee(Employee employee);
    
    DogService updateDogService(DogService service) ;
}
