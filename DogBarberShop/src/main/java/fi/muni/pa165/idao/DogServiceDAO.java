/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.muni.pa165.idao;

import fi.muni.pa165.entity.Dog;
import fi.muni.pa165.entity.DogService;
import fi.muni.pa165.entity.Employee;
import fi.muni.pa165.entity.Service;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author martin
 */
public interface DogServiceDAO {
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
    public List<DogService> getDogServiceByDate(Date date);
    
    /*
     * 
     */
    public List<DogService> getDogServiceByEmployee(Employee employee);
}
